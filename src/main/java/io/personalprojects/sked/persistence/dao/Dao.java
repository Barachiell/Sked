package io.personalprojects.sked.persistence.dao;

import io.personalprojects.sked.persistence.model.Model;

import java.util.List;

public interface Dao <T extends Model> {

    List<T> findAll();

    T findById(Integer id);

    T saveOrUpdate(T modelObject);

    void delete(Integer id);
}
