package com.pryjda.mongoDB_app;

import com.pryjda.mongoDB_app.instance_provider.MongoProvider;
import com.pryjda.mongoDB_app.crud.MongoCrud;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        MongoCrud mongoInstance = MongoProvider.getInstance().getMongoCrud();

        Document docOne = new Document("name", "Bob")
                .append("place", "Cracow")
                .append("start_date", "2018-10-10")
                .append("start_time", "8:00")
                .append("members_limit", "100");

        Document docTwo = new Document("name", "Mike")
                .append("place", "Cracow")
                .append("start_date", "2018-10-10")
                .append("start_time", "8:00")
                .append("members_limit", "100");

        Document docThree = new Document("name", "John")
                .append("place", "Cracow")
                .append("start_date", "2018-10-10")
                .append("start_time", "8:00")
                .append("members_limit", "100");

        List<Document> docs = new ArrayList<>();
        docs.add(docTwo);
        docs.add(docThree);

        //CREATE
        mongoInstance.create(docOne);
        mongoInstance.create(docs);
        mongoInstance.createRandomDocs(10);

        //READ
        mongoInstance.read().forEach(i -> System.out.println(i.toJson()));
        System.out.println();
        mongoInstance.read(5).forEach(i -> System.out.println(i.toJson()));

        System.out.println();
        mongoInstance.findByPlace("Cracow").forEach(i -> System.out.println(i.toJson()));

        System.out.println();
        mongoInstance.findRange(30, 70).forEach(i -> System.out.println(i.toJson()));

        //UPDATE
        mongoInstance.updatePlaceInsideRange(30, 70, "Moscow");
        System.out.println();
        mongoInstance.read().forEach(i -> System.out.println(i.toJson()));

        //DELETE
        mongoInstance.deleteInsideRange(20, 80);
        System.out.println();
        mongoInstance.read().forEach(i -> System.out.println(i.toJson()));

        mongoInstance.delete();

    }
}
