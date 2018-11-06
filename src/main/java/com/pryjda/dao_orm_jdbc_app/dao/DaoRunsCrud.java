package com.pryjda.dao_orm_jdbc_app.dao;

import com.pryjda.dao_orm_jdbc_app.entities.Runs;

import java.util.List;

public interface DaoRunsCrud {

    void create(String sqlQuery);

    void create(Runs runs);

    List<Runs> read();

    Runs read(int id);

    void update(Runs runs);

    void delete();

    void delete(int id);

}
