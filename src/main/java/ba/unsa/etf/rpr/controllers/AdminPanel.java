package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPanel {

    public void akcijaDugmetaNazad(ActionEvent actionEvent) throws IOException {
        Node node=(Node) actionEvent.getSource();
        Stage stage= (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/fxml/adminprijava.fxml"));
        Parent root=fxmlLoader.load();
        Scene scene=new Scene(root,400,200);
        stage.setTitle("Admin prijava");
        stage.setScene(scene);
        stage.show();
    }

    public void akcijaDugmetaPocetak(ActionEvent actionEvent) throws IOException {
        Node node=(Node) actionEvent.getSource();
        Stage stage= (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/fxml/odabirwindow.fxml"));
        Parent root=fxmlLoader.load();
        Scene scene=new Scene(root,400,200);
        stage.setTitle("Dobro došli!");
        stage.setScene(scene);
        stage.show();
    }

    public void akcijaDugmetaDodajPredmet(ActionEvent actionEvent) {
    }

    public void akcijaDugmetaObrisiPredmet(ActionEvent actionEvent) {
    }
}
