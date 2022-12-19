package ba.unsa.etf.rpr.controllers;
import ba.unsa.etf.rpr.dao.InstruktorDaoSQLImpl;
import ba.unsa.etf.rpr.dao.MedjutabelaDao;
import ba.unsa.etf.rpr.dao.MedjutabelaDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PredmetDaoSQLImpl;
import ba.unsa.etf.rpr.tabele.Dostupan;
import ba.unsa.etf.rpr.tabele.Instruktor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class SecondWindowController {
    @FXML
    private TableView<Model> tableview;
    public TableColumn<Model,String> naziv;
    public TableColumn<Model,String> telefon;
    public TableColumn<Model,Double> cijena;
    private MedjutabelaDaoSQLImpl m;
    private ObservableList<Model> ob;


    @FXML
    public void initialize(){
        SingletonKlasa sk=SingletonKlasa.getInstance();
        PredmetDaoSQLImpl pd=new PredmetDaoSQLImpl();
        int id=pd.getId(sk.getPredmet(),sk.getNivo());

        if(id==-1)
            throw new RuntimeException("Nema predmeta");

        m=new MedjutabelaDaoSQLImpl();
        List<Integer> instruktorIds=m.getbyPredmet(id);

        if(instruktorIds.isEmpty()){
            throw new RuntimeException("nemamo ponudu "); }

        ob = FXCollections.observableArrayList();
        this.naziv.setCellValueFactory(new PropertyValueFactory<Model, String>("naziv"));
        this.telefon.setCellValueFactory(new PropertyValueFactory<Model,String>("telefon"));
        this.cijena.setCellValueFactory(new PropertyValueFactory<Model,Double>("cijena"));

        for(Integer el: instruktorIds) {
            InstruktorDaoSQLImpl ins=new InstruktorDaoSQLImpl();
            ob.add(new Model(ins.getbyId(el).getNazivInstruktora(), ins.getbyId(el).getTelefonskiBroj(), ins.getbyId(el).getCijenaPoCasu()));
        }
        tableview.setItems(ob);
        tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
