package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.tabele.Medjutabela;

import java.util.List;

public interface MedjutabelaDao extends Dao<Medjutabela> {
    List<Integer> getbyPredmet(int id);
}
