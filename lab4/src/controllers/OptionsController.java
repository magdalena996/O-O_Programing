package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;


public class OptionsController {
    @FXML
    private CheckBox checkBox = new CheckBox();
    @FXML
    private CheckBox checkBox2 = new CheckBox();
    private MainController mainController;


    public void backMenu() {
        mainController.loadMenuScreen();
    }

    public void saveSettings() {
        System.out.println("Changes saved.");
    }

    public void turnSoundsEffects() {

        if (checkBox2.isSelected()) {
            System.out.println("Sounds effects are on");
        } else {
            System.out.println("Sounds effects are off");
        }
    }

    public void turnMusic() {

        if (checkBox.isSelected()) {
            System.out.println("Music is on");
        } else {
            System.out.println("Music is off");
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}

