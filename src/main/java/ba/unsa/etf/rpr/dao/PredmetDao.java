package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Predmet;

/**
 * Dao interface for Predmet domain bea
 */

public interface PredmetDao extends Dao<Predmet>{
    int getId(String predmet, String nivo);
}
