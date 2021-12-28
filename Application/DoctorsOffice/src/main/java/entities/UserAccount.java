package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "USER_ACCOUNTS")
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToOne
    private User user;

    @Column(name = "BLOCKED")
    private boolean blocked;

    public UserAccount() {

    }

    public UserAccount(User user, boolean blocked) {
        this.user = user;
        this.blocked = blocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return id == that.id && blocked == that.blocked && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, blocked);
    }
}
