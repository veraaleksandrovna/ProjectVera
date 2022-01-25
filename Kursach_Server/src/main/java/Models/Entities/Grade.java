package Models.Entities;

import java.io.Serializable;

public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean Strength;
    private boolean Weight;
    private boolean Size;
    private boolean Thickness;
    private boolean Appearance;
    private boolean Result;

    public Grade(boolean strength, boolean weight, boolean size, boolean thickness, boolean appearance) {
        Strength = strength;
        Weight = weight;
        Size = size;
        Thickness = thickness;
        Appearance = appearance;
    }

    public Grade() {

    }

    public boolean checkAppearance(int Standart, int Produce) {
        if ((double) Produce / (double) Standart >= 1)
            return true;
        else return false;
    }

    public void setAppearance(boolean appearance) {
        Appearance = appearance;
    }

    public boolean checkThickness(int Standart, int Produce) {
        if ((double) Produce / (double) Standart >= 1)
            return true;
        else return false;
    }

    public void setThickness(boolean thickness) {
        Thickness = thickness;
    }

    public boolean checkSize(int Standart, int Produce) {
        if ((double) Produce / (double) Standart >= 1)
            return true;
        else return false;
    }

    public void setSize(boolean size) {
        Size = size;
    }

    public boolean checkWeight(int Standart, int Produce) {
        if ((double) Produce / (double) Standart >= 1)
            return true;
        else return false;
    }

    public void setWeight(boolean weight) {
        Weight = weight;
    }

    public boolean checkStrength(int Standart, int Produce) {
        if ((double) Produce / (double) Standart >= 1)
            return true;
        else return false;
    }

    public void setStrength(boolean strength) {
        Strength = strength;
    }

    public void getResult() {
        if (Strength == false || Weight == false || Size == false || Thickness == false || Appearance == false) {
            Result=false;
        }
        else {
            Result=true;
        }
    }
}
