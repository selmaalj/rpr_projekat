package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Medjutabela;
import ba.unsa.etf.rpr.domain.Predmet;

import java.util.List;

public interface MedjutabelaDao extends Dao<Medjutabela> {
    List<Integer> getByPredmet(int id);
    List<Predmet> getByInstruktor(int id);
    void deleteByInstruktor(int instruktorId);
    void deleteByBoth(int instruktorId, int predmetId);
    boolean postoji(int instruktorId, int predmetId);
}
