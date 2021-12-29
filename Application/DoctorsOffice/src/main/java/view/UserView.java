package view;

import entities.UserAccount;
import enums.RoleEnum;

public class UserView {

    private static final String YES = "Yes";
    private static final String NO  = "No";

    private int id;
    private boolean blocked;
    private String  state;
    private String  role;
    private RoleEnum roleEnum;
    private String  identifier;
    private String  name;

    public UserView( UserAccount userAccount ) {
        this.id         = userAccount.getId();
        this.blocked    = userAccount.isBlocked();
        this.state      = userAccount.isBlocked() ? YES : NO;
        this.role       = userAccount.getUser().getRole().getRoleName();
        this.name       = userAccount.getUser().getFullName();
        this.identifier = userAccount.getUser().getIdentifier();
        this.roleEnum   = userAccount.getUser().getRole().getRoleUid();
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
