package com.raingxm.mongo;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindWithFilterTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("findWithFilter");

        coll.drop();
        for(int i=0; i<10; i++) {
            coll.insertOne(new Document().append("x", new Random().nextInt(2)).append("y",
                    new Random().nextInt(100)).append("i", i));
        }

        List<Document> all = coll.find().into(new ArrayList<Document>());
        printJson(all);
    }

    private static void printJson(List<Document> all) {
        for(Document doc: all) {
            System.out.println(doc);
        }
    }

}
