package ba.unsa.etf.rpr.tabele;

import java.util.Objects;

public class Predmet {
    private int idPredmet;
    private String naziv_predmeta;
    private String nivo_skolovanja;

    public int getIdPredmet() {
        return idPredmet;
    }

    public void setIdPredmet(int idPredmet) {
        this.idPredmet = idPredmet;
    }

    public String getNaziv_predmeta() {
        return naziv_predmeta;
    }

    public void setNaziv_predmeta(String naziv_predmeta) {
        this.naziv_predmeta = naziv_predmeta;
    }

    public String getNivo_skolovanja() {
        return nivo_skolovanja;
    }

    public void setNivo_skolovanja(String nivo_skolovanja) {
        this.nivo_skolovanja = nivo_skolovanja;
    }

    @Override
    public String toString() {
        return "Predmet{" +
                "idPredmet=" + idPredmet +
                ", naziv_predmeta='" + naziv_predmeta + '\'' +
                ", nivo_skolovanja='" + nivo_skolovanja + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Predmet predmet = (Predmet) o;
        return idPredmet == predmet.idPredmet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPredmet, naziv_predmeta, nivo_skolovanja);
    }
}
