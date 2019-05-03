package com.atypon.training.litratum.model.database.daos.interfaces;

import com.atypon.training.litratum.model.database.datamodel.NormalUserModel;

public interface INormalUser extends ISubUserDao<NormalUserModel> {
    void update(NormalUserModel normalUser);
}
