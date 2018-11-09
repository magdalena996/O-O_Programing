package controllers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    private MainController mainController;


    // method assigned to button "Start Game" used for opening game window
    // Actions for controls are defined in FXML files, in this case method
    // onAction="#openApplication"

    public void openApplication(){
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
        mainController.setScreen(pane);
    }

    // method assigned to button "Options" opening a window with options
    public void openOptions(){
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

    }

    // method closing application window
    public void exit(){

        // Creation of alert window
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure you want to leave the game?");

        alert.showAndWait().ifPresent(result -> {

            if (result == ButtonType.OK) {
                Platform.exit();
            }
        });
    }

    public void setMainController (MainController mainController){
        this.mainController = mainController;
    }
}
