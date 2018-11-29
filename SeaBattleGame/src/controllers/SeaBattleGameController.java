package controllers;

/**
 * Game window background: https://images7.alphacoders.com/542/542340.jpg
 */

import components.*;
import javafx.scene.control.*;
import utils.FxmlUtils;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;

import java.util.*;
import java.util.List;

import static components.GameMap.*;
import static controllers.OptionsController.*;
import static utils.DialogsUtils.*;
import static utils.FxmlUtils.musicPlayer;

public class SeaBattleGameController {

    private MainController mainController;
    private MenuController menuController;

    private Button[][] playerButtonsArray = new Button[10][10];
    private Button[][] computerButtonsArray = new Button[10][10];

    private static List<Coordinate> playerShips = new ArrayList<>();
    private List<Coordinate> computerShips = new ArrayList<>();

    private static int count = 1;
    private static int computerCount = 1;
    private static int numberOfShips = 0;
    private static int numberOfComputerShips = 0;
    private int playerHits = 0;
    private int computerHits = 0;
    private static int playerCells = 0;
    private int playerMissed = 0;
    private int computerMissed = 0;
    private int playerShoots = 0;
    private int enemyShoots = 0;
    private String gameStage;

    private static boolean ifOneShip = false;
    private boolean applyMap = true;
    private boolean ifStartGame = false;

    private FxmlUtils fxmlUtils = new FxmlUtils();

    private static final String CREATED_BY = "Created by Ma$iK Studio \n\t(magdalena996)";

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
    private CheckMenuItem turnM;
    @FXML
    private CheckMenuItem turnSE;
    @FXML
    private Label createByLabel;


    @FXML
    void initialize() {

        createByLabel.setText(CREATED_BY);
        gameStage = "first";

        messageLabelFill(messageLabel);
        textArea.setEditable(false);
        computerMap.setDisable(true);

        // Filling the array with buttons
        fillWithButtons(playerButtonsArray, "#bce8ff;");
        fillWithButtons(computerButtonsArray, "#6b6b6b;");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int tempX = i;
                int tempY = j;

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
        computerMapFill(computerMap, computerButtonsArray);

        // and placing computer ships
         setComputerShips(computerShips);
       // placeComputerShips();
        textArea.setText(HELP_TEXT);

//        if(musicStatus)
//            turnM.setSelected(true);
//        else
//            turnM.setSelected(false);
//
//        if(soundsEffectsStatus)
//            turnSE.setSelected(true);
//        else
//            turnSE.setSelected(false);
    }

    // the method allowing the player to set ships
    public void setPlayerShips(int x, int y, String hOrV) {
        if (count == 1) {
            Ship ship = new Ship(4);
            if ((x >= 3 && hOrV.equals("Vertical")) || (y <= 6 && hOrV.equals("Horizontal"))) { // check if the ship does not cross the border of map
                playerCells += 4;
                fxmlUtils.getSoundsEffects("splash");
                ship.createShip(playerButtonsArray, playerShips, x, y, hOrV);
                numberOfShips++;
                ifOneShip = true;
            }
            if (numberOfShips == 1) {
                count++;
                numberOfShips = 0;
            }
        } else if (count == 2) {
            Ship ship = new Ship(3);
            if ((x >= 2 && hOrV.equals("Vertical")) || (y <= 7 && hOrV.equals("Horizontal"))) {
                playerCells += 3;
                fxmlUtils.getSoundsEffects("splash");
                ship.createShip(playerButtonsArray, playerShips, x, y, hOrV);
                numberOfShips++;
            }
            if (numberOfShips == 2) {
                count++;
                numberOfShips = 0;
            }

        } else if (count == 3) {
            Ship ship = new Ship(2);
            if ((x >= 1 && hOrV.equals("Vertical")) || (y <= 8 && hOrV.equals("Horizontal"))) {
                fxmlUtils.getSoundsEffects("splash");
                ship.createShip(playerButtonsArray, playerShips, x, y, hOrV);
                numberOfShips++;
                playerCells += 2;
            }
            if (numberOfShips == 3) {
                count++;
                numberOfShips = 0;
            }
        } else if (count == 4) {
            playerCells += 1;
            Ship ship = new Ship(1);
            fxmlUtils.getSoundsEffects("splash");
            ship.createShip(playerButtonsArray, playerShips, x, y, hOrV);
            numberOfShips++;

            if (numberOfShips == 4) {
                count++;
                numberOfShips = 0;
            }
            //System.out.println(count);
            ifAllShips(count);
        }

    }

    // the method set random enemy ships
    public void setRandomComputerShips(String hOrV) {
        int x;
        int y;
        if (computerCount == 1) {
            Ship ship = new Ship(4);
            x = getRandomX();
            y = getRandomY();
            if ((x >= 3 && hOrV.equals("Vertical")) || (y <= 6 && hOrV.equals("Horizontal"))) { // check if the ship does not cross the border of map
                ship.createShip(computerButtonsArray, computerShips, x, y, hOrV);
                numberOfComputerShips++;
            }
            if (numberOfComputerShips == 1) {
                computerCount++;
                numberOfComputerShips = 0;
            }
        } else if (computerCount == 2) {
            Ship ship = new Ship(3);
            x = getRandomX();
            y = getRandomY();
            if ((x >= 2 && hOrV.equals("Vertical")) || (y <= 7 && hOrV.equals("Horizontal"))) {
                ship.createShip(computerButtonsArray, computerShips, x, y, hOrV);
                numberOfComputerShips++;
            }
            if (numberOfComputerShips == 2) {
                computerCount++;
                numberOfComputerShips = 0;
            }

        } else if (computerCount == 3) {
            Ship ship = new Ship(2);
            x = getRandomX();
            y = getRandomY();
            if ((x >= 1 && hOrV.equals("Vertical")) || (y <= 8 && hOrV.equals("Horizontal"))) {
                ship.createShip(computerButtonsArray, computerShips, x, y, hOrV);
                numberOfComputerShips++;
            }
            if (numberOfComputerShips == 3) {
                computerCount++;
                numberOfComputerShips = 0;
            }
        } else if (computerCount == 4) {
            Ship ship = new Ship(1);
            x = getRandomX();
            y = getRandomY();
            ship.createShip(computerButtonsArray, computerShips, x, y, hOrV);
            numberOfComputerShips++;

            if (numberOfComputerShips == 4) {
                computerCount++;
                numberOfComputerShips = 0;
            }
           // System.out.println(computerCount);
        }

    }

    // the method placing random enemy ships
    public void placeComputerShips() {
        Random rand = new Random();
        String type;

        for (int i = 0; i < 10; i++) {
            if (rand.nextInt(2) == 1)
                type = "Vertical";
            else
                type = "Horizontal";

            setRandomComputerShips(type);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                computerButtonsArray[i][j].setStyle("-fx-background-color: #6b6b6b;" +
                        "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
            }
        }
    }

    // method allowing the player to shoot enemy
    public void startShootEnemy() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int tempX = i;
                int tempY = j;
                computerButtonsArray[tempX][tempY].setOnAction(event -> checkPlayerHit(tempX, tempY));
            }
        }
    }

    // method for random shooting at player map
    public void enemyShoot() {

        enemyShoots++;

        Random random = new Random();

        int randomI = random.nextInt(10);
        int randomJ = random.nextInt(10);

        while (playerButtonsArray[randomI][randomJ].getText().equals("X")) {
            randomI = random.nextInt(10);
            randomJ = random.nextInt(10);
        }

        boolean ifComputerMissed = true;
        for (Coordinate coordinate : playerShips) {

            if (randomI == coordinate.getX() && randomJ == coordinate.getY()) {

                playerButtonsArray[randomI][randomJ].setText("X");
                playerButtonsArray[randomI][randomJ].setStyle("-fx-background-color: #996e57;");
                ++computerHits;
                //textArea.setText("Your ship has been damaged " + computerHits + " times!\n" + textArea.getText() );
                ifComputerMissed = false;
                break;
            }
        }

        if (ifComputerMissed)
            checkEnemyMissed(randomI, randomJ);

        if (computerHits == playerCells) {
            fxmlUtils.soundsEffectsLoader("boo");
            messageLabel.setText("You lose! Try again.");
            textArea.setText("\tYou lose! Try again.");
            computerMap.setDisable(true);
            openStatisticsWindow();
            gameStage = "end";
        }

        enemyHitsLabel.setText(computerHits + " / " + enemyShoots);
    }

    // method checking if player hit enemy ship
    public void checkPlayerHit(int x, int y) {

        playerShoots++;

        // after player shoots, computer shoots
        enemyShoot();

        boolean ifPlayerMissed = true;
        for (Coordinate coordinate : computerShips) {
            if (coordinate.getX() == x && coordinate.getY() == y) {
                fxmlUtils.getSoundsEffects("destroy");

                computerButtonsArray[x][y].setDisable(true);
                computerButtonsArray[x][y].setText("X");
                computerButtonsArray[x][y].setStyle("-fx-background-color: Red;" +
                        "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                // textArea.setText(textArea.getText() + "\nYou damaged enemy ship!");

                // if player hit enemy ship, a variable playerHits is preincremented
                ++playerHits;
                textArea.setText("You damaged enemy ships " + playerHits + " times!\n" + textArea.getText());

                ifPlayerMissed = false;
                break;
            }
        }

        if (playerHits == 20) {
            messageLabel.setText("You win! Congratulations!");
            textArea.setText("You win! Congratulations!");
            fxmlUtils.soundsEffectsLoader("applause");
            computerMap.setDisable(true);
            openStatisticsWindow();
            gameStage = "end";
        }

        if (ifPlayerMissed)
            checkPlayerMissed(x, y);

        playerHitsLabel.setText(playerHits + " / " + playerShoots);

    }

    public void checkPlayerMissed(int x, int y) {

        if (!computerButtonsArray[x][y].getText().equals("X")) {
            fxmlUtils.getSoundsEffects("splash");
            computerButtonsArray[x][y].setStyle("-fx-background-color: #8ac8ff;" +
                    "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
            computerButtonsArray[x][y].setText("X");
            //textArea.setText(textArea.getText() + "\nYou missed!");
            computerButtonsArray[x][y].setDisable(true);
            ++playerMissed;
            textArea.setText("You missed " + playerMissed + " times!\n" + textArea.getText());
        }
    }

    public void checkEnemyMissed(int x, int y) {

        playerButtonsArray[x][y].setText("X");
        //textArea.setText(textArea.getText() + "\nYour enemy missed!");
        playerButtonsArray[x][y].setDisable(true);
        ++computerMissed;
        textArea.setText("Your enemy missed " + computerMissed + " times!\n" + textArea.getText());
    }

    // method running when all player ships are set
    public void ifAllShips(int shipNumber) {
        if (shipNumber > 4) {
            computerMap.setDisable(false);
            playerMap.setDisable(true);
            resetButton.setDisable(true);
            applyButton.setDisable(true);
            textArea.setText("You set all your ships\nThe game begins!\nShoot your enemy!");
            messageLabel.setText("\n\nStatistics");
            startShootEnemy();
            gameStage = "second";
            ifStartGame = true;
        }
    }

    public void resetMap() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                playerButtonsArray[i][j].setStyle("-fx-background-color: #bce8ff; " +
                        "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
            }
        }
        numberOfShips = 0;
        count = 1;
        ifOneShip = false;

//        for(Coordinate coordinate: playerShips){
//            System.out.println(coordinate.toString());
//        }
//        System.out.println("--------------------");

        playerShips.clear();
    }

    public void messageLabelFill(Label label) {
        label.setText("Set your ships\nand click apply button.");
    }

    public void applyMap() {
        if (applyMap && ifOneShip) {
            computerMap.setDisable(false);
            playerMap.setDisable(true);
            resetButton.setDisable(true);
            applyButton.setDisable(true);
            applyMap = false;
            messageLabel.setText("\n\n\n\n\nSTATISTICS");
            textArea.setText("\t\t\tThe game begins!");
            startShootEnemy();
            gameStage = "second";
            ifStartGame = true;
        } else {
            messageLabel.setText("You must set\nat least one ship!");
        }
    }

    public void backMenu() {
        fxmlUtils.alertsSoundsLoader();

        Alert alert = getBackMenuAlert();
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                mainController.loadMenuScreen();
            }
        });

        numberOfShips = 0;
        count = 1;
        applyMap = true;
        ifOneShip = false;
        ifStartGame = false;
        playerShips.clear();
    }

    public void endGame() {
        fxmlUtils.alertsSoundsLoader();
        endGameAlert();
    }

    public void startNewGame() {
        applyMap = true;
        ifOneShip = false;
        numberOfShips = 0;
        count = 1;
        ifStartGame = false;
        menuController.openApplication();
        playerShips.clear();
        textArea.clear();
    }

    public void openHelpWindow() {
        setHelpWindow(gameStage);
    }

    public void openStatisticsWindow() {
        if (ifStartGame)
            setStatisticsWindow(playerHits, playerShoots, computerHits, enemyShoots, playerCells);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    // checking if checkMenuItem (turnM or turnSE) is selected or not, and doing proper instructions
    public void check() {
        if(turnM.isSelected()){
            musicPlayer.play();
            musicStatus = true;
        }else {
            musicPlayer.stop();
            musicStatus = false;
        }
        if(turnSE.isSelected()){
            soundsEffectsStatus = true;
        }else {
            soundsEffectsStatus = false;
        }
    }
}
