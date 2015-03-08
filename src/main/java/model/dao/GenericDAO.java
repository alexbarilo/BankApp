package model.dao;

import java.util.List;

public interface GenericDAO<T> {
    public void saveOrUpdateEntity(T entity);
    public void deleteEntity(T entity);
    public T getEntityByID(Class entityClass, int id);
    public List<T> getAllEntities(Class entityClass);
}
