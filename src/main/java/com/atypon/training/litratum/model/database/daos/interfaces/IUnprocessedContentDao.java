package com.atypon.training.litratum.model.database.daos.interfaces;

import com.atypon.training.litratum.model.database.datamodel.UnprocessedContentModel;

import java.util.List;

public interface IUnprocessedContentDao extends IDao<UnprocessedContentModel> {
    List<UnprocessedContentModel> getAll();

    UnprocessedContentModel getById(String unprocessedContentId);

    void updateStatus(String unprocessedId,int status);

    void delete(String unprocessedId);
}
