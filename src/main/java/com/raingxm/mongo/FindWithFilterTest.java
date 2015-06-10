package com.raingxm.mongo;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Filter;

import static com.mongodb.client.model.Filters.*;

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

        Bson filter = eq("x", 0);
        Bson projection = Projections.exclude("x");

        List<Document> all = coll.find(filter).projection(projection).into(new ArrayList<Document>());
        printJson(all);
    }

    private static void printJson(List<Document> all) {
        for(Document doc: all) {
            System.out.println(doc);
        }
    }

}
