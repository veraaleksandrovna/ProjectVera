package Models.Entities;
import java.io.Serializable;
import java.util.Objects;

public class Worker implements Serializable {

    private static final long serialVersionUID = 1L;

    private int workerId;
    private String fio;
    private String prName;
    private String position;
    private String otdel;

    public Worker() { }

    public Worker(int workerId, String fio, String prName, String position, String otdel) {
        this.workerId = workerId;
        this.fio = fio;
        this.prName = prName;
        this.position = position;
        this.otdel = otdel;
    }

    public Worker(int workerId, String prName, String position, String otdel) {
        this.workerId = workerId;
        this.prName = prName;
        this.position = position;
        this.otdel = otdel;
    }


    public Worker(int workerId) {
        this.workerId = workerId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOtdel() {
        return otdel;
    }

    public void setOtdel(String otdel) {
        this.otdel = otdel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker that = (Worker) o;
        return workerId == that.workerId && Objects.equals(fio, that.fio) && Objects.equals(prName, that.prName) && Objects.equals(position, that.position) && Objects.equals(otdel, that.otdel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workerId, fio, prName, position, otdel);
    }
}
