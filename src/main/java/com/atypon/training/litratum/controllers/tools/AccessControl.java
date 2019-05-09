package com.atypon.training.litratum.controllers.tools;

import com.atypon.training.litratum.model.database.daos.implementations.LicenseCountDao;
import com.atypon.training.litratum.model.database.daos.implementations.LicenseDao;
import com.atypon.training.litratum.model.database.daos.implementations.LicenseSubscriptionDao;
import com.atypon.training.litratum.model.database.daos.implementations.NormalUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.ILicenseDao;
import com.atypon.training.litratum.model.database.daos.interfaces.INormalUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubLicenseDao;
import com.atypon.training.litratum.model.database.datamodel.LicenseCountModel;
import com.atypon.training.litratum.model.database.datamodel.LicenseModel;
import com.atypon.training.litratum.model.database.datamodel.LicenseSubscriptionModel;
import com.atypon.training.litratum.model.database.datamodel.NormalUserModel;

import java.time.LocalDate;

public class AccessControl {
    private AccessControl() {
    }

    public static boolean isAuthorized(String userId) {
        INormalUserDao normalDao = new NormalUserDao();
        ILicenseDao licenseDao = new LicenseDao();

        NormalUserModel normalUserModel = normalDao.getByUserId(userId);
        if (normalUserModel.getLicenseId() == null) {
            return false;
        }
        LicenseModel licenseModel = licenseDao.getById(normalUserModel.getLicenseId());

        int licenseType = licenseModel.getLicenseType();
        switch (licenseType) {
            case 1:
                return licenseCountCheck(licenseModel);
            case 2:
                return licenseSubscriptionCheck(licenseModel);
            default:
                return false;
        }
    }

    private static boolean licenseCountCheck(LicenseModel licenseModel) {
        ISubLicenseDao<LicenseCountModel> dao = new LicenseCountDao();
        LicenseCountModel model = dao.getById(licenseModel.getActualLicenseId());
        int count = model.getCount();
        model.setCount(count - 1);
        dao.update(model);
        return count > 0;
    }

    private static boolean licenseSubscriptionCheck(LicenseModel licenseModel) {
        ISubLicenseDao<LicenseSubscriptionModel> dao = new LicenseSubscriptionDao();
        LicenseSubscriptionModel model = dao.getById(licenseModel.getActualLicenseId());

        String date = model.getEndDate();

        LocalDate localDate = LocalDate.parse(date);
        LocalDate now = LocalDate.now();

        return now.isBefore(localDate);
    }
}
