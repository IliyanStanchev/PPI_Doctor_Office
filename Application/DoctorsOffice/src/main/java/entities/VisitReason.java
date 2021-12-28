package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "VISIT_REASONS")
public class VisitReason implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "REASON")
    private String reason;

    public VisitReason() {

    }

    public VisitReason(String reason) {
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitReason that = (VisitReason) o;
        return id == that.id && Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reason);
    }

    @Override
    public String toString() {
        return reason;
    }
}
