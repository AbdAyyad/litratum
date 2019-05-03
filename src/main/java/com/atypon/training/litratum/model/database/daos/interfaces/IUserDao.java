package com.atypon.training.litratum.model.database.daos.interfaces;

import com.atypon.training.litratum.model.database.datamodel.UserModel;

public interface IUserDao extends IDao<UserModel> {
    UserModel getByEmail(String email);

    void update(UserModel user);
}
