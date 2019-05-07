package com.atypon.training.litratum.model.database.daos.interfaces;

import com.atypon.training.litratum.model.database.datamodel.ArticleMetaModel;

import java.util.List;

public interface IArticleMetaDao extends IDao<ArticleMetaModel> {
    List<ArticleMetaModel> getAll();
}
