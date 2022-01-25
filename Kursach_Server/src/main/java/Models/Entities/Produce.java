package Models.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "kursach", catalog = "")
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

    public Produce(int productId,String prName, int appearance, int size, int strength, int thickness, int weight) {
        this.productId = productId;
        this.appearance = appearance;
        this.prName = prName;
        this.size = size;
        this.strength = strength;
        this.thickness = thickness;
        this.weight = weight;
    }

    public Produce(int productId) {
        this.productId = productId;
    }

    @Id
    @Column(name = "ProductID")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
    @Column(name = "appearance")
    public Integer getAppearance() {
        return appearance;
    }

    public void setAppearance(Integer appearance) {
        this.appearance = appearance;
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

    @ManyToOne
    @JoinColumn(name = "Series_SeriesID")
    public Batch getSeriesSeriesId() {
        return seriesSeriesId;
    }

    public void setSeriesSeriesId(Batch seriesSeriesId) {
        this.seriesSeriesId = seriesSeriesId;
    }

    @ManyToOne
    @JoinColumn(name = "BaseProducts_BaseProductID")
    public Standart getBaseProductsBaseProductId() {
        return baseProductsBaseProductId;
    }

    public void setBaseProductsBaseProductId(Standart baseProductsBaseProductId) {
        this.baseProductsBaseProductId = baseProductsBaseProductId;
    }

    @ManyToOne
    @JoinColumn(name = "Series_Workers_workerID")
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
}
