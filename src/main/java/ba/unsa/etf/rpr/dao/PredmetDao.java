package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.tabele.Predmet;

import java.util.List;

public interface PredmetDao extends Dao<Predmet>{
    public List<Integer> getIds(Predmet p);
}
