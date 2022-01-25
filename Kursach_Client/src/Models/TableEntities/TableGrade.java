package Models.TableEntities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableGrade {
    private SimpleIntegerProperty Id;
    private SimpleStringProperty Name;
    private SimpleStringProperty Strength;
    private SimpleStringProperty Weight;
    private SimpleStringProperty Size;
    private SimpleStringProperty Thickness;
    private SimpleStringProperty Appearance;
    private SimpleStringProperty Result;

    public TableGrade(int id, String name, String strength, String weight, String size, String thickness, String appearance, String result) {
        Id = new SimpleIntegerProperty(id);
        Name = new SimpleStringProperty(name);
        Strength = new SimpleStringProperty(strength);
        Weight = new SimpleStringProperty(weight);
        Size = new SimpleStringProperty(size);
        Thickness = new SimpleStringProperty(thickness);
        Appearance = new SimpleStringProperty(appearance);
        Result = new SimpleStringProperty(result);
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

    public String getStrength() {
        return Strength.get();
    }

    public SimpleStringProperty strengthProperty() {
        return Strength;
    }

    public void setStrength(String strength) {
        this.Strength.set(strength);
    }

    public String getWeight() {
        return Weight.get();
    }

    public SimpleStringProperty weightProperty() {
        return Weight;
    }

    public void setWeight(String weight) {
        this.Weight.set(weight);
    }

    public String getSize() {
        return Size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return Size;
    }

    public void setSize(String size) {
        this.Size.set(size);
    }

    public String getThickness() {
        return Thickness.get();
    }

    public SimpleStringProperty thicknessProperty() {
        return Thickness;
    }

    public void setThickness(String thickness) {
        this.Thickness.set(thickness);
    }

    public String getAppearance() {
        return Appearance.get();
    }

    public SimpleStringProperty appearanceProperty() {
        return Appearance;
    }

    public void setAppearance(String appearance) {
        this.Appearance.set(appearance);
    }

    public String getResult() {
        return Result.get();
    }

    public SimpleStringProperty resultProperty() {
        return Result;
    }

    public void setResult(String result) {
        this.Result.set(result);
    }
}
