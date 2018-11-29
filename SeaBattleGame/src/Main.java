import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.FxmlUtils;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // point the loader where is the window to be loaded
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainScreen.fxml"));

        // from loader using method load() get StackPane container
        StackPane stackPane = loader.load();

        // locating container in scene
        Scene scene = new Scene(stackPane, 900, 550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sea Battle Game");
        primaryStage.show();
        primaryStage.setResizable(false);


    }

    public static void main(String[] args) {
        launch(args);
    }
}