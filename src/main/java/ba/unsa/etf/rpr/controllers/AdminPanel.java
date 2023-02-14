package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DostupanDaoSQLImpl;
import ba.unsa.etf.rpr.dao.InstruktorDaoSQLImpl;
import ba.unsa.etf.rpr.dao.MedjutabelaDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PredmetDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Dostupan;
import ba.unsa.etf.rpr.domain.Instruktor;
import ba.unsa.etf.rpr.domain.Medjutabela;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminPanel {

    public ListView<String> predmeti;
    public TextField nivo, naziv;
    public TextField updateNaziv, idPolje;
    public ListView<String> pregledPredmetaInstruktora;
    public TextField telefon, grad, cijena, ime, prezime;
    public RadioButton ponedjeljak, utorak, srijeda, cetvrtak, petak, subota, nedjelja;
    public ListView<String> instruktori;

    @FXML
    void initialize() {
        ObservableList<String> obp = FXCollections.observableArrayList();
        PredmetDaoSQLImpl pd = PredmetDaoSQLImpl.getInstance();
        List<Predmet> lp = pd.getAll();
        for (Predmet p : lp)
            obp.add(p.getId() + "-" + p.getNazivPredmeta() + "-" + p.getNivoSkolovanja());
        predmeti.setItems(obp);
        predmeti.getSelectionModel().select(0);
        ObservableList<String> obi = FXCollections.observableArrayList();
        InstruktorDaoSQLImpl insd = InstruktorDaoSQLImpl.getInstance();
        List<Instruktor> li = insd.getAll();
        for (Instruktor i : li)
            obi.add(i.getId() + "-" + i.getNazivInstruktora() + "-" + i.getTelefonskiBroj() + "-" + i.getCijenaPoCasu() + "-" + i.getGrad());
        instruktori.setItems(obi);
        instruktori.getSelectionModel().select(0);
    }

    public void akcijaDugmetaNazad(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/adminprijava.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Admin prijava");
        stage.setScene(scene);
        stage.show();
    }

    public void akcijaDugmetaPocetak(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/odabirwindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Dobro došli!");
        stage.setScene(scene);
        stage.show();
    }

    public void akcijaDugmetaDodajPredmet(ActionEvent actionEvent) {
        PredmetDaoSQLImpl pd = PredmetDaoSQLImpl.getInstance();
        Predmet p = pd.add(new Predmet(0, naziv.getText(), nivo.getText()));
        ObservableList<String> ob = predmeti.getItems();
        ob.add(p.getId() + "-" + p.getNazivPredmeta() + "-" + p.getNivoSkolovanja());
        predmeti.setItems(ob);
        predmeti.refresh();
    }

    public void akcijaDugmetaObrisiPredmet(ActionEvent actionEvent) {
        List<String> temp = predmeti.getSelectionModel().getSelectedItems();
        PredmetDaoSQLImpl pd = PredmetDaoSQLImpl.getInstance();
        MedjutabelaDaoSQLImpl md = MedjutabelaDaoSQLImpl.getInstance();
        ObservableList<String> ob = predmeti.getItems();
        for (String s : temp) {
            int id = Integer.parseInt(s.split("-")[0]);
            md.delete(id);
            pd.delete(id);
            ob.remove(s);
            predmeti.setItems(ob);
            predmeti.refresh();
        }
        predmeti.getSelectionModel().select(0);
    }

    public void akcijaDugmetaUpdatePredmet(ActionEvent actionEvent) {
        List<String> temp = predmeti.getSelectionModel().getSelectedItems();
        PredmetDaoSQLImpl pd = PredmetDaoSQLImpl.getInstance();
        ObservableList<String> ob = predmeti.getItems();
        for (String s : temp) {
            int id = Integer.parseInt(s.split("-")[0]);
            pd.update(new Predmet(id, updateNaziv.getText(), s.split("-")[2]));
            predmeti.refresh();
        }
        predmeti.getSelectionModel().select(0);
    }

    public void akcijaDugmetaSpoji(ActionEvent actionEvent) {
        MedjutabelaDaoSQLImpl md=MedjutabelaDaoSQLImpl.getInstance();
        PredmetDaoSQLImpl pd=PredmetDaoSQLImpl.getInstance();
        InstruktorDaoSQLImpl insd=InstruktorDaoSQLImpl.getInstance();
        List<String> l=predmeti.getSelectionModel().getSelectedItems();
        int idPredmet=Integer.parseInt(l.get(0).split("-")[0]);
        int idInstruktor=Integer.parseInt(idPolje.getText());
        if(md.postoji(idInstruktor, idPredmet)) {
        }
        else {
            md.add(new Medjutabela(pd.getById(idPredmet), insd.getById(idInstruktor)));
        }
    }

    public void akcijaDugmetaOdspoji(ActionEvent actionEvent) {
        MedjutabelaDaoSQLImpl md=MedjutabelaDaoSQLImpl.getInstance();
        List<String> l=predmeti.getSelectionModel().getSelectedItems();
        int idPredmet=Integer.parseInt(l.get(0).split("-")[0]);
        int idInstruktor=Integer.parseInt(idPolje.getText());
        if(md.postoji(idInstruktor, idPredmet)) {
            md.deleteByBoth(idInstruktor, idPredmet);
        }
        else {
        }
    }

    public void akcijaDugmetaObrisiInstruktor(ActionEvent actionEvent) {
        List<String> temp = instruktori.getSelectionModel().getSelectedItems();
        InstruktorDaoSQLImpl insd=InstruktorDaoSQLImpl.getInstance();
        MedjutabelaDaoSQLImpl md = MedjutabelaDaoSQLImpl.getInstance();
        DostupanDaoSQLImpl dd=DostupanDaoSQLImpl.getInstance();
        ObservableList<String> ob = instruktori.getItems();
        for (String s : temp) {
            int id = Integer.parseInt(s.split("-")[0]);
            md.deleteByInstruktor(id);
            List<Dostupan> listaDostupan=dd.getByInstruktor(insd.getById(id));
            for (Dostupan dostupan : listaDostupan) {
                dd.delete(dostupan.getId());
            }
            insd.delete(id);
            ob.remove(s);
            instruktori.setItems(ob);
            instruktori.refresh();
        }
        instruktori.getSelectionModel().select(0);
    }

    public void akcijaDugmetaUpdateInstruktor(ActionEvent actionEvent) {
    }

}
