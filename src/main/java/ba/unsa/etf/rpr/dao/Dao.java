package ba.unsa.etf.rpr.dao;

import java.util.List;

/**
 * Interface za sve Dao klase
 * @param <Tip>
 */
public abstract interface  Dao<Tip> {
    /**
     * get entity from database base on ID
     * @param id primary key of entity
     * @return Entity from database
     */
    Tip getById(int id);
    /**
     * Saves entity into database
     * @param element bean for saving to database
     * @return saved item with id field populated
     */
    Tip add(Tip element);
    /**
     * Fully updates entity in database based on id (primary) match.
     * @param element - bean to be updated. id must be populated
     * @return updated version of bean
     */
    Tip update(Tip element);
    /**
     * Hard delete of item from database with given id
     * @param id - primary key of entity
     */
    void delete(int id);
    /**
     * Lists all entities from database. WARNING: Very slow operation because it reads all records.
     * @return List of entities from database
     */
    List<Tip> getAll();
}
