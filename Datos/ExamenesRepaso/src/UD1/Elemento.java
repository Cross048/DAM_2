package UD1;

import java.util.Objects;

public class Elemento {
    private String Atributo1;
    private int Atributo2;

    public Elemento() {
    }

    public Elemento(String Atributo1, int Atributo2) {
        this.Atributo1 = Atributo1;
        this.Atributo2 = Atributo2;
    }

    public String getAtributo1() {
        return this.Atributo1;
    }

    public void setAtributo1(String Atributo1) {
        this.Atributo1 = Atributo1;
    }

    public int getAtributo2() {
        return this.Atributo2;
    }

    public void setAtributo2(int Atributo2) {
        this.Atributo2 = Atributo2;
    }

    public Elemento Atributo1(String Atributo1) {
        setAtributo1(Atributo1);
        return this;
    }

    public Elemento Atributo2(int Atributo2) {
        setAtributo2(Atributo2);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Elemento)) {
            return false;
        }
        Elemento elemento = (Elemento) o;
        return Objects.equals(Atributo1, elemento.Atributo1) && Atributo2 == elemento.Atributo2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Atributo1, Atributo2);
    }

    @Override
    public String toString() {
        return getAtributo1() + "," + getAtributo2();
    }
}