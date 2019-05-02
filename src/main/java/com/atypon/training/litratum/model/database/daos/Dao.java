package com.atypon.training.litratum.model.database.daos;

import java.util.List;

public interface Dao {
    void addEntry(Object o);

    Object getSingleEntry(String id);

    List<Object> getAllEntries();

    void editEntry(Object o);
}
