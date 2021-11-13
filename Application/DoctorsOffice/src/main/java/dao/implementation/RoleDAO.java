package dao.implementation;

import dao.BaseDAO;
import entities.Role;

public class RoleDAO extends BaseDAO<Role> {

    public RoleDAO() {
        setClass(Role.class);
    }
}
