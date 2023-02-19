package ba.unsa.etf.rpr.domain;

/**
 * Interface koji forsira sve POJO beans da imaju polje id
 */
public interface Idable {
    void setId(int id);
    int getId();
}
