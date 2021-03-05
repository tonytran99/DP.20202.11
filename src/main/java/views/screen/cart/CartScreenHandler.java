package views.screen.cart;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import common.exception.MediaNotAvailableException;
import common.exception.PlaceOrderException;
import controller.PlaceOrderController;
import controller.ViewCartController;
import entity.cart.CartItem;
import entity.order.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.popup.PopupScreen;
import views.screen.shipping.ShippingScreenHandler;

public class CartScreenHandler extends BaseScreenHandler {
	private static Logger LOGGER = Utils.getLogger(CartScreenHandler.class.getName());

	@FXML
	private ImageView aimsImage;

	@FXML
	private Label pageTitle;

	@FXML
	VBox vboxCart;

	@FXML
	private Label shippingFees;

	@FXML
	private Label labelAmount;

	@FXML
	private Label labelSubtotal;

	@FXML
	private Label labelVAT;

	@FXML
	private Button btnPlaceOrder;
        //content coupling do doi du lieu cua LOGGER
        // common coupling do day la phuong thuc public nên có the thay the bat cu lúc nào
        // data coupling do truyen và su dung het du lieu
	public CartScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		try {
			setupFunctionality(); 
		} catch (IOException ex) {
			LOGGER.info(ex.getMessage());
			PopupScreen.error("Error when loading resources.");
		} catch (Exception ex) {
			LOGGER.info(ex.getMessage());
			PopupScreen.error(ex.getMessage());
		}
	}
        // content coupling do doi du lieu cua aimsImage
        // common coupling do day la phuong thuc public nên có the thay the bat cu lúc nào
	protected void setupFunctionality() throws Exception {
		// fix relative image path caused by fxml
		File file = new File(ViewsConfig.IMAGE_PATH + "/Logo.png");
		Image im = new Image(file.toURI().toString());
		aimsImage.setImage(im);

		// on mouse clicked, we back to home
		aimsImage.setOnMouseClicked(e -> {
			homeScreenHandler.show();
		});

		// on mouse clicked, we start processing place order use case
		btnPlaceOrder.setOnMouseClicked(e -> {
			LOGGER.info("Place Order button clicked");
			try {
				requestToPlaceOrder();
			} catch (SQLException | IOException exp) {
				LOGGER.severe("Cannot place the order, see the logs");
				exp.printStackTrace();
				throw new PlaceOrderException(Arrays.toString(exp.getStackTrace()).replaceAll(", ", "\n"));
			}

		});
	}
        
	public ViewCartController getBController(){
		return (ViewCartController) super.getBController();
	}
        // common coupling do day la phuong thuc public nên có the thay the bat cu lúc nào
	public void requestToViewCart(BaseScreenHandler prevScreen) throws SQLException {
		setPreviousScreen(prevScreen);
		setScreenTitle("Cart Screen");
		getBController().checkAvailabilityOfProduct();
		displayCartWithMediaAvailability();
		show();
	}
     // common coupling do day la phuong thuc public nên có the thay the bat cu lúc nào
	public void requestToPlaceOrder() throws SQLException, IOException {
		try {
			// create placeOrderController and process the order
			PlaceOrderController placeOrderController = new PlaceOrderController();
			if (placeOrderController.getListCartMedia().size() == 0){
				PopupScreen.error("You don't have anything to place");
				return;
			}

			placeOrderController.placeOrder();
			
			// display available media
			displayCartWithMediaAvailability();

			// create order
			Order order = placeOrderController.createOrder();

			// display shipping form
			ShippingScreenHandler shippingScreenHandler = new ShippingScreenHandler(
					this.stage, ViewsConfig.SHIPPING_SCREEN_PATH, order);
			shippingScreenHandler.setPreviousScreen(this);
			shippingScreenHandler.setHomeScreenHandler(homeScreenHandler);
			shippingScreenHandler.setScreenTitle("Shipping Screen");
			shippingScreenHandler.setBController(placeOrderController);
			shippingScreenHandler.show();

		} catch (MediaNotAvailableException e) {
			// if some media are not available then display cart and break usecase Place Order
			displayCartWithMediaAvailability();
		}
	}

	public void updateCart() throws SQLException{
		getBController().checkAvailabilityOfProduct();
		displayCartWithMediaAvailability();
	}
         
	void updateCartAmount(){
		// calculate subtotal and amount
		int subtotal = getBController().getCartSubtotal();
		int vat = (int)((ViewsConfig.PERCENT_VAT/100)*subtotal);
		int amount = subtotal + vat;
		LOGGER.info("amount" + amount); // content coupling do thay ??i d? li?u c?a LOGGER

		// update subtotal and amount of Cart
		labelSubtotal.setText(ViewsConfig.getCurrencyFormat(subtotal)); // content coupling do thay ??i d? li?u c?a labelSubtotal
		labelVAT.setText(ViewsConfig.getCurrencyFormat(vat)); // content coupling do thay ??i d? li?u c?a labelVAT
		labelAmount.setText(ViewsConfig.getCurrencyFormat(amount)); // content coupling do thay ??i d? li?u c?a labelAmount
	}
	//   common coupling do phuong thuc dang  public nên có the thay doi khi nao goi
	private void displayCartWithMediaAvailability(){
		// clear all old cartMedia
		vboxCart.getChildren().clear(); // content coupling do do doi du lieu cua vboxCart

		// get list media of cart after check availability
		List lstMedia = getBController().getListCartMedia();

		try {
			for (Object cm : lstMedia) {

				// display the attribute of vboxCart media
				CartItem cartItem = (CartItem) cm;
				MediaHandler mediaCartScreen = new MediaHandler(ViewsConfig.CART_MEDIA_PATH, this);
				mediaCartScreen.setCartItem(cartItem);  // content coupling do do doi du lieu cua mediaCartScreen

				// add spinner
				vboxCart.getChildren().add(mediaCartScreen.getContent()); // content coupling do do doi du lieu cua vboxCart
			}
			// calculate subtotal and amount
			updateCartAmount();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
