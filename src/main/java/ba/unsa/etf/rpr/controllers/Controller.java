package ba.unsa.etf.rpr.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Controller {
    public Button potvrda;
    public Slider slider;
    public ChoiceBox<String> choiceBox;
    public Text text;
    public TextField textfield;
    public Separator separator;
    @FXML
    void initialize() {
        choiceBox.getItems().addAll("Osnovna", "Srednja", "Fakultet");
        slider.setMin(5.);
        slider.setMax(65.);
        slider.setBlockIncrement(10);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
    }
    public void akcijaDugmeta(ActionEvent actionEvent) {
        Node node= (Node) actionEvent.getSource();
        Stage stage= (Stage) node.getScene().getWindow();
        stage.close();
    }
}
