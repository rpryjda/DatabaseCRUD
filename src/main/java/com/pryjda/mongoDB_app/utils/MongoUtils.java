package com.pryjda.mongoDB_app.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Getter;

public class MongoUtils {

    private static MongoUtils ourInstance = new MongoUtils();
    @Getter
    private MongoClient mongoClient;

    private MongoUtils() {
        mongoClient = MongoClients.create("mongodb://localhost");
    }

    public static MongoUtils getInstance() {
        return ourInstance;
    }


}
