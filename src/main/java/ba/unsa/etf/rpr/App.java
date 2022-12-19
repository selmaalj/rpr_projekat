package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * Hello world!
 *
 */
public class App extends Application
{
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/fxmlfile.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setMinHeight(325);
        stage.setMinWidth(475);
        stage.setMaxHeight(500);
        stage.setMaxWidth(700);
        stage.setTitle("Dobro došli!");
        stage.getIcons().add(new Image("file:///C:/Users/WIN10/Downloads/book.png"));
        stage.setScene(scene);
        stage.show();
    }
    public static void main (String[] args) {
        launch();
    }
}