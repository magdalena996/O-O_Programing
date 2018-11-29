package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import static utils.FxmlUtils.musicPlayer;

public class OptionsController {
    @FXML
    private CheckBox musicCheckBox;
    @FXML
    private CheckBox soundsEffectsCheckBox;
    @FXML
    private Label infoLabel;

    private MainController mainController;

    public static boolean musicStatus = true;
    public static boolean soundsEffectsStatus = true;
    private boolean isSaved = false;
    private String INFO_TEXT = "If you back to menu before click Save Settings,\nyour changes will not be saved.";

    @FXML
    void initialize() {
        musicCheckBox.setSelected(true);
        soundsEffectsCheckBox.setSelected(true);
    }

    public void backMenu() {
        mainController.loadMenuScreen();
    }

    public void saveSettings() {

        if (soundsEffectsCheckBox.isSelected()) {
            soundsEffectsStatus = true;
            System.out.println("Sounds effects are on");
        } else {
            soundsEffectsStatus = false;
            //System.out.println("Sounds effects are off");
        }

        if (musicCheckBox.isSelected()) {
            musicStatus = true;
            //System.out.println("Music is on");

        } else {
            musicStatus = false;
            //System.out.println("Music is off");
        }

        infoLabel.setText("Changes are being saved...");
        //System.out.println("Changes saved.");
        isSaved = true;
    }

    public void turnMusic() {
        if (musicCheckBox.isSelected())
            musicPlayer.play();
        else
            musicPlayer.stop();
    }

    public void getCheckBox() {
        if (soundsEffectsStatus)
            soundsEffectsCheckBox.setSelected(true);
        else
            soundsEffectsCheckBox.setSelected(false);

        if (musicStatus)
            musicCheckBox.setSelected(true);
        else
            musicCheckBox.setSelected(false);
    }

    public void infoView() {
        if(!isSaved)
            infoLabel.setText(INFO_TEXT);
        else
            infoLabel.setText("Changes saved.");
    }

    public void change(){
        isSaved = false;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

}

