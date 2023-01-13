package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.tabele.Dostupan;
import ba.unsa.etf.rpr.tabele.Instruktor;

import java.util.List;

public interface DostupanDao extends Dao<Dostupan> {
    List<Dostupan> getByInstruktor(Instruktor ins);
}
