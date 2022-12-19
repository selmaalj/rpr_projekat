package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public Button potvrda;
    public Slider slider;
    public ChoiceBox<String> choiceBox1, choiceBox2;
    public Text text;
    public TextField textfield;
    public Separator separator;
    @FXML
    void initialize() {
        choiceBox1.getItems().addAll("Osnovna", "Srednja", "Fakultet");
        choiceBox1.getSelectionModel().select(0);
        choiceBox2.getItems().addAll("Ponedjeljak","Utorak","Srijeda","Cetvrtak","Petak","Subota","Nedjelja");
        choiceBox2.getSelectionModel().select(0);
        slider.setMin(5.);
        slider.setMax(65.);
        slider.setBlockIncrement(10);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
    }
    public void akcijaDugmeta(ActionEvent actionEvent) throws IOException {
       /* p.setNivoSkolovanja(choiceBox1.getValue());
        p.setNazivPredmeta(textfield.getText());
        System.out.println(p.getNazivPredmeta());
        System.out.println(p.getNivoSkolovanja());*/
        SingletonKlasa sk=SingletonKlasa.getInstance();
        Node node= (Node) actionEvent.getSource();
        Stage stage= (Stage) node.getScene().getWindow();
        sk.setCijena(slider.getValue());
        sk.setDan(choiceBox2.getValue());
        sk.setNivo(choiceBox1.getValue());
        sk.setPredmet(textfield.getText());
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/secondwindow.fxml"));
        Parent root = fxmlLoader.load();
        SecondWindowController secondwindow= fxmlLoader.getController();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Vaši instruktori!");
        stage.setScene(scene);
        stage.show();
    }
}
