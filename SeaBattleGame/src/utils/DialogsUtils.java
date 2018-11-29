package utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public class DialogsUtils {

    public static final String HELP_TEXT = "If you want reset your map, click RESET button." +
            "\nPlease leave at least one empty field between" +
            "\neach ship and do NOT place ships on each other." +
            "\nLMB - place the ship vertically, \nRMB - place the ship horizontally." +
            "\n\n\t\t\t\tHAVE FUN!";
    public static final String SECOND_HELP_TEXT = "You set all your ships or you click apply button." +
            "\nThe game begins!" + "\nShoot your enemy!";
    public static final String END_GAME_TEXT = "\tThe game is end. Thank you for play :)" +
            "\n\nClick Game -> Statistics, if you want see statistics." +
            "\n\nClick Game -> New Game, if you want start new game.";

    public static String STATISTICS_TEXT = "";

    private static final String HEADER_TEXT = "\t\t\tCONFIRMATION";
    private static final String HEADER_INFO_TEXT = "\t\t\tINFORMATION\nWhat now";
    private static final String EXIT_INFO = "Are you sure you want to LEAVE the game?";
    private static final String EXTRA_TEXT = "\nYour progress will be LOST!";


    // Creation of alert window
    public static void exitAlert() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(HEADER_TEXT);
        alert.setContentText(EXIT_INFO);

        alert.showAndWait().ifPresent(result -> {

            if (result == ButtonType.OK) {
                Platform.exit();
            }
        });
    }

    public static void endGameAlert() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(HEADER_TEXT);
        alert.setContentText(EXIT_INFO + EXTRA_TEXT);

        alert.showAndWait().ifPresent(result -> {

            if (result == ButtonType.OK) {
                Platform.exit();
            }
        });
    }

    public static void setHelpWindow(String stage) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(HEADER_INFO_TEXT);
        if (stage.equals("first"))
            alert.setContentText(HELP_TEXT);
        else if (stage.equals("second"))
            alert.setContentText(SECOND_HELP_TEXT);
        else
            alert.setContentText(END_GAME_TEXT);
        alert.showAndWait().ifPresent(result -> {
        });
    }

    public static void setStatisticsWindow(int playerHits, int playerShoots, int computerHits, int computerShoots, int playerCells) {

        STATISTICS_TEXT = "YOUR HITS:\t" + playerHits + "\t\t" + "ENEMY HITS:\t" + computerHits +
                "\n\nYour misses:\t" + (playerShoots - playerHits) + "\t\t" + "Enemy misses:\t" + (computerShoots - computerHits);

        if (playerHits == 20) {
            STATISTICS_TEXT += "\n\n YOU WIN! CONGRATULATIONS! :) \n\n Click Game -> New Game, if you want start new game.";
        } else if (computerHits == playerCells) {
            STATISTICS_TEXT += "\n\n YOU LOSE! I AM SORRY :( \n But do not worry, you can try again :) \n\n Click Game -> New Game, if you want start new game.";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("\t\t\tSTATISTICS");
        alert.setContentText(STATISTICS_TEXT);
        alert.showAndWait().ifPresent(result -> {
        });
    }

    public static Alert getBackMenuAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(HEADER_TEXT);
        alert.setContentText(EXIT_INFO + EXTRA_TEXT);
        return alert;
    }

}
