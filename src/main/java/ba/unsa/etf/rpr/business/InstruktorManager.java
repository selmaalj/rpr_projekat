package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Izuzetak;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Instruktor;

import java.util.List;

public class InstruktorManager {

    public Instruktor add(Instruktor ins){
        try{
            return DaoFactory.instruktorDao().add(ins);
        }catch (Izuzetak e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void delete(int instruktorId){
        try{
            DaoFactory.instruktorDao().delete(instruktorId);
        }
           catch(Izuzetak e){
               System.out.println(e.getMessage());
           }
    }

    public Instruktor update(Instruktor ins) throws Izuzetak{
        try {
            return DaoFactory.instruktorDao().update(ins);
        }
        catch(Izuzetak e){
            System.out.println(e.getMessage());
        }
        return ins;
    }

    public List<Instruktor> getAll(){
        try{
            return DaoFactory.instruktorDao().getAll();
        }
        catch(Izuzetak e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
