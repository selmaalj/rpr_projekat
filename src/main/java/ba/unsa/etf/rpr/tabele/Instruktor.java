package ba.unsa.etf.rpr.tabele;

import java.util.Objects;

public class Instruktor {
    private int idInstruktor;
    private String nazivInstruktora;
    private String telefonskiBroj;
    private Double cijenaPoCasu;

    public int getIdInstruktor() {
        return idInstruktor;
    }

    public void setIdInstruktor(int idInstruktor) {
        this.idInstruktor = idInstruktor;
    }

    public String getNazivInstruktora() {
        return nazivInstruktora;
    }

    public void setNazivInstruktora(String nazivInstruktora) {
        this.nazivInstruktora = nazivInstruktora;
    }

    public String getTelefonskiBroj() {
        return telefonskiBroj;
    }

    public void setTelefonskiBroj(String telefonskiBroj) {
        this.telefonskiBroj = telefonskiBroj;
    }

    public Double getCijenaPoCasu() {
        return cijenaPoCasu;
    }

    public void setCijenaPoCasu(Double cijenaPoCasu) {
        this.cijenaPoCasu = cijenaPoCasu;
    }
    @Override
    public String toString() {
        return "Instruktor{" +
                "idInstruktor=" + idInstruktor +
                ", nazivInstruktora='" + nazivInstruktora + '\'' +
                ", telefonskiBroj='" + telefonskiBroj + '\'' +
                ", cijenaPoCasu=" + cijenaPoCasu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruktor instruktor = (Instruktor) o;
        return idInstruktor == instruktor.idInstruktor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInstruktor, nazivInstruktora, telefonskiBroj, cijenaPoCasu);
    }
}
