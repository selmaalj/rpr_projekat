package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Properties;

public class AdminPrijava{
    public PasswordField lozinka;
    public TextField mail;
    @FXML
    public void initialize() throws IOException {
    }
    public void akcijaDugmetaNazad(ActionEvent actionEvent) throws IOException {
        Node node= (Node) actionEvent.getSource();
        Stage stage= (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/odabirwindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Dobro došli!:");
        stage.setScene(scene);
        stage.show();
    }
    public void akcijaDugmetaPotvrdi(ActionEvent actionEvent) throws IOException{
        boolean tacan=false;
        Properties p=new Properties();
        p.load(ClassLoader.getSystemResource("admin.properties").openStream());
        String em = p.getProperty("email");
        String pass = p.getProperty("lozinka");
        if(mail.getText().equals(em) && lozinka.getText().equals(pass))
            tacan=true;
        if(tacan) {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/adminpanel.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 800, 500);
            stage.setTitle("Admin panel");
            stage.setScene(scene);
            stage.show();
        }
        else{
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/odabirwindow.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 400, 200);
            stage.setTitle("Dobro došli!:");
            stage.setScene(scene);
            stage.show();
        }
    }
}
