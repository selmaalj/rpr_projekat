package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Izuzetak;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Instruktor;

import java.util.List;
import java.util.Objects;

/**
 * Biznis sloj za Instruktor klasu
 */
public class InstruktorManager {

    public void validateNaziv(String name) throws Izuzetak {
        if (name==null || name.isEmpty() || name.split(" ")[1] == null || name.split(" ")[0].length()<2 || name.split(" ")[1].length()<2)
            throw new Izuzetak("Naziv mora imati i ime i prezime najmanje duzine 2!");
    }

    public Instruktor add(Instruktor ins) {
        validateNaziv(ins.getNazivInstruktora());
        for(Instruktor i: getAll())
            if(Objects.equals(ins.getNazivInstruktora(), i.getNazivInstruktora()) && Objects.equals(ins.getTelefonskiBroj(), i.getTelefonskiBroj()) && Objects.equals(ins.getGrad(), i.getGrad()))
                throw new Izuzetak("Postoji vec u bazi taj instruktor");
        return DaoFactory.instruktorDao().add(ins);
    }

    public void delete(int instruktorId) {
        DaoFactory.instruktorDao().delete(instruktorId);
    }

    public Instruktor update(Instruktor ins) throws Izuzetak {
        validateNaziv(ins.getNazivInstruktora());
        return DaoFactory.instruktorDao().update(ins);
    }

    public List<Instruktor> getAll() {
        return DaoFactory.instruktorDao().getAll();
    }

    public Instruktor getById(int id) {
        return DaoFactory.instruktorDao().getById(id);
    }
}
