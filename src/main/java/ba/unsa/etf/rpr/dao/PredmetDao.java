package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.tabele.Predmet;


public interface PredmetDao extends Dao<Predmet>{
    int getId(String predmet, String nivo);
}
