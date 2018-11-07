package com.pryjda.mongoDB_app.instance_provider;

import com.pryjda.mongoDB_app.crud.MongoCrud;
import lombok.Getter;

public class MongoProvider {

    private static MongoProvider ourInstance = new MongoProvider();
    @Getter
    private MongoCrud mongoCrud;

    private MongoProvider() {
        mongoCrud = new MongoCrud();
    }

    public static MongoProvider getInstance() {
        return ourInstance;
    }

}
