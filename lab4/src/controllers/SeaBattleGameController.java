package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.*;

public class SeaBattleGameController {
    private MainController mainController;

    private boolean applyMap = true;

    private Button[][] playerButtonsArray = new Button[10][10];
    private Button[][] computerButtonsArray = new Button[10][10];

    private List<Coordinate> playerShips = new ArrayList<>();
    private List<Coordinate> computerShips = new ArrayList<>();

    private static int count = 1;
    private static int numberOfShips = 0;
    private int playerHits = 0;
    private int computerHits = 0;
    private static int ship = 0;
    private static int playerCells = 0;
    private static int playerMissed = 0;
    private static int computerMissed = 0;
    private int playerShoots = 0;
    private int enemyShoots = 0;

    @FXML
    private GridPane playerMap;
    @FXML
    private GridPane computerMap;
    @FXML
    private Button resetButton;
    @FXML
    private Button applyButton;
    @FXML
    private TextArea textArea;
    @FXML
    private Label messageLabel;
    @FXML
    private Label playerHitsLabel;
    @FXML
    private Label enemyHitsLabel;


    @FXML
    void initialize() {

        messageLabelFill(messageLabel);

        // Filling the array with buttons
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int tempX = i;
                int tempY = j;

                playerButtonsArray[i][j] = new Button();
                playerButtonsArray[i][j].setPrefSize(25, 25);
                playerButtonsArray[i][j].setStyle("-fx-background-color: #8ac8ff; " +
                        "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                computerButtonsArray[i][j] = new Button();
                computerButtonsArray[i][j].setPrefSize(25, 25);
                //computerButtonsArray[i][j].setStyle("-fx-background-color: #8ac8ff; " +
                //      "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                computerButtonsArray[i][j].setStyle("-fx-background-color: #6b6b6b; " +
                        "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                // If primary mouse button is pressed the ship will be placed vertically
                // If secondary mouse button is pressed the ship will be placed horizontally
                playerButtonsArray[tempX][tempY].setOnMousePressed(event -> {
                    if (event.isPrimaryButtonDown())
                        setPlayerShips(tempX, tempY, "Vertical");
                    else if (event.isSecondaryButtonDown())
                        setPlayerShips(tempX, tempY, "Horizontal");
                });
            }
        }

        // Filling the player map with buttons
        mapFill(playerMap, playerButtonsArray);

        // Filling the computer map with buttons and placing computer ships
        computerMapFill();

        computerMap.setDisable(true);
        textArea.setEditable(false);

        textArea.setText("If you want reset your map, click reset button." +
                "\nPlease leave at least one empty field between" +
                "\neach ship and do not place ships on each other." +
                "\nLMB - place the ship vertically, \nRMB - place the ship horizontally." +
                "\n\n\t\t\t\tHave fun!");
    }

    // the method allowing the player to set ships
    public void setPlayerShips(int tempX, int tempY, String hOrV) {

        if (count == 1) {

            playerCells += 4;

            if (hOrV.equals("Vertical")) {

                if (tempX >= 3) {       // check if the ship does not cross the border of map

//                    addShipToVBox(3);
//                    verticalShip.getChildren().remove(1);
//                    verticalShip.getChildren().remove(2);
//                    verticalShip.getChildren().remove(3);
//                    verticalShip.getChildren().remove(4);

                    ship = 1;
                    numberOfShips++;
                    playerButtonsArray[tempX][tempY].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    playerButtonsArray[tempX - 1][tempY].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    playerButtonsArray[tempX - 2][tempY].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    playerButtonsArray[tempX - 3][tempY].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    // Saving the coordinates of player ships to the list
                    playerShips.add(new Coordinate(tempX, tempY));
                    playerShips.add(new Coordinate(tempX - 1, tempY));
                    playerShips.add(new Coordinate(tempX - 2, tempY));
                    playerShips.add(new Coordinate(tempX - 3, tempY));
                } else {
                    textArea.setText(textArea.getText() + "\nShip cross the map border!");
                }

            } else if (hOrV.equals("Horizontal")) {

                if (tempY <= 6) {       // check if the ship does not cross the border of map
                    ship = 1;
                    numberOfShips++;
                    playerButtonsArray[tempX][tempY].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    playerButtonsArray[tempX][tempY + 1].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    playerButtonsArray[tempX][tempY + 2].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    playerButtonsArray[tempX][tempY + 3].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    // Saving the coordinates of player ships to the list
                    playerShips.add(new Coordinate(tempX, tempY));
                    playerShips.add(new Coordinate(tempX, tempY + 1));
                    playerShips.add(new Coordinate(tempX, tempY + 2));
                    playerShips.add(new Coordinate(tempX, tempY + 3));
                } else {
                    textArea.setText(textArea.getText() + "\nShip cross the map border!");
                }
            }

            if (numberOfShips == 1) {
                count++;
                numberOfShips = 0;
            }

        } else if (count == 2) {
            playerCells += 3;

            if (hOrV.equals("Vertical")) {

                if (tempX >= 2) {
                    numberOfShips++;
                    playerButtonsArray[tempX][tempY].setStyle("-fx-background-color: #ba2ca1;" +
                            " -fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    playerButtonsArray[tempX - 1][tempY].setStyle("-fx-background-color: #ba2ca1; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    playerButtonsArray[tempX - 2][tempY].setStyle("-fx-background-color: #ba2ca1;" +
                            " -fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    playerShips.add(new Coordinate(tempX, tempY));
                    playerShips.add(new Coordinate(tempX - 1, tempY));
                    playerShips.add(new Coordinate(tempX - 2, tempY));
                } else {
                    textArea.setText(textArea.getText() + "\nShip cross the map border!");
                }

            } else if (hOrV.equals("Horizontal")) {

                if (tempY <= 7) {
                    numberOfShips++;
                    playerButtonsArray[tempX][tempY].setStyle("-fx-background-color: #ba2ca1; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    playerButtonsArray[tempX][tempY + 1].setStyle("-fx-background-color: #ba2ca1; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    playerButtonsArray[tempX][tempY + 2].setStyle("-fx-background-color: #ba2ca1; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    playerShips.add(new Coordinate(tempX, tempY));
                    playerShips.add(new Coordinate(tempX, tempY + 1));
                    playerShips.add(new Coordinate(tempX, tempY + 2));
                } else {
                    textArea.setText(textArea.getText() + "\nShip cross the map border!");
                }
            }

            if (numberOfShips == 2) {
                count++;
                numberOfShips = 0;
            }

        } else if (count == 3) {

            playerCells += 2;

            if (hOrV.equals("Vertical")) {

                if (tempX >= 1) {
                    numberOfShips++;
                    playerButtonsArray[tempX][tempY].setStyle("-fx-background-color: #0fff37; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    playerButtonsArray[tempX - 1][tempY].setStyle("-fx-background-color: #0fff37; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    playerShips.add(new Coordinate(tempX, tempY));
                    playerShips.add(new Coordinate(tempX - 1, tempY));
                } else {
                    textArea.setText(textArea.getText() + "\nShip cross the map border!");
                }

            } else if (hOrV.equals("Horizontal")) {

                if (tempY <= 7) {
                    numberOfShips++;
                    playerButtonsArray[tempX][tempY].setStyle("-fx-background-color: #0fff37; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    playerButtonsArray[tempX][tempY + 1].setStyle("-fx-background-color: #0fff37; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    playerShips.add(new Coordinate(tempX, tempY));
                    playerShips.add(new Coordinate(tempX, tempY + 1));
                } else {
                    textArea.setText(textArea.getText() + "\nShip cross the map border!");
                }
            }

            if (numberOfShips == 3) {
                count++;
                numberOfShips = 0;
            }

        } else if (count == 4) {

            playerCells += 1;

            if (hOrV.equals("Vertical")) {
                numberOfShips++;
                playerButtonsArray[tempX][tempY].setStyle("-fx-background-color: #f2ff49; " +
                        "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                playerShips.add(new Coordinate(tempX, tempY));

            } else if (hOrV.equals("Horizontal")) {
                numberOfShips++;
                playerButtonsArray[tempX][tempY].setStyle("-fx-background-color: #f2ff49; " +
                        "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                playerShips.add(new Coordinate(tempX, tempY));
            }

            if (numberOfShips == 4) {
                count++;
                numberOfShips = 0;
            }

            ifAllShips(count);
        }
    }


    // method saving coordinates of computer ships to list
    public void setComputerShips() {

//        computerButtonsArray[0][0].setStyle("-fx-background-color: Red");
//        computerButtonsArray[1][0].setStyle("-fx-background-color: Red");
//        computerButtonsArray[2][0].setStyle("-fx-background-color: Red");
//        computerButtonsArray[3][0].setStyle("-fx-background-color: Red");
//
//        computerButtonsArray[1][3].setStyle("-fx-background-color: Red");
//        computerButtonsArray[2][3].setStyle("-fx-background-color: Red");
//        computerButtonsArray[3][3].setStyle("-fx-background-color: Red");
//
//        computerButtonsArray[0][7].setStyle("-fx-background-color: Red");
//
//        computerButtonsArray[2][6].setStyle("-fx-background-color: Red");
//        computerButtonsArray[2][7].setStyle("-fx-background-color: Red");
//        computerButtonsArray[2][8].setStyle("-fx-background-color: Red");
//
//        computerButtonsArray[4][6].setStyle("-fx-background-color: Red");
//
//        computerButtonsArray[5][2].setStyle("-fx-background-color: Red");
//        computerButtonsArray[5][3].setStyle("-fx-background-color: Red");
//        computerButtonsArray[5][9].setStyle("-fx-background-color: Red");
//
//        computerButtonsArray[6][6].setStyle("-fx-background-color: Red");
//        computerButtonsArray[7][6].setStyle("-fx-background-color: Red");
//
//        computerButtonsArray[7][0].setStyle("-fx-background-color: Red");
//
//        computerButtonsArray[8][3].setStyle("-fx-background-color: Red");
//        computerButtonsArray[9][3].setStyle("-fx-background-color: Red");

        computerShips.add(new Coordinate(0, 0));
        computerShips.add(new Coordinate(1, 0));
        computerShips.add(new Coordinate(2, 0));
        computerShips.add(new Coordinate(3, 0));

        computerShips.add(new Coordinate(1, 3));
        computerShips.add(new Coordinate(2, 3));
        computerShips.add(new Coordinate(3, 3));

        computerShips.add(new Coordinate(0, 7));

        computerShips.add(new Coordinate(2, 6));
        computerShips.add(new Coordinate(2, 7));
        computerShips.add(new Coordinate(2, 8));

        computerShips.add(new Coordinate(4, 6));

        computerShips.add(new Coordinate(5, 2));
        computerShips.add(new Coordinate(5, 3));
        computerShips.add(new Coordinate(5, 9));

        computerShips.add(new Coordinate(6, 6));
        computerShips.add(new Coordinate(7, 6));

        computerShips.add(new Coordinate(7, 0));

        computerShips.add(new Coordinate(8, 3));
        computerShips.add(new Coordinate(9, 3));
    }

    // method allowing the player to shoot enemy
    public void startShootEnemy() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int tempX = i;
                int tempY = j;
                computerButtonsArray[tempX][tempY].setOnAction(event -> {
                    checkPlayerHit(tempX, tempY);
                });
            }
        }
    }

    // method checking if player hit enemy ship
    public void checkPlayerHit(int x, int y) {

        playerShoots++;

        // after player shoots, computer shoots
        enemyShoot();

        for (Coordinate coordinate : computerShips) {
            if (coordinate.getX() == x && coordinate.getY() == y) {

                computerButtonsArray[x][y].setDisable(true);
                computerButtonsArray[x][y].setText("X");
                computerButtonsArray[x][y].setStyle("-fx-background-color: Red;" +
                        "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                // textArea.setText("You damaged enemy ships " + (++playerHits) + " times!");
                textArea.setText(textArea.getText() + "\nYou damaged enemy ship!");

                // if player hit enemy ship, a variable playerHits is preincremented
                ++playerHits;

                if (playerHits == 20) {
                    messageLabel.setText("You win! Congratulations!");
                    textArea.setText("You win! Congratulations!");
                    computerMap.setDisable(true);
                }

            } else if (coordinate.getX() != x && coordinate.getY() != y) {
                checkPlayerMissed(x, y);
            }
        }
        playerHitsLabel.setText(playerHits + " / " + playerShoots);
    }

    // method for random shooting at player map
    public void enemyShoot() {

        enemyShoots++;

        Random random = new Random();

        int randomI = random.nextInt(10);
        int randomJ = random.nextInt(10);

        for (Coordinate coordinate : playerShips) {

            if (randomI == coordinate.getX() && randomJ == coordinate.getY()){
                    // !playerButtonsArray[randomI][randomJ].getText().equals("X")) {

                playerButtonsArray[randomI][randomJ].setText("X");
                playerButtonsArray[randomI][randomJ].setStyle("-fx-background-color: #996e57;");
                textArea.setText(textArea.getText() + "\nYour ship has been damaged!");
                ++computerHits;

                if (computerHits == playerCells) {
                    messageLabel.setText("You lose! Try again.");
                    textArea.setText("You lose! Try again.");
                    computerMap.setDisable(true);
                }

            } else if (coordinate.getX() != randomI && coordinate.getY() != randomJ) {
                checkEnemyMissed(randomI, randomJ);
            }
            // playerButtonsArray[randomI][randomJ].setText("X");
        }

        enemyHitsLabel.setText(computerHits + " / " + enemyShoots);

    }


    public void checkPlayerMissed(int x, int y) {

        if (!computerButtonsArray[x][y].getText().equals("X")) {
            computerButtonsArray[x][y].setStyle("-fx-background-color: #8ac8ff;" +
                    "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
            computerButtonsArray[x][y].setText("X");
            textArea.setText(textArea.getText() + "\nYou missed!");
            computerButtonsArray[x][y].setDisable(true);
            ++playerMissed;
        }
    }

    public void checkEnemyMissed(int x, int y) {

      //  if (!playerButtonsArray[x][y].getText().equals("X")) {
            playerButtonsArray[x][y].setText("X");
           // textArea.setText(textArea.getText() + "\nYour enemy missed!");
            playerButtonsArray[x][y].setDisable(true);
            ++computerMissed;
      //  }
    }

    // method running when all player ships are set
    public void ifAllShips(int shipNumber) {
        if (shipNumber > 4) {
            computerMap.setDisable(false);
            playerMap.setDisable(true);
            resetButton.setDisable(true);
            applyButton.setDisable(true);
            textArea.setText("You set all your ships." +
                    "\nThe game begins!" + "\nShoot your enemy!");
            messageLabel.setText("\n\nStatistics");
            startShootEnemy();
        }
    }

    public void backMenu() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure you want to back to menu?" +
                "\nYour progress will be lost!");

        alert.showAndWait().ifPresent(result -> {

            if (result == ButtonType.OK) {
                mainController.loadMenuScreen();
            }
        });
        numberOfShips = 0;
        count = 1;
    }

    public void endGame() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure you want to leave the game?" +
                 "\nYour progress will be lost!");

        alert.showAndWait().ifPresent(result -> {

            if (result == ButtonType.OK) {
                Platform.exit();
            }
        });
    }

    public void resetMap() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                playerButtonsArray[i][j].setStyle("-fx-background-color: #8ac8ff; " +
                        "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
            }
        }
        numberOfShips = 0;
        count = 1;
    }

    public void messageLabelFill(Label label) {
        label.setText("Set your ships \nand click apply button.");
    }

    public void applyMap() {
        if (applyMap && ship == 1) {
            computerMap.setDisable(false);
            playerMap.setDisable(true);
            resetButton.setDisable(true);
            applyButton.setDisable(true);
            applyMap = false;
            messageLabel.setText("\n\nStatistics");
            textArea.setText("\t\t\tThe game begins!");
            startShootEnemy();
        } else {
            messageLabel.setText("You must set" + "\nat least one ship!");
        }
    }


    // method filling map with buttons
    @FXML
    public void mapFill(GridPane gridPane, Button[][] buttonsArray) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridPane.add(buttonsArray[i][j], j, i);
            }
        }
    }

    public void computerMapFill() {
        mapFill(computerMap, computerButtonsArray);
        setComputerShips();
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
