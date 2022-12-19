package ba.unsa.etf.rpr.controllers;

public final class SingletonKlasa { //za prenos podataka
    private String predmet, dan, nivo;
    private double cijena;
    private final static SingletonKlasa instance=new SingletonKlasa();
    private SingletonKlasa(){}

    public static SingletonKlasa getInstance() {
        return instance;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
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
