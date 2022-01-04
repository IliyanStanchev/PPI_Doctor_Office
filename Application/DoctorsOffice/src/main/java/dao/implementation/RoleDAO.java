package dao.implementation;

import dao.BaseDAO;
import entities.Role;
import enums.RoleEnum;
import manager.EntityManagerExtender;

import javax.persistence.NoResultException;

public class RoleDAO extends BaseDAO<Role> {

    public RoleDAO() {

        super.setClass(Role.class);
    }

    public Role getRoleByUID(RoleEnum roleEnum) {

        Role role;
        try {
            role = (Role) EntityManagerExtender.getEntityManager().createQuery("FROM ROLES r WHERE  r.roleUid=: roleEnum ")
                    .setParameter("roleEnum", roleEnum)
                    .getSingleResult();

        } catch (NoResultException e) {
            role = null;
        }

        return role;
    }
}
