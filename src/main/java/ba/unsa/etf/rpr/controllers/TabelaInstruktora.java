package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.InstruktorDaoSQLImpl;
import ba.unsa.etf.rpr.dao.MedjutabelaDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PredmetDaoSQLImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

public class TabelaInstruktora {
    @FXML
    private TableView<Model> tableview;
    public TableColumn<Model, String> naziv;
    public TableColumn<Model, String> telefon;
    public TableColumn<Model, Double> cijena;

    @FXML
    public void initialize() {
        SingletonKlasa sk = SingletonKlasa.getInstance();
        PredmetDaoSQLImpl pd = PredmetDaoSQLImpl.getInstance();
        int id = pd.getId(sk.getPredmet(), sk.getNivo());//id predmeta
        MedjutabelaDaoSQLImpl m = new MedjutabelaDaoSQLImpl();
        List<Integer> instruktorIds = m.getbyPredmet(id);
            for (int i = 0; i < instruktorIds.size(); i++) {
                InstruktorDaoSQLImpl ins = InstruktorDaoSQLImpl.getInstance();
                if (!(sk.getGrad().equals(ins.getById(instruktorIds.get(i)).getGrad()))) {
                    instruktorIds.remove(i--);
                }
            }
        ObservableList<Model> ob = FXCollections.observableArrayList();
            this.naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
            this.telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
            this.cijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
            for (Integer el : instruktorIds) {
                InstruktorDaoSQLImpl ins = InstruktorDaoSQLImpl.getInstance();
                ob.add(new Model(ins.getById(el).getNazivInstruktora(), ins.getById(el).getTelefonskiBroj(), ins.getById(el).getCijenaPoCasu()));
            }
            tableview.setItems(ob);
            tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        }
}
