package ba.unsa.etf.rpr.controllers;

public final class PrenosPodataka {
    private String predmet, grad, nivo;
    private double cijena;
    private final static PrenosPodataka instance=new PrenosPodataka();
    private PrenosPodataka(){}

    public static PrenosPodataka getInstance() {
        return instance;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String dan) {
        this.grad = dan;
    }

    public String getNivo() {
        return nivo;
    }

    public void setNivo(String nivo) {
        this.nivo = nivo;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }
}
