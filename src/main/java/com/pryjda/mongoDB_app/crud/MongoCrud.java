package com.pryjda.mongoDB_app.crud;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.pryjda.mongoDB_app.utils.MongoUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MongoCrud {

    private MongoClient client = MongoUtils.getInstance().getMongoClient();
    private MongoDatabase mongoDatabase = client.getDatabase("datastorage");
    private MongoCollection<Document> collection = mongoDatabase.getCollection("runs");

    public void create(Document document) {
        collection.insertOne(document);
    }

    public void create(List<Document> documents) {
        collection.insertMany(documents);
    }

    public void createRandomDocs(int quantity) {
        Random random = new Random();
        List<Document> results = Stream.iterate(1, i -> i + 1).limit(quantity)
                .map(j -> new Document("name", UUID.randomUUID().toString())
                        .append("place", "Warsaw")
                        .append("start_date", "2018-10-10")
                        .append("start_time", "8:00")
                        .append("members_limit", random.nextInt(100)))
                .collect(Collectors.toList());
        collection.insertMany(results);
    }

    public List<Document> read() {
        Iterable<Document> documents = collection.find();
        List<Document> results = new ArrayList<>();
        for (Document doc : documents) {
            results.add(doc);
        }
        return results;
    }

    public List<Document> read(int limit) {
        Iterable<Document> documents = collection.find().limit(limit);
        List<Document> results = new ArrayList<>();
        for (Document doc : documents) {
            results.add(doc);
        }
        return results;
    }

    public Document findById(String idNumber) {
        Document doc = collection
                .find(Filters.eq("_id", new ObjectId(idNumber)))
                .first();
        return doc;
    }

    public List<Document> findByPlace(String place) {
        List<Document> results = new ArrayList<>();
        Iterable<Document> documents = collection
                .find(Filters.eq("place", place));
        for (Document doc : documents) {
            results.add(doc);
        }
        return results;
    }

    public List<Document> findRange(int min, int max) {
        Iterable<Document> documents = collection
                .find(Filters
                        .and(
                                Filters.gte("members_limit", min),
                                Filters.lte("members_limit", max)
                        ));
        List<Document> results = new ArrayList<>();
        for (Document doc : documents) {
            results.add(doc);
        }
        return results;
    }

    public void updatePlaceById(String id, String place) {
        collection.updateOne(Filters
                .eq("_id", new ObjectId(id)), Updates.set("place", place));
    }

    public void updatePlaceInsideRange(int min, int max, String place) {
        collection.updateMany(Filters
                .and(
                        Filters.gte("members_limit", min),
                        Filters.lte("members_limit", max)
                ), Updates.set("place", place));
    }

    public void delete() {
        for(Document doc: collection.find()){
            collection.deleteOne(doc);
        }
    }

    public void deleteById(String id) {
        collection.deleteOne(Filters
                .eq("_id", new ObjectId(id)));
    }

    public void deleteInsideRange(int min, int max) {
        collection.deleteMany(Filters
                .and(
                        Filters.gte("members_limit", min),
                        Filters.lte("members_limit", max)
                ));
    }

}
