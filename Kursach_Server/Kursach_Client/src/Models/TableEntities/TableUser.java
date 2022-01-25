package Models.TableEntities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableUser {
    private SimpleIntegerProperty Id;
    private SimpleStringProperty Name;
    private SimpleStringProperty Password;
    private SimpleStringProperty Role;
    public TableUser(int id, String name, String password, String role) {
        Id = new SimpleIntegerProperty(id);
        Name = new SimpleStringProperty(name);
        Password = new SimpleStringProperty(password);
        Role = new SimpleStringProperty(role);
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

    public String getPassword() {
        return Password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password.set(password);
    }

    public String getRole() {
        return Role.get();
    }

    public SimpleStringProperty roleProperty() {
        return Role;
    }

    public void setRole(String role) {
        this.Role.set(role);
    }
}
