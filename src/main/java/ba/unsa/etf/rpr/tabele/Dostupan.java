package ba.unsa.etf.rpr.tabele;

import java.util.Objects;

public class Dostupan {
    private int id;
    private String dan;
    private Instruktor ins;

    public Dostupan(int id, String dan, Instruktor ins) {
        this.id=id;
        this.dan=dan;
        this.ins=ins;
    }
    public Dostupan(){};
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
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
                ", dan=" + dan +
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
        return Objects.hash(id,dan,ins);
    }
}
