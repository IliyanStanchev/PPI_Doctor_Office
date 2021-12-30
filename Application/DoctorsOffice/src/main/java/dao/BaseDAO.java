package dao;

import manager.MyEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public abstract class BaseDAO<EntityClass extends Serializable> {

    private final EntityManager entityManager = MyEntityManager.getEntityManager();

    private Class<EntityClass> entityClass;

    public final void setClass( Class<EntityClass> entityClass) {
        this.entityClass = entityClass;
    }

    public List<EntityClass> getAll() {

        return entityManager.createQuery("FROM " + entityClass.getName()).getResultList();
    }

    public EntityClass findById(int id) {


        return MyEntityManager.getEntityManager().find(entityClass, id);
    }

    public EntityClass saveOrUpdate(EntityClass entityObject) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {

            entityTransaction.begin();

            if (entityManager.contains(entityObject))
                entityManager.merge(entityObject);

            entityManager.persist(entityObject);
            entityManager.flush();

            entityTransaction.commit();

        } catch (RuntimeException e) {

            entityTransaction.rollback();
            return null;
        }

        return entityObject;
    }

    public void delete(EntityClass entityClass) {

        entityManager.remove(entityClass);
    }

    public void deleteById(int id) {

        EntityClass entityClass = findById(id);

        MyEntityManager.executeInsideTransaction(
                entityManager -> entityManager.remove(
                        entityManager.contains(entityClass) ? entityClass : entityManager.merge(entityClass)));

    }
}
