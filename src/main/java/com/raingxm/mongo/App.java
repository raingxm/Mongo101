package com.raingxm.mongo;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class App {
    public static void main(String[] args) {
        MongoClient client = new MongoClient(new ServerAddress());
        MongoDatabase db = client.getDatabase("test").
                withReadPreference(ReadPreference.primary());
        MongoCollection<Document> col = db.getCollection("test").
                withReadPreference(ReadPreference.secondary());

    }
}
