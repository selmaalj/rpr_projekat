package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Dostupan;
import ba.unsa.etf.rpr.domain.Instruktor;

import java.util.List;

/**
 * Dao interface for Dostupan domain bea
 */
public interface DostupanDao extends Dao<Dostupan> {
    List<Dostupan> getByInstruktor(Instruktor ins);
    void deleteByInstruktor(Instruktor ins);
}
