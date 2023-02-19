package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Medjutabela;
import ba.unsa.etf.rpr.domain.Predmet;

import java.util.List;

/**
 * Dao interface for Medjutabela domain bea
 */
public interface MedjutabelaDao extends Dao<Medjutabela> {
    List<Integer> getByPredmet(int id);
    List<Predmet> getByInstruktor(int id);
    void deleteByInstruktor(int instruktorId);
    void deleteByBoth(int instruktorId, int predmetId);

}
