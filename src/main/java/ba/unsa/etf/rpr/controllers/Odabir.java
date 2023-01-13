package ba.unsa.etf.rpr.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Odabir {
    public Button korisnik;
    public Button instruktor;
    public Button admin;

    public void akcijaDugmetaIzaberi(ActionEvent actionEvent) throws IOException {
        Node node= (Node) actionEvent.getSource();
        Stage stage= (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/korisnik.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Popunite zahtjeve:");
        stage.setScene(scene);
        stage.show();
    }
    public void akcijaDugmetaPrijavi(ActionEvent actionEvent) throws IOException {
        Node node= (Node) actionEvent.getSource();
        Stage stage= (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/instruktor.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Popunite podatke:");
        stage.setScene(scene);
        stage.show();
    }
    public void akcijaDugmetaAdmin(ActionEvent actionEvent) throws IOException {
    }
}
