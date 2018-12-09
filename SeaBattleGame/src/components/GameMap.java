package components;


import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Random;

public class GameMap {

    public static void fillWithButtons(Button[][] playerButtonsArray, String color) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                playerButtonsArray[i][j] = new Button();
                playerButtonsArray[i][j].setPrefSize(25, 25);
                playerButtonsArray[i][j].setStyle("-fx-background-color: " + color +
                        "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

            }
        }
    }

    public static void mapFill(GridPane gridPane, Button[][] buttonsArray) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridPane.add(buttonsArray[i][j], j, i);
            }
        }
    }

    // method saving coordinates of computer ships to list
    public static void setComputerShips(List<Coordinate> computerShips) {

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

    private static Coordinate getRandomCoordinate() {
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        return new Coordinate(x, y);
    }

    public static int getRandomX() {
        Coordinate coordinate = getRandomCoordinate();
        return coordinate.getX();
    }

    public static int getRandomY() {
        Coordinate coordinate = getRandomCoordinate();
        return coordinate.getY();
    }

    public static void computerMapFill(GridPane computerMap, Button[][] computerButtonsArray) {
        mapFill(computerMap, computerButtonsArray);
    }

    public static void showComputerShips(List<Coordinate> computerShips, Button[][] computerButtonsArray) {
        int tempX;
        int tempY;
        for (int i = 0; i < computerButtonsArray.length; i++) {
            for (int j = 0; j < computerButtonsArray.length; j++) {
                computerButtonsArray[i][j].setStyle("-fx-background-color: #8ac8ff;" +
                        "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
            }
        }
        for (Coordinate coordinate : computerShips) {
            tempX = coordinate.getX();
            tempY = coordinate.getY();
            computerButtonsArray[tempX][tempY].setStyle("-fx-background-color: brown; " +
                    "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
        }

    }

}