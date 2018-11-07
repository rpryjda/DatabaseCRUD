package com.pryjda.hibernate_app.dao;

import com.pryjda.hibernate_app.entities.Runs;

import java.util.List;

public interface DaoRunsCrud {

    void create(Runs runs);

    List<Runs> read();

    Runs read(int id);

    void update(Runs runs);

    void delete();

    void delete(int id);

    void delete(Runs runs);
}
