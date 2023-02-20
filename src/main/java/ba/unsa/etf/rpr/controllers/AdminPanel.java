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
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa za komunikaciju admina sa bazom podataka
 * Dva taba - predmeti i instruktori
 */

public class AdminPanel {

    public ListView<String> predmeti;
    public TextField nivo, naziv;
    public TextField telefon, grad, cijena, ime, prezime;
    public RadioButton ponedjeljak, utorak, srijeda, cetvrtak, petak, subota, nedjelja;
    public ListView<String> instruktori;
    public ListView<String> predmetiLista, pregledLista;

    @FXML
    void initialize() {
        ObservableList<String> obp = FXCollections.observableArrayList();
        PredmetDaoSQLImpl pd = PredmetDaoSQLImpl.getInstance();
        List<Predmet> lp = pd.getAll();
        for (Predmet p : lp)
            obp.add(p.getId() + "-" + p.getNazivPredmeta() + "-" + p.getNivoSkolovanja());
        predmeti.setItems(obp);
        predmetiLista.setItems(obp);
        predmetiLista.getSelectionModel().select(0);
        predmeti.getSelectionModel().selectedItemProperty().addListener((observableValue, oldval, newval) -> {
            if (newval != null) {
                naziv.setText(newval.split("-")[1]);
                nivo.setText(newval.split("-")[2]);
            }
        });
        predmeti.getSelectionModel().select(0);
        ObservableList<String> obi = FXCollections.observableArrayList();
        InstruktorDaoSQLImpl insd = InstruktorDaoSQLImpl.getInstance();
        List<Instruktor> li = insd.getAll();
        for (Instruktor i : li)
            obi.add(i.getId() + "-" + i.getNazivInstruktora() + "-" + i.getTelefonskiBroj() + "-" + i.getCijenaPoCasu() + "-" + i.getGrad());
        instruktori.setItems(obi);
        instruktori.getSelectionModel().selectedItemProperty().addListener((observableValue, oldval, newval) -> {
            if (newval != null) {
                ponedjeljak.setSelected(false);
                utorak.setSelected(false);
                srijeda.setSelected(false);
                cetvrtak.setSelected(false);
                petak.setSelected(false);
                subota.setSelected(false);
                nedjelja.setSelected(false);
                int id = Integer.parseInt(newval.split("-")[0]);
                Instruktor i = insd.getById(id);
                telefon.setText(i.getTelefonskiBroj());
                cijena.setText(String.valueOf(i.getCijenaPoCasu()));
                grad.setText(i.getGrad());
                ime.setText(i.getNazivInstruktora().split(" ")[0]);
                prezime.setText(i.getNazivInstruktora().split(" ")[1]);
                List<Dostupan> dani = DostupanDaoSQLImpl.getInstance().getByInstruktor(i);
                for (Dostupan d : dani) {
                    String dan = d.getDan();
                    if (dan.equals("Ponedjeljak")) ponedjeljak.setSelected(true);
                    if (dan.equals("Utorak")) utorak.setSelected(true);
                    if (dan.equals("Srijeda")) srijeda.setSelected(true);
                    if (dan.equals("Cetvrtak")) cetvrtak.setSelected(true);
                    if (dan.equals("Petak")) petak.setSelected(true);
                    if (dan.equals("Subota")) subota.setSelected(true);
                    if (dan.equals("Nedjelja")) nedjelja.setSelected(true);
                }
                List<Predmet> predmeti = MedjutabelaDaoSQLImpl.getInstance().getByInstruktor(id);
                ObservableList<String> obs = FXCollections.observableArrayList();
                for (Predmet p : predmeti)
                    obs.add(p.getId() + "-" + p.getNazivPredmeta() + "-" + p.getNivoSkolovanja());
                pregledLista.setItems(obs);
                pregledLista.getSelectionModel().select(0);
            }
        });
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
        String temp = predmeti.getSelectionModel().getSelectedItem();
        PredmetDaoSQLImpl pd = PredmetDaoSQLImpl.getInstance();
        MedjutabelaDaoSQLImpl md = MedjutabelaDaoSQLImpl.getInstance();
        ObservableList<String> ob = predmeti.getItems();
        int id = Integer.parseInt(temp.split("-")[0]);
        md.delete(id);
        pd.delete(id);
        ob.remove(temp);
        predmeti.setItems(ob);
        predmeti.refresh();
        predmeti.getSelectionModel().select(0);
    }

    public void akcijaDugmetaUpdatePredmet(ActionEvent actionEvent) {
        String temp = predmeti.getSelectionModel().getSelectedItem();
        PredmetDaoSQLImpl pd = PredmetDaoSQLImpl.getInstance();
        ObservableList<String> ob = predmeti.getItems();
        int id = Integer.parseInt(temp.split("-")[0]);
        Predmet p = pd.update(new Predmet(id, naziv.getText(), temp.split("-")[2]));
        for (int i = 0; i < ob.size(); i++) {
            if (temp.equals(ob.get(i))) {
                ob.set(i, p.getId() + "-" + p.getNazivPredmeta() + "-" + p.getNivoSkolovanja());
                break;
            }
        }
        predmeti.setItems(ob);
        predmeti.refresh();
        predmeti.getSelectionModel().select(0);
    }

    /**
     * Dodavanje veze izmedju instruktora i predmeta u Medjutabelu
     *
     * @param actionEvent
     */
    public void akcijaDugmetaSpoji(ActionEvent actionEvent) {
        MedjutabelaDaoSQLImpl md = MedjutabelaDaoSQLImpl.getInstance();
        ObservableList<String> ob = pregledLista.getItems();
        String predmet = predmetiLista.getSelectionModel().getSelectedItem();
        String instruktor = instruktori.getSelectionModel().getSelectedItem();
        if (!ob.contains(predmet)) {
            int id = Integer.parseInt(predmet.split("-")[0]);
            String naziv = predmet.split("-")[1];
            String nivo = predmet.split("-")[2];
            int idIns = Integer.parseInt(instruktor.split("-")[0]);
            String nazivIns = instruktor.split("-")[1];
            String telefon = instruktor.split("-")[2];
            double cijena = Double.parseDouble(instruktor.split("-")[3]);
            String grad = instruktor.split("-")[4];
            md.add(new Medjutabela(new Predmet(id, naziv, nivo), new Instruktor(idIns, nazivIns, telefon, cijena, grad)));
            ob.add(predmet);
            pregledLista.setItems(ob);
            pregledLista.refresh();
        }
    }

    /**
     * Brisanje veze izmedju instruktora i predmeta u Medjutabeli
     *
     * @param actionEvent
     */
    public void akcijaDugmetaOdspoji(ActionEvent actionEvent) {
        MedjutabelaDaoSQLImpl md = MedjutabelaDaoSQLImpl.getInstance();
        ObservableList<String> ob = pregledLista.getItems();
        String instruktor = instruktori.getSelectionModel().getSelectedItem();
        String predmet = pregledLista.getSelectionModel().getSelectedItem();
        int idPredmet = Integer.parseInt(predmet.split("-")[0]);
        int idInstruktor = Integer.parseInt(instruktor.split("-")[0]);
        md.deleteByBoth(idInstruktor, idPredmet);
        ob.remove(predmet);
        pregledLista.setItems(ob);
        pregledLista.refresh();
    }

    public void akcijaDugmetaObrisiInstruktor(ActionEvent actionEvent) {
        String temp = instruktori.getSelectionModel().getSelectedItem();
        InstruktorDaoSQLImpl insd = InstruktorDaoSQLImpl.getInstance();
        MedjutabelaDaoSQLImpl md = MedjutabelaDaoSQLImpl.getInstance();
        DostupanDaoSQLImpl dd = DostupanDaoSQLImpl.getInstance();
        ObservableList<String> ob = instruktori.getItems();
        int id = Integer.parseInt(temp.split("-")[0]);
        md.deleteByInstruktor(id);
        List<Dostupan> listaDostupan = dd.getByInstruktor(insd.getById(id));
        for (Dostupan dostupan : listaDostupan) {
            dd.delete(dostupan.getId());
        }
        insd.delete(id);
        ob.remove(temp);
        instruktori.setItems(ob);
        instruktori.refresh();
        instruktori.getSelectionModel().select(0);
    }

    public void akcijaDugmetaUpdateInstruktor(ActionEvent actionEvent) {
        String temp = instruktori.getSelectionModel().getSelectedItem();
        int id = Integer.parseInt(temp.split("-")[0]);
        InstruktorDaoSQLImpl insd = InstruktorDaoSQLImpl.getInstance();
        Instruktor i = insd.update(new Instruktor(id, ime.getText() + " " + prezime.getText(), telefon.getText(), Double.parseDouble(cijena.getText()), grad.getText()));
        ObservableList<String> ob = instruktori.getItems();
        for (int j = 0; j < ob.size(); j++) {
            if (temp.equals(ob.get(j))) {
                ob.set(j, i.getId() + "-" + i.getNazivInstruktora() + "-" + i.getTelefonskiBroj() + "-" + i.getCijenaPoCasu() + "-" + i.getGrad());
                break;
            }
        }
        instruktori.setItems(ob);
        instruktori.refresh();
        DostupanDaoSQLImpl.getInstance().deleteByInstruktor(i);
        List<String> dani = new ArrayList<>();
        if (ponedjeljak.isSelected()) dani.add("Ponedjeljak");
        if (utorak.isSelected()) dani.add("Utorak");
        if (srijeda.isSelected()) dani.add("Srijeda");
        if (cetvrtak.isSelected()) dani.add("Cetvrtak");
        if (petak.isSelected()) dani.add("Petak");
        if (subota.isSelected()) dani.add("Subota");
        if (nedjelja.isSelected()) dani.add("Nedjelja");
        for (String s : dani)
            DostupanDaoSQLImpl.getInstance().add(new Dostupan(0, s, i));
        instruktori.getSelectionModel().select(0);
    }
}
