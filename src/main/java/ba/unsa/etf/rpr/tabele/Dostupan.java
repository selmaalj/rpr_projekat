package ba.unsa.etf.rpr.tabele;

import java.util.Objects;

public class Dostupan {
    private int id;
    private Instruktor ins;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instruktor getIns() {
        return ins;
    }

    public void setIns(Instruktor ins) {
        this.ins = ins;
    }

    @Override
    public String toString() {
        return "Dostupan{" +
                "id=" + id +
                ", ins=" + ins +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dostupan dostupan = (Dostupan) o;
        return id == dostupan.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, ins);
    }
}