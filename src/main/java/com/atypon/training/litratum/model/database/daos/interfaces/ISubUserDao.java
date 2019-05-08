package com.atypon.training.litratum.model.database.daos.interfaces;

import java.util.List;

public interface ISubUserDao<T> extends IDao<T> {
    T getByUserId(String userId);
    List<T> selectAll();
}
