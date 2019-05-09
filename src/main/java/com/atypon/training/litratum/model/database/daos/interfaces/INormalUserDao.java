package com.atypon.training.litratum.model.database.daos.interfaces;

import com.atypon.training.litratum.model.database.datamodel.NormalUserModel;

public interface INormalUserDao extends ISubUserDao<NormalUserModel> {
    void update(String userId, String licenseId);
}
