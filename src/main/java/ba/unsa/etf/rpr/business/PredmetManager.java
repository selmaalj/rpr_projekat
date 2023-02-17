package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Izuzetak;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Predmet;

import java.util.List;

public class PredmetManager {

    public Predmet add(Predmet predmet){
        try{
            return DaoFactory.predmetDao().add(predmet);
        }catch (Izuzetak e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void delete(int predmetId){
        try{
            DaoFactory.predmetDao().delete(predmetId);
        }
        catch(Izuzetak e){
            System.out.println(e.getMessage());
        }
    }

    public Predmet update(Predmet predmet) throws Izuzetak{
        try {
            return DaoFactory.predmetDao().update(predmet);
        }
        catch(Izuzetak e){
            System.out.println(e.getMessage());
        }
        return predmet;
    }

    public List<Predmet> getAll(){
        try{
            return DaoFactory.predmetDao().getAll();
        }
        catch(Izuzetak e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
