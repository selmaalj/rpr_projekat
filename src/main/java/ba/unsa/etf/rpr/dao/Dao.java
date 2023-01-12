package ba.unsa.etf.rpr.dao;

import java.util.List;

public abstract interface  Dao<Tip> {
    Tip getById(int id);
    Tip add(Tip element);
    Tip update(Tip element);
    void delete(int id);
    List<Tip> getAll();
}
