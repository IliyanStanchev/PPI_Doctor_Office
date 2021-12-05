package entities;

import enums.RoleEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column( name = "role_name", unique = true )
    private String roleName;

    @Column( name = "role_uid", unique = true )
    private RoleEnum roleUid;

    public Role() {
    }

    public Role(String roleName, RoleEnum roleUid) {
        this.roleName = roleName;
        this.roleUid = roleUid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleEnum getRoleUid() {
        return roleUid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getId() == role.getId() && Objects.equals(getRoleName(), role.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoleName());
    }
}
