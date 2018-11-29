package components;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private int shipLength;

    public Ship(int shipLength) {
        this.shipLength = shipLength;
    }

    public void createShip(Button[][] buttonsArray, List<Coordinate> listOfShips, int x, int y, String hOrV) {
        if (hOrV.equals("Vertical")) {
            switch (shipLength) {
                case 4:
                    buttonsArray[x][y].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    buttonsArray[x - 1][y].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    buttonsArray[x - 2][y].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    buttonsArray[x - 3][y].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    // Saving the ships coordinates to the list
                    listOfShips.add(new Coordinate(x, y));
                    listOfShips.add(new Coordinate(x - 1, y));
                    listOfShips.add(new Coordinate(x - 2, y));
                    listOfShips.add(new Coordinate(x - 3, y));
                    break;
                case 3:
                    buttonsArray[x][y].setStyle("-fx-background-color: #ba2ca1; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    buttonsArray[x - 1][y].setStyle("-fx-background-color: #ba2ca1; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    buttonsArray[x - 2][y].setStyle("-fx-background-color: #ba2ca1; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    listOfShips.add(new Coordinate(x, y));
                    listOfShips.add(new Coordinate(x - 1, y));
                    listOfShips.add(new Coordinate(x - 2, y));
                    break;
                case 2:
                    buttonsArray[x][y].setStyle("-fx-background-color: #0fff37; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    buttonsArray[x - 1][y].setStyle("-fx-background-color: #0fff37; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    listOfShips.add(new Coordinate(x, y));
                    listOfShips.add(new Coordinate(x - 1, y));
                    break;
                case 1:
                    buttonsArray[x][y].setStyle("-fx-background-color: #f2ff49; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    listOfShips.add(new Coordinate(x, y));
                    break;
            }
        } else if (hOrV.equals("Horizontal")) {
            switch (shipLength) {
                case 4:
                    buttonsArray[x][y].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    buttonsArray[x][y + 1].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    buttonsArray[x][y + 2].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    buttonsArray[x][y + 3].setStyle("-fx-background-color: Red; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    listOfShips.add(new Coordinate(x, y));
                    listOfShips.add(new Coordinate(x, y + 1));
                    listOfShips.add(new Coordinate(x, y + 2));
                    listOfShips.add(new Coordinate(x, y + 3));
                    break;
                case 3:
                    buttonsArray[x][y].setStyle("-fx-background-color: #ba2ca1; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    buttonsArray[x][y + 1].setStyle("-fx-background-color: #ba2ca1; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    buttonsArray[x][y + 2].setStyle("-fx-background-color: #ba2ca1; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    listOfShips.add(new Coordinate(x, y));
                    listOfShips.add(new Coordinate(x, y + 1));
                    listOfShips.add(new Coordinate(x, y + 2));
                    break;
                case 2:
                    buttonsArray[x][y].setStyle("-fx-background-color: #0fff37; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");
                    buttonsArray[x][y + 1].setStyle("-fx-background-color: #0fff37; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    listOfShips.add(new Coordinate(x, y));
                    listOfShips.add(new Coordinate(x, y + 1));
                    break;
                case 1:
                    buttonsArray[x][y].setStyle("-fx-background-color: #f2ff49; " +
                            "-fx-background-radius: 0; -fx-border-width: 1; -fx-border-color: black;");

                    listOfShips.add(new Coordinate(x, y));
                    break;
            }
        }
    }

}
