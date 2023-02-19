package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Instruktor;

/**
 * Dao interface for Instruktor domain bea
 */
public interface InstruktorDao extends Dao<Instruktor>{
    Instruktor getByNazivTel(String naziv,String tel);
}
