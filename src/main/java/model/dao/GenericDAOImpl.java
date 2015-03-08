package model.dao;

import org.hibernate.Query;
import util.HibernateUtil;

import java.util.List;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
    @Override
    public void saveOrUpdateEntity(T entity) {
        HibernateUtil.getSession().saveOrUpdate(entity);
    }

    @Override
    public void deleteEntity(T entity) {
        HibernateUtil.getSession().delete(entity);
    }

    @Override
    public List<T> getAllEntities(Class entityClass) {
        Query query = null;
        query = HibernateUtil.getSession().createQuery("from " + entityClass.getName());
        return query.list();
    }

    @Override
    public T getEntityByID(Class entityClass, int id) {
        T entity = (T) HibernateUtil.getSession().get(entityClass, id);
        return entity;
    }
}
