package ba.unsa.etf.rpr.controllers;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Model {
    private SimpleStringProperty naziv, telefon;
    private SimpleDoubleProperty cijena;
    public Model(){};
    public Model(String naziv, String telefon, Double cijena) {
        this.naziv = new SimpleStringProperty(naziv);
        this.telefon = new SimpleStringProperty(telefon);
        this.cijena = new SimpleDoubleProperty(cijena);
    }
    public String getNaziv() {
        return naziv.get();
    }

    public SimpleStringProperty nazivProperty() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv.set(naziv);
    }

    public String getTelefon() {
        return telefon.get();
    }

    public SimpleStringProperty telefonProperty() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }

    public double getCijena() {
        return cijena.get();
    }

    public SimpleDoubleProperty cijenaProperty() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena.set(cijena);
    }
}
