package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.PredmetDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Predmet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminPanel {

    public ListView<String> predmeti;

    @FXML
    void initialize(){
        ObservableList<String> ob= FXCollections.observableArrayList();
        PredmetDaoSQLImpl pd=PredmetDaoSQLImpl.getInstance();
        List<Predmet> l=pd.getAll();
        for(Predmet p: l)
            ob.add(p.getId()+"-"+p.getNazivPredmeta()+"-"+p.getNivoSkolovanja());
        predmeti.setItems(ob);
        predmeti.getSelectionModel().select(0);
    }

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
