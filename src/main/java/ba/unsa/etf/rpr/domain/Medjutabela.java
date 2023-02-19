package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Veza izmedju instruktora i predmeta koje predaju
 */
public class Medjutabela {
    private Predmet predmet;
    private Instruktor ins;

    public Medjutabela(Predmet predmet, Instruktor ins) {
        this.predmet = predmet;
        this.ins = ins;
    }

    public Predmet getPredmet() {
        return predmet;
    }
    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
    public Instruktor getIns() {
        return ins;
    }
    public void setIns(Instruktor ins) {
        this.ins = ins;
    }

    @Override
    public String toString() {
        return "Medjutabela{" +
                "predmet=" + predmet +
                ", ins=" + ins +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medjutabela that = (Medjutabela) o;
        return predmet.equals(that.predmet) && ins.equals(that.ins);
    }
    @Override
    public int hashCode() {
        return Objects.hash(predmet, ins);
    }
}
