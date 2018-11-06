package com.pryjda.dao_orm_jdbc_app.dao_provider;

import com.pryjda.dao_orm_jdbc_app.dao.JdbcRunsCrud;
import lombok.Getter;

public class DaoProvider {

    private static DaoProvider ourInstance = new DaoProvider();
    @Getter
    private JdbcRunsCrud runsInstance;

    private DaoProvider() {
        runsInstance = new JdbcRunsCrud();
    }

    public static DaoProvider getInstance() {
        return ourInstance;
    }

}
