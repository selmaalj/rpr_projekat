package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.InstruktorDaoSQLImpl;
import ba.unsa.etf.rpr.dao.MedjutabelaDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PredmetDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Instruktor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TabelaInstruktora {
    @FXML
    private TableView<ModelInstruktori> tableview;
    public TableColumn<ModelInstruktori, String> naziv;
    public TableColumn<ModelInstruktori, String> telefon;
    public TableColumn<ModelInstruktori, Double> cijena;
    public static Instruktor instruktor;

    @FXML
    public void initialize() throws IOException {
        PrenosPodataka sk = PrenosPodataka.getInstance();
        PredmetDaoSQLImpl pd = PredmetDaoSQLImpl.getInstance();
        int id = pd.getId(sk.getPredmet(), sk.getNivo());//id predmeta
        MedjutabelaDaoSQLImpl m = new MedjutabelaDaoSQLImpl();
        List<Integer> instruktorIds = m.getByPredmet(id);
            for (int i = 0; i < instruktorIds.size(); i++) {
                Instruktor ins = InstruktorDaoSQLImpl.getInstance().getById(instruktorIds.get(i));
                if (!(sk.getGrad().equals(ins.getGrad())) || (sk.getCijena()<ins.getCijenaPoCasu())) {
                    instruktorIds.remove(i--);
                }
            }
        ObservableList<ModelInstruktori> ob = FXCollections.observableArrayList();
            this.naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
            this.telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
            this.cijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
            for (Integer el : instruktorIds) {
                InstruktorDaoSQLImpl ins = InstruktorDaoSQLImpl.getInstance();
                ob.add(new ModelInstruktori(ins.getById(el).getNazivInstruktora(), ins.getById(el).getTelefonskiBroj(), ins.getById(el).getCijenaPoCasu()));
            }
            tableview.setItems(ob);
            tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends ModelInstruktori> observableValue, ModelInstruktori oldval, ModelInstruktori newval) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dostupan.fxml"));
                    InstruktorDaoSQLImpl ins = InstruktorDaoSQLImpl.getInstance();
                    instruktor = ins.getByNazivTel(newval.getNaziv(), newval.getTelefon());
                    System.out.println(instruktor);
                    Parent root;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(root, 400, 300);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setTitle("Izabrani instruktor:");
                    stage.getIcons().add(new Image("file:///C:/Users/WIN10/Downloads/book.png"));
                    stage.show();
                }
            });
            tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        }

    public void akcijaDugmetaNazad(ActionEvent actionEvent) throws IOException {
        Node node= (Node) actionEvent.getSource();
        Stage stage= (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/korisnik.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Popunite zahtjeve:");
        stage.setScene(scene);
        stage.show();
    }

    public void akcijaDugmetaPocetak(ActionEvent actionEvent) throws IOException {
        Node node= (Node) actionEvent.getSource();
        Stage stage= (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/odabirwindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Dobro došli!");
        stage.setScene(scene);
        stage.show();
    }
}
