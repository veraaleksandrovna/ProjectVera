package Models.TableEntities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableStandart {

    private SimpleIntegerProperty Id;
    private SimpleStringProperty Name;
    private SimpleIntegerProperty Strength;
    private SimpleIntegerProperty Weight;
    private SimpleIntegerProperty Size;
    private SimpleIntegerProperty Thickness;
    private SimpleIntegerProperty Appearance;

    public TableStandart(int id, String name, int strength, int weight, int size, int thickness, int appearance) {
        Id = new SimpleIntegerProperty(id);
        Name = new SimpleStringProperty(name);
        Strength = new SimpleIntegerProperty(strength);
        Weight = new SimpleIntegerProperty(weight);
        Size = new SimpleIntegerProperty(size);
        Thickness = new SimpleIntegerProperty(thickness);
        Appearance = new SimpleIntegerProperty(appearance);
    }

    public String getName() {
        return Name.get();
    }

    public SimpleStringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public int getId() {
        return Id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return Id;
    }

    public void setId(int id) {
        this.Id.set(id);
    }

    public int getStrength() {
        return Strength.get();
    }

    public SimpleIntegerProperty strengthProperty() {
        return Strength;
    }

    public void setStrength(int strength) {
        this.Strength.set(strength);
    }

    public int getWeight() {
        return Weight.get();
    }

    public SimpleIntegerProperty weightProperty() {
        return Weight;
    }

    public void setWeight(int weight) {
        this.Weight.set(weight);
    }

    public int getSize() {
        return Size.get();
    }

    public SimpleIntegerProperty sizeProperty() {
        return Size;
    }

    public void setSize(int size) {
        this.Size.set(size);
    }

    public int getThickness() {
        return Thickness.get();
    }

    public SimpleIntegerProperty thicknessProperty() {
        return Thickness;
    }

    public void setThickness(int thickness) {
        this.Thickness.set(thickness);
    }

    public int getAppearance() {
        return Appearance.get();
    }

    public SimpleIntegerProperty appearanceProperty() {
        return Appearance;
    }

    public void setAppearance(int appearance) {
        this.Appearance.set(appearance);
    }
}
