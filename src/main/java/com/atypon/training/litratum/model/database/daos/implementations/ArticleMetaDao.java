package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.IDao;
import com.atypon.training.litratum.model.database.datamodel.ArticleMetaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArticleMetaDao implements IDao<ArticleMetaModel> {
    @Override
    public void add(ArticleMetaModel o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "insert into article_meta_table( author, doi, date, title) VALUES (?,?,?,?);";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, o.getAuthor());
            statement.setString(2, o.getDoi());
            statement.setString(3, o.getReleaseDate());
            statement.setString(4, o.getTitle());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
