package ba.unsa.etf.rpr.controllers;
import javafx.beans.property.SimpleStringProperty;

public class ModelDani {
    private SimpleStringProperty ponedjeljak, utorak, srijeda, cetvrtak, petak, subota, nedjelja;

    public ModelDani() {
    }
    public ModelDani(SimpleStringProperty[] yesno) {
        ponedjeljak = yesno[0];
        utorak = yesno[1];
        srijeda = yesno[2];
        cetvrtak = yesno[3];
        petak = yesno[4];
        subota = yesno[5];
        nedjelja = yesno[6];
    }

    public String getPonedjeljak() {
        return ponedjeljak.get();
    }

    public SimpleStringProperty ponedjeljakProperty() {
        return ponedjeljak;
    }

    public void setPonedjeljak(String ponedjeljak) {
        this.ponedjeljak.set(ponedjeljak);
    }

    public String getUtorak() {
        return utorak.get();
    }

    public SimpleStringProperty utorakProperty() {
        return utorak;
    }

    public void setUtorak(String utorak) {
        this.utorak.set(utorak);
    }

    public String getSrijeda() {
        return srijeda.get();
    }

    public SimpleStringProperty srijedaProperty() {
        return srijeda;
    }

    public void setSrijeda(String srijeda) {
        this.srijeda.set(srijeda);
    }

    public String getCetvrtak() {
        return cetvrtak.get();
    }

    public SimpleStringProperty cetvrtakProperty() {
        return cetvrtak;
    }

    public void setCetvrtak(String cetvrtak) {
        this.cetvrtak.set(cetvrtak);
    }

    public String getPetak() {
        return petak.get();
    }

    public SimpleStringProperty petakProperty() {
        return petak;
    }

    public void setPetak(String petak) {
        this.petak.set(petak);
    }

    public String getSubota() {
        return subota.get();
    }

    public SimpleStringProperty subotaProperty() {
        return subota;
    }

    public void setSubota(String subota) {
        this.subota.set(subota);
    }

    public String getNedjelja() {
        return nedjelja.get();
    }

    public SimpleStringProperty nedjeljaProperty() {
        return nedjelja;
    }

    public void setNedjelja(String nedjelja) {
        this.nedjelja.set(nedjelja);
    }
}
