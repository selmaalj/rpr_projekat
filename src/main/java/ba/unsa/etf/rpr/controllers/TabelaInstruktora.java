package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.InstruktorDaoSQLImpl;
import ba.unsa.etf.rpr.dao.MedjutabelaDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PredmetDaoSQLImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private MedjutabelaDaoSQLImpl m;
    private PredmetDaoSQLImpl pd;
    private ObservableList<Model> ob;

    @FXML
    public void initialize() {
        SingletonKlasa sk = SingletonKlasa.getInstance();
        pd = new PredmetDaoSQLImpl();
        int id = pd.getId(sk.getPredmet(), sk.getNivo());//id predmeta
        m = new MedjutabelaDaoSQLImpl();
        List<Integer> instruktorIds = m.getbyPredmet(id);
            for (int i = 0; i < instruktorIds.size(); i++) {
                InstruktorDaoSQLImpl ins = new InstruktorDaoSQLImpl();
                if (!(sk.getGrad().equals(ins.getbyId(instruktorIds.get(i)).getGrad()))) {
                    instruktorIds.remove(i--);
                }
            }
            ob = FXCollections.observableArrayList();
            this.naziv.setCellValueFactory(new PropertyValueFactory<Model, String>("naziv"));
            this.telefon.setCellValueFactory(new PropertyValueFactory<Model, String>("telefon"));
            this.cijena.setCellValueFactory(new PropertyValueFactory<Model, Double>("cijena"));
            for (Integer el : instruktorIds) {
                InstruktorDaoSQLImpl ins = new InstruktorDaoSQLImpl();
                ob.add(new Model(ins.getbyId(el).getNazivInstruktora(), ins.getbyId(el).getTelefonskiBroj(), ins.getbyId(el).getCijenaPoCasu()));
            }
            tableview.setItems(ob);
            tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        }
}
