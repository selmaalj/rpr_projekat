package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.GMailer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Instruktor {
    public TextField ime;
    public TextField prezime;
    public TextField email;
    public TextField grad;
    public TextField cijena;
    public RadioButton ponedjeljak,utorak,srijeda,cetvrtak,petak,subota,nedjelja;

    @FXML
    void initialize() {
    }

    public void prijaviSeAction(ActionEvent actionEvent) throws Exception {
        GMailer gm=new GMailer();
        gm.posaljiMail(ime.getText()+" "+prezime.getText()+"\n"+email.getText()+"\n"+grad.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        Stage st = (Stage) alert.getDialogPane().getScene().getWindow();
        st.getIcons().add(new Image("file:///C:/Users/WIN10/Downloads/book.png"));
        alert.setContentText("Uspješno ste se prijavili\nOčekujte naš odgovor na email!");
        alert.showAndWait();
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
