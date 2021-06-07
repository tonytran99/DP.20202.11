package views.screen.intro;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

public class IntroScreenHandler extends BaseScreenHandler {
	// singleton design pattern

    private static final Logger LOGGER = Utils.getLogger(IntroScreenHandler.class.getName()); //content coupling do thay doi du lieu cua LOGGER


    @FXML
    ImageView logo;
    //content coupling do thay doi du lieu cua LOGGER
    // common coupling do phuong thuc public co the thay  bat cu khi nao goi toi
    // Temporal cohesion khi cac method dc gom gai theo thoi gian su ly
    public IntroScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        try {
            setupData(null);
            setupFunctionality();
        } catch (IOException ex) {
            LOGGER.info(ex.getMessage());
            PopupScreen.error("Error when loading resources.");
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
            PopupScreen.error(ex.getMessage());
        }
    }

    // stamp coupling:Trueyn doi tuong dto nhung khong su dung
    protected void setupData(Object dto) throws Exception {
        return;
    }
    //content coupling do thay doi du lieu cua LOGGER
    // common coupling do phuong thuc public co the thay  bat cu khi n�o goi toi
    protected void setupFunctionality() throws Exception {
        File file = new File("src/main/resources/assets/images/Logo.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }
}