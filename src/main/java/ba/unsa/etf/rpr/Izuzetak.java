package ba.unsa.etf.rpr;

public class Izuzetak extends RuntimeException{
    public Izuzetak(String poruka){
        super(poruka);
    }
}
