package org.coffeautoclick.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class MongoDB {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    public static void connection() {
        String user = "localhost";
        int port = 27111;
        String url = "mongodb://localhost:27017/";
        mongoClient = MongoClients.create(url);
        database = mongoClient.getDatabase("Coffe-Autoclick");
        collection = database.getCollection("souls");
    }

    public static MongoCollection<Document> getDocument() {
        return collection;
    }

    public static MongoClient getClient() {
        return mongoClient;
    }

    public static MongoDatabase getDB() {
        return database;
    }
}
