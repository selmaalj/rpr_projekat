package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Izuzetak;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Instruktor;
import ba.unsa.etf.rpr.domain.Predmet;

import java.util.List;
import java.util.Objects;

/**
 * Biznis sloj za klasu Predmet
 */
public class PredmetManager {

    public void validateNaziv(String naziv) throws Izuzetak {
        if (naziv == null || naziv.length() < 2)
            throw new Izuzetak("Naziv mora biti duzine 2 minimum!");
    }
    public Predmet add(Predmet predmet){
        validateNaziv(predmet.getNazivPredmeta());
        for(Predmet p: getAll())
            if(Objects.equals(predmet.getNazivPredmeta(), p.getNazivPredmeta()) && Objects.equals(predmet.getNivoSkolovanja(), p.getNivoSkolovanja()))
                throw new Izuzetak("Postoji vec u bazi taj predmet");
        return DaoFactory.predmetDao().add(predmet);
    }

    public void delete(int predmetId){
        DaoFactory.predmetDao().delete(predmetId);
    }

    public Predmet update(Predmet predmet) throws Izuzetak{
        validateNaziv(predmet.getNazivPredmeta());
        return DaoFactory.predmetDao().update(predmet);
    }

    public List<Predmet> getAll(){
        return DaoFactory.predmetDao().getAll();
    }

    public Predmet getById(int id) {
        return DaoFactory.predmetDao().getById(id);
    }
}
