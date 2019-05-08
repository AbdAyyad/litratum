package com.atypon.training.litratum.model.database.daos.interfaces;

import com.atypon.training.litratum.model.database.datamodel.LicenseModel;

public interface ILicenseDao extends IDao<LicenseModel> {
    LicenseModel getById(String licenseId);
}
