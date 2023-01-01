package ba.unsa.etf.rpr.controllers;

public class Izuzetak extends RuntimeException{
    public Izuzetak(String poruka){
        super(poruka);
    }
}
