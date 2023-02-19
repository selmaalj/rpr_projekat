package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Izuzetak;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Predmet;

import java.util.List;

/**
 * Biznis sloj za klasu Predmet
 */
public class PredmetManager {

    public Predmet add(Predmet predmet){
        return DaoFactory.predmetDao().add(predmet);
    }

    public void delete(int predmetId){
        DaoFactory.predmetDao().delete(predmetId);
    }

    public Predmet update(Predmet predmet) throws Izuzetak{
        return DaoFactory.predmetDao().update(predmet);
    }

    public List<Predmet> getAll(){
        return DaoFactory.predmetDao().getAll();
    }

    public Predmet getById(int id) {
        return DaoFactory.predmetDao().getById(id);
    }
}
