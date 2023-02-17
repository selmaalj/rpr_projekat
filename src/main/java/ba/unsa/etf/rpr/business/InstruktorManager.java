package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Izuzetak;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Instruktor;

import java.util.List;

public class InstruktorManager {

    public Instruktor add(Instruktor ins){
        return DaoFactory.instruktorDao().add(ins);
    }

    public void delete(int instruktorId){
        DaoFactory.instruktorDao().delete(instruktorId);
    }

    public Instruktor update(Instruktor ins) throws Izuzetak{
        return DaoFactory.instruktorDao().update(ins);
    }

    public List<Instruktor> getAll(){
        return DaoFactory.instruktorDao().getAll();
    }

    public Instruktor getById(int id) {
        return DaoFactory.instruktorDao().getById(id);
    }
}
