package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class App extends Application
{
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/odabirwindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 400, 200);
        stage.setResizable(false);
        stage.setTitle("Dobro došli!");
        stage.getIcons().add(new Image("file:///C:/Users/WIN10/Downloads/book.png"));
        stage.setScene(scene);
        stage.show();
    }
    public static void main (String[] args) {
        launch();
    }
}