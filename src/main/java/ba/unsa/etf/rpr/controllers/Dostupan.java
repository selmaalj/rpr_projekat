package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DostupanDaoSQLImpl;
import ba.unsa.etf.rpr.tabele.Instruktor;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class Dostupan {
    public TextField naziv, telefon;
    public TableView<ModelDani> table;
    public TableColumn<ModelDani, String> pon,ut,sr,cet,pet,sub,ned;
    private Instruktor ins;
    @FXML
    public void initialize() throws IOException {
        ins=TabelaInstruktora.instruktor;
        naziv.setText(ins.getNazivInstruktora());
        telefon.setText(ins.getTelefonskiBroj());
        ObservableList<ModelDani> ob = FXCollections.observableArrayList();
        pon.setCellValueFactory(new PropertyValueFactory<>("ponedjeljak"));
        ut.setCellValueFactory(new PropertyValueFactory<>("utorak"));
        sr.setCellValueFactory(new PropertyValueFactory<>("srijeda"));
        cet.setCellValueFactory(new PropertyValueFactory<>("cetvrtak"));
        pet.setCellValueFactory(new PropertyValueFactory<>("petak"));
        sub.setCellValueFactory(new PropertyValueFactory<>("subota"));
        ned.setCellValueFactory(new PropertyValueFactory<>("nedjelja"));
        SimpleStringProperty[] yesno=new SimpleStringProperty[7];
        for(int i=0; i<7; i++) yesno[i]=new SimpleStringProperty("NE");
        List<ba.unsa.etf.rpr.tabele.Dostupan> l=DostupanDaoSQLImpl.getInstance().getByInstruktor(ins);
        for(ba.unsa.etf.rpr.tabele.Dostupan d:l){
            if(d.getDan().equalsIgnoreCase("Ponedjeljak")) yesno[0].setValue("DA");
            if(d.getDan().equalsIgnoreCase("Utorak")) yesno[1].setValue("DA");
            if(d.getDan().equalsIgnoreCase("Srijeda")) yesno[2].setValue("DA");
            if(d.getDan().equalsIgnoreCase("Cetvrtak")) yesno[3].setValue("DA");
            if(d.getDan().equalsIgnoreCase("Petak")) yesno[4].setValue("DA");
            if(d.getDan().equalsIgnoreCase("Subota")) yesno[5].setValue("DA");
            if(d.getDan().equalsIgnoreCase("Nedjelja")) yesno[6].setValue("DA");
        }
        ob.add(new ModelDani(yesno));
        table.setItems(ob);
        table.refresh();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
