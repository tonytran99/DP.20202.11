    package views.screen.cart;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Logger;

import common.exception.MediaUpdateException;
import common.exception.ViewCartException;
import controller.SessionInformation;
import entity.cart.CartItem;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.ViewsConfig;

public class MediaHandler extends FXMLScreenHandler {

	// singleton design pattern
	private static Logger LOGGER = Utils.getLogger(MediaHandler.class.getName());

	@FXML
	protected HBox hboxMedia;

	@FXML
	protected ImageView image;

	@FXML
	protected VBox description;

	@FXML
	protected Label labelOutOfStock;

	@FXML
	protected VBox spinnerFX;

	@FXML
	protected Label title;

	@FXML
	protected Label price;

	@FXML
	protected Label currency;

	@FXML
	protected Button btnDelete;

	private CartItem cartItem;
	private Spinner<Integer> spinner;
	private CartScreenHandler cartScreen;
	public MediaHandler(String screenPath, CartScreenHandler cartScreen) throws IOException {
		super(screenPath);
		this.cartScreen = cartScreen;
		hboxMedia.setAlignment(Pos.CENTER);
	}
	
	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
		setMediaInfo();
	}
        // Communicational cohesion khi cac thanh phan cua method nay dung chung du lieu cartItem
	private void setMediaInfo() {
		final int HEIGHT_MEDIA_INFO = 110;
		final int WIDTH_MEDIA_INFO = 92;
		title.setText(cartItem.getMedia().getTitle()); // content coupling do doi du lieu cuaa title
		price.setText(ViewsConfig.getCurrencyFormat(cartItem.getPrice())); // content coupling do doi du lieu cua price
		File file = new File(cartItem.getMedia().getImageURL());
		Image im = new Image(file.toURI().toString());
		image.setImage(im); 
		image.setPreserveRatio(false);
		image.setFitHeight(HEIGHT_MEDIA_INFO);
		image.setFitWidth(WIDTH_MEDIA_INFO);

		// add delete button
		btnDelete.setFont(ViewsConfig.REGULAR_FONT); // content coupling do thay doi du lieu cua btnDelete
		btnDelete.setOnMouseClicked(e -> {
			try {
				SessionInformation.cart.removeCartMedia(cartItem); // update user cart
				cartScreen.updateCart(); // re-display user cart
				LOGGER.info("Deleted " + cartItem.getMedia().getTitle() + " from the cart");
			} catch (SQLException exp) {
				exp.printStackTrace();
				throw new ViewCartException();
			}
		});

		initializeSpinner();
	}
        // content coupling do thay doi du lieu cua btnDelete,LOGGER,cartItem,labelOutOfStock,spinner,price
        //common coupling do phuong thuc public co the thay the bat cu luc nao duoc goi
	private void initializeSpinner(){
		SpinnerValueFactory<Integer> valueFactory = //
			new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, cartItem.getQuantity());
		spinner = new Spinner<Integer>(valueFactory); 
		spinner.setOnMouseClicked( e -> {
			try {
				int numOfProd = this.spinner.getValue();
				int remainQuantity = cartItem.getMedia().getQuantity();
				LOGGER.info("NumOfProd: " + numOfProd + " -- remainOfProd: " + remainQuantity);
				if (numOfProd > remainQuantity){
					LOGGER.info("product " + cartItem.getMedia().getTitle() + " only remains " + remainQuantity + " (required " + numOfProd + ")");
					labelOutOfStock.setText("Sorry, Only " + remainQuantity + " remain in stock");
					spinner.getValueFactory().setValue(remainQuantity);
					numOfProd = remainQuantity;
				}

				// update quantity of mediaCart in useCart
				cartItem.setQuantity(numOfProd);

				// update the total of mediaCart
				price.setText(ViewsConfig.getCurrencyFormat(numOfProd* cartItem.getPrice()));

				// update subtotal and amount of Cart
				cartScreen.updateCartAmount();

			} catch (SQLException e1) {
				throw new MediaUpdateException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
			}
			
		});
		spinnerFX.setAlignment(Pos.CENTER);
		spinnerFX.getChildren().add(this.spinner);
	}
}