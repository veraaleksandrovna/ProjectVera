package Models.TableEntities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableBatch {
    private SimpleIntegerProperty Id;
    private SimpleStringProperty Date;
    private SimpleStringProperty Worker;
    private SimpleStringProperty User;

    public TableBatch(int id, String date, String worker, String user) {
        Id = new SimpleIntegerProperty(id);
        Date = new SimpleStringProperty(date);
        Worker = new SimpleStringProperty(worker);
        User = new SimpleStringProperty(user);
    }

    public String getDate() {
        return Date.get();
    }

    public SimpleStringProperty dateProperty() {
        return Date;
    }

    public void setDate(String date) {
        this.Date.set(date);
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

    public String getWorker() {
        return Worker.get();
    }

    public SimpleStringProperty workerProperty() {
        return Worker;
    }

    public void setWorker(String worker) {
        this.Worker.set(worker);
    }

    public String getUser() {
        return User.get();
    }

    public SimpleStringProperty userProperty() {
        return User;
    }

    public void setUser(String user) {
        this.User.set(user);
    }
}
