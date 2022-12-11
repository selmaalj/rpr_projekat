package ba.unsa.etf.rpr.tabele;

import java.util.Objects;

public class Predmet {
    private int id;
    private String nazivPredmeta;
    private String nivoSkolovanja;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public String getNivoSkolovanja() {
        return nivoSkolovanja;
    }

    public void setNivoSkolovanja(String nivoSkolovanja) {
        this.nivoSkolovanja = nivoSkolovanja;
    }

    @Override
    public String toString() {
        return "Predmet{" +
                "idPredmet=" + id +
                ", naziv_predmeta='" + nazivPredmeta + '\'' +
                ", nivo_skolovanja='" + nivoSkolovanja + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Predmet predmet = (Predmet) o;
        return id == predmet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazivPredmeta, nivoSkolovanja);
    }
}
