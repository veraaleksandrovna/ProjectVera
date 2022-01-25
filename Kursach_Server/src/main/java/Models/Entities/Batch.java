package Models.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "seria", schema = "kursach", catalog = "")
public class Batch implements Serializable {

    private static final long serialVersionUID = 1L;

    private int seriaId;
    private Date date;
    private User usersUserId;
    private Worker workersWorkerId;

    public Batch() { }

    public Batch(int seriaId, Date date, User usersUserId, Worker workersWorkerId) {
        this.seriaId = seriaId;
        this.date = date;
        this.usersUserId = usersUserId;
        this.workersWorkerId = workersWorkerId;
    }

    public Batch(int seriaId) {
        this.seriaId = seriaId;
    }

    @Id
    @Column(name = "SeriaID")
    public int getSeriaId() {
        return seriaId;
    }

    public void setSeriaId(int seriaId) {
        this.seriaId = seriaId;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "Users_UserID")
    public User getUsersUserId() {
        return usersUserId;
    }

    public void setUsersUserId(User usersUserId) {
        this.usersUserId = usersUserId;
    }

    @ManyToOne
    @JoinColumn(name = "Workers_workerID")
    public Worker getWorkersWorkerId() {
        return workersWorkerId;
    }

    public void setWorkersWorkerId(Worker workersWorkerId) {
        this.workersWorkerId = workersWorkerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Batch that = (Batch) o;
        return seriaId == that.seriaId && Objects.equals(date, that.date) && Objects.equals(usersUserId, that.usersUserId) && Objects.equals(workersWorkerId, that.workersWorkerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seriaId, date, usersUserId, workersWorkerId);
    }
}
