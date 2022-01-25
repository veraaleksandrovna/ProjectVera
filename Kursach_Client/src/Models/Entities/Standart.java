package Models.Entities;

import java.io.Serializable;
import java.util.Objects;

public class Standart implements Serializable {

    private static final long serialVersionUID = 1L;

    private int baseProductId;
    private Integer appearance;
    private String prName;
    private Integer size;
    private Integer strength;
    private Integer thickness;
    private Integer weight;

    public Standart() { }

    public Standart(int baseProductId, int appearance, String prName, int size, int strength, int thickness, int weight) {
        this.baseProductId = baseProductId;
        this.appearance = appearance;
        this.prName = prName;
        this.size = size;
        this.strength = strength;
        this.thickness = thickness;
        this.weight = weight;
    }

    public Standart(int baseProductId) {
        this.baseProductId = baseProductId;
    }

    public int getBaseProductId() {
        return baseProductId;
    }

    public void setBaseProductId(int baseProductId) {
        this.baseProductId = baseProductId;
    }

    public Integer getAppearance() {
        return appearance;
    }

    public void setAppearance(Integer appearance) {
        this.appearance = appearance;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Standart that = (Standart) o;
        return baseProductId == that.baseProductId && Objects.equals(appearance, that.appearance) && Objects.equals(prName, that.prName) && Objects.equals(size, that.size) && Objects.equals(strength, that.strength) && Objects.equals(thickness, that.thickness) && Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseProductId, appearance, prName, size, strength, thickness, weight);
    }
}
