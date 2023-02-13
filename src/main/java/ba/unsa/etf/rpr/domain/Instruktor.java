package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Instruktor implements Idable{
    private int id;
    private String nazivInstruktora;
    private String telefonskiBroj;
    private Double cijenaPoCasu;
    private String grad;

    public Instruktor(int id, String nazivInstruktora, String telefonskiBroj, Double cijenaPoCasu, String grad) {
        this.id = id;
        this.nazivInstruktora = nazivInstruktora;
        this.telefonskiBroj = telefonskiBroj;
        this.cijenaPoCasu = cijenaPoCasu;
        this.grad = grad;
    }
    public Instruktor() {};
    public String getGrad() {
        return grad;
    }
    public void setGrad(String grad) {
        this.grad = grad;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
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
        return id == instruktor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazivInstruktora, telefonskiBroj, cijenaPoCasu);
    }
}
