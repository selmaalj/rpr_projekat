package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.PredmetDao;
import ba.unsa.etf.rpr.dao.PredmetDaoSQLImpl;
import ba.unsa.etf.rpr.tabele.Instruktor;
import ba.unsa.etf.rpr.tabele.Predmet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;


public class Controller {
    public Button potvrda;
    public Slider slider;
    public ChoiceBox<String> choiceBox;
    public Text text;
    public TextField textfield;
    public Separator separator;
    private Predmet p;
    private PredmetDaoSQLImpl pd;
    private Instruktor ins;
    @FXML
    void initialize() {
        choiceBox.getItems().addAll("Osnovna", "Srednja", "Fakultet");
        slider.setMin(5.);
        slider.setMax(65.);
        slider.setBlockIncrement(10);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        p=new Predmet();
        pd=new PredmetDaoSQLImpl();
        ins=new Instruktor();
    }
    public double getCijenapoCasu(){
        return ins.getCijenaPoCasu();
    }
    public List<Integer> getIds(){
        return pd.getIds(p);
    }
    public void akcijaDugmeta(ActionEvent actionEvent) throws IOException {
        p.setNivoSkolovanja(choiceBox.getValue());
        p.setNazivPredmeta(textfield.getText());
        ins.setCijenaPoCasu(slider.getValue());
        System.out.println("Cijena po casu "+ins.getCijenaPoCasu());
        System.out.println("Nivo skolovanja: "+p.getNivoSkolovanja()+" naziv predmeta "+p.getNazivPredmeta());
        Node node= (Node) actionEvent.getSource();
        Stage stage= (Stage) node.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/secondwindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Vaši instruktori!");
        //stage.getIcons().add(new Image("file:///C:/Users/WIN10/Downloads/teacher.png"));
        stage.setScene(scene);
        stage.show();
    }
}
