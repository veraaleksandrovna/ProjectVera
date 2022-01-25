package Models.Entities;

import java.io.Serializable;

public class Grade implements Serializable {
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

    public boolean checkAppearance() {
        return Appearance;
    }

    public void setAppearance(boolean appearance) {
        Appearance = appearance;
    }

    public boolean checkThickness() {
        return Thickness;

    }

    public void setThickness(boolean thickness) {
        Thickness = thickness;
    }

    public boolean checkSize() {
        return Size;

    }

    public void setSize(boolean size) {
        Size = size;
    }

    public boolean checkWeight() {
        return Weight;

    }

    public void setWeight(boolean weight) {
        Weight = weight;
    }

    public boolean checkStrength() {
        return Strength;

    }

    public void setStrength(boolean strength) {
        Strength = strength;
    }

    public boolean getResult() {
        return Result;

    }
}
