package com.pryjda.hibernate_app.dao_provider;

import com.pryjda.hibernate_app.dao.DaoRunsCrud;
import com.pryjda.hibernate_app.dao.HibernateRunsCrud;
import lombok.Getter;

public class DaoProvider {

    private static DaoProvider ourInstance = new DaoProvider();
    @Getter
    private DaoRunsCrud runsInstance;

    private DaoProvider() {
        runsInstance = new HibernateRunsCrud();
    }

    public static DaoProvider getInstance() {
        return ourInstance;
    }


}
