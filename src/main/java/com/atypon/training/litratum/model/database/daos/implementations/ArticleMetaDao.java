package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.IArticleMetaDao;
import com.atypon.training.litratum.model.database.datamodel.ArticleMetaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleMetaDao implements IArticleMetaDao {
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

    @Override
    public List<ArticleMetaModel> getAll() {
        List<ArticleMetaModel> data = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "select * from article_meta_table";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ArticleMetaModel model = generateObject(result);
                data.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void setUnprocessedId(String doi, String unprocessedId) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "update article_meta_table set unprocessed_id  = ? where doi = ?;";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, unprocessedId);
            statement.setString(2, doi);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String unprocessedId) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "DELETE FROM article_meta_table WHERE unprocessed_id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, unprocessedId);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArticleMetaModel getByUnprocessedId(String unprocessedId) {
        ArticleMetaModel meta = null;
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "select * from article_meta_table where unprocessed_id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, unprocessedId);
            ResultSet result = statement.executeQuery();
            result.next();
            meta = generateObject(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meta;
    }

    private ArticleMetaModel generateObject(ResultSet resultSet) throws SQLException {
        String author = resultSet.getString(1);
        String doi = resultSet.getString(2);
        String releaseDate = resultSet.getString(3);
        String title = resultSet.getString(4);
        return new ArticleMetaModel(author, doi, releaseDate, title);
    }
}
