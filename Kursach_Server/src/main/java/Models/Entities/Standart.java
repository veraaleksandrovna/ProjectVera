package Models.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "baseproduct", schema = "kursach", catalog = "")
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

    @Id
    @Column(name = "BaseProduct_ID")
    public int getBaseProductId() {
        return baseProductId;
    }

    public void setBaseProductId(int baseProductId) {
        this.baseProductId = baseProductId;
    }

    @Basic
    @Column(name = "appearance")
    public Integer getAppearance() {
        return appearance;
    }

    public void setAppearance(Integer appearance) {
        this.appearance = appearance;
    }

    @Basic
    @Column(name = "pr_name")
    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    @Basic
    @Column(name = "size")
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Basic
    @Column(name = "strength")
    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    @Basic
    @Column(name = "thickness")
    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    @Basic
    @Column(name = "weight")
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
