package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Medjutabela;

import java.util.List;

public interface MedjutabelaDao extends Dao<Medjutabela> {
    List<Integer> getByPredmet(int id);
    void deleteByInstruktor(int instruktorId);
}
