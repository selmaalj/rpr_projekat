package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.GMailer;
import ba.unsa.etf.rpr.dao.DostupanDaoSQLImpl;
import ba.unsa.etf.rpr.dao.InstruktorDaoSQLImpl;
import ba.unsa.etf.rpr.dao.MedjutabelaDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PredmetDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Dostupan;
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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Instruktor {
    public TextField ime;
    public TextField prezime;
    public TextField email;
    public ChoiceBox<String> grad;
    public TextField cijena;
    public RadioButton ponedjeljak, utorak, srijeda, cetvrtak, petak, subota, nedjelja;
    public ListView predmetiListView;
    public ListView pregledListView;
    public TextField telefon;

    @FXML
    void initialize() {
        ObservableList<String> ob = FXCollections.observableArrayList();
        PredmetDaoSQLImpl pd = PredmetDaoSQLImpl.getInstance();
        List<Predmet> l = pd.getAll();
        for (Predmet p : l) {
            ob.add(p.getId() + ". " + p.getNazivPredmeta() + " (" + p.getNivoSkolovanja() + ")");
        }
        predmetiListView.setItems(ob);
        predmetiListView.getSelectionModel().select(0);
        grad.getItems().addAll("Sarajevo", "Mostar", "Tuzla", "Zenica", "Banja Luka", "Brcko");
        grad.getSelectionModel().select(0);
    }

    public void prijaviSeAction(ActionEvent actionEvent) throws Exception {
        try {
            if (Double.parseDouble(cijena.getText()) < 0 || Double.parseDouble(cijena.getText()) > 50)
                throw new Izuzetak("Potrebno je da cijene budu u opsegu od 0 KM do 50 KM.\n" +
                        "Ponovite unos cijene.");
            InstruktorDaoSQLImpl ins = InstruktorDaoSQLImpl.getInstance();
            ba.unsa.etf.rpr.domain.Instruktor i = new ba.unsa.etf.rpr.domain.Instruktor();
            i.setNazivInstruktora(ime.getText() + " " + prezime.getText());
            i.setId(0);
            i.setGrad(grad.getValue());
            i.setTelefonskiBroj(telefon.getText());
            i.setCijenaPoCasu(Double.parseDouble(cijena.getText()));
            i = ins.add(i);
            ObservableList<String> ob = pregledListView.getItems();
            PredmetDaoSQLImpl pd = PredmetDaoSQLImpl.getInstance();
            for (String s : ob) {
                int id = Integer.parseInt(s.split("\\.")[0]);
                Predmet p = pd.getById(id);
                MedjutabelaDaoSQLImpl.getInstance().add(new Medjutabela(p, i));
            }
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
            GMailer gm = new GMailer();
            gm.posaljiMail("Podaci o instruktoru:" + "\nNaziv: " + ime.getText() + " " + prezime.getText() + "\nEmail adresa: " + email.getText() + "\nGrad: " + grad.getValue() + "\nCijena po času: " + cijena.getText());
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
        catch(Izuzetak e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            Stage st = (Stage) alert.getDialogPane().getScene().getWindow();
            st.getIcons().add(new Image("file:///C:/Users/WIN10/Downloads/book.png"));
            alert.setContentText(e.getMessage());
            cijena.clear();
            alert.showAndWait();
        }
    }

    public void akcijaDugmetaNazad(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/odabirwindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Admin:");
        stage.setScene(scene);
        stage.show();
    }

    public void akcijaDugmetaDodaj(ActionEvent actionEvent) {
        ObservableList<String> ob= pregledListView.getItems();
        List<String> temp = predmetiListView.getSelectionModel().getSelectedItems();
        for (String s : temp) {
            if (!ob.contains(s))
                ob.add(s);
        }
        pregledListView.setItems(ob);
        pregledListView.refresh();
        predmetiListView.getSelectionModel().select(0);
    }
}
