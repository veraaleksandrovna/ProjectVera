package Models.Entities;

import java.io.Serializable;
import java.util.Objects;

public class Produce implements Serializable {

    private static final long serialVersionUID = 1L;

    private int productId;
    private String prName;
    private Integer appearance;
    private Integer size;
    private Integer strength;
    private Integer thickness;
    private Integer weight;
    private Batch seriesSeriesId;
    private Standart baseProductsBaseProductId;
    private Worker seriesWorkersWorkerId;

    public Produce() { }

    public Produce(int productId, int appearance, int size, int strength, int thickness, int weight, String prName) {
        this.productId = productId;
        this.prName = prName;
        this.appearance = appearance;
        this.size = size;
        this.strength = strength;
        this.thickness = thickness;
        this.weight = weight;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public Produce(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getAppearance() {
        return appearance;
    }

    public void setAppearance(Integer appearance) {
        this.appearance = appearance;
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

    public Batch getSeriesSeriesId() {
        return seriesSeriesId;
    }

    public void setSeriesSeriesId(Batch seriesSeriesId) {
        this.seriesSeriesId = seriesSeriesId;
    }

    public Standart getBaseProductsBaseProductId() {
        return baseProductsBaseProductId;
    }

    public void setBaseProductsBaseProductId(Standart baseProductsBaseProductId) {
        this.baseProductsBaseProductId = baseProductsBaseProductId;
    }

    public Worker getSeriesWorkersWorkerId() {
        return seriesWorkersWorkerId;
    }

    public void setSeriesWorkersWorkerId(Worker seriesWorkersWorkerId) {
        this.seriesWorkersWorkerId = seriesWorkersWorkerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produce that = (Produce) o;
        return productId == that.productId && Objects.equals(appearance, that.appearance) && Objects.equals(size, that.size) && Objects.equals(strength, that.strength) && Objects.equals(thickness, that.thickness) && Objects.equals(weight, that.weight) && Objects.equals(seriesSeriesId, that.seriesSeriesId) && Objects.equals(baseProductsBaseProductId, that.baseProductsBaseProductId) && Objects.equals(seriesWorkersWorkerId, that.seriesWorkersWorkerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, appearance, size, strength, thickness, weight, seriesSeriesId, baseProductsBaseProductId, seriesWorkersWorkerId);
    }

    public void setName(String name) {
        this.baseProductsBaseProductId.setPrName(name);
    }
}
