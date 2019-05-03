package com.atypon.training.litratum.model.database.daos.interfaces;

public interface ISubLicenseDao<T> extends IDao<T> {
    T getById(String id);

    void update(T license);
}
