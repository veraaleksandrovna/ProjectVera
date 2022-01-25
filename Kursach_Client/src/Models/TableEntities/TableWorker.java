package Models.TableEntities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableWorker {
    private SimpleIntegerProperty Id;
    private SimpleStringProperty Name;
    private SimpleStringProperty Position;
    private SimpleStringProperty Department;
    public TableWorker(int id, String name, String position, String department) {
        Id = new SimpleIntegerProperty(id);
        Name = new SimpleStringProperty(name);
        Position = new SimpleStringProperty(position);
        Department = new SimpleStringProperty(department);
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

    public String getName() {
        return Name.get();
    }

    public SimpleStringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public String getPosition() {
        return Position.get();
    }

    public SimpleStringProperty positionProperty() {
        return Position;
    }

    public void setPosition(String position) {
        this.Position.set(position);
    }

    public String getDepartment() {
        return Department.get();
    }

    public SimpleStringProperty departmentProperty() {
        return Department;
    }

    public void setDepartment(String department) {
        this.Department.set(department);
    }

}
