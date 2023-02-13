package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Instruktor;

public interface InstruktorDao extends Dao<Instruktor>{
    Instruktor getByNazivTel(String naziv,String tel);
}
