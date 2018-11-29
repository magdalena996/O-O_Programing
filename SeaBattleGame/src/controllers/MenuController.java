package controllers;

/**
 * Menu window background source: http://getwallpapers.com
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import utils.FxmlUtils;

import java.io.IOException;

import static controllers.OptionsController.musicStatus;
import static utils.DialogsUtils.exitAlert;

public class MenuController {

    private MainController mainController;

    private FxmlUtils fxmlUtils = new FxmlUtils();

    @FXML
    void initialize() {
        if (musicStatus)
            FxmlUtils.musicLoader();
    }

    // method assigned to button "Start Game" used for opening game window
    // Actions for controls are defined in FXML files, in this case method
    // onAction="#openApplication"

    public void openApplication() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/SeaBattleGame.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // If need access to window controller, get it from loader

        SeaBattleGameController seaBattleGameController = loader.getController();
        seaBattleGameController.setMainController(mainController);
        seaBattleGameController.setMenuController(this);
        mainController.setScreen(pane);
    }

    // method assigned to button "Options" opening a window with options
    public void openOptions() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/OptionsScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OptionsController optionsController = loader.getController();
        optionsController.setMainController(mainController);
        mainController.setScreen(pane);
        optionsController.getCheckBox();

    }

    // method closing application window
    public void exit() {
        fxmlUtils.alertsSoundsLoader();
        exitAlert();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
