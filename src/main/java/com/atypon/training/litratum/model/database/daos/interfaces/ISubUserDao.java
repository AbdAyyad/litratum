package com.atypon.training.litratum.model.database.daos.interfaces;

public interface ISubUserDao<T> extends IDao<T> {
    T getByUserId(String userId);
}
