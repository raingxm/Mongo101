package com.raingxm.mongo;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.bson.Document;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;

public class HelloWithMongo {
    private static Configuration configuration = new Configuration();

    public static void main(String[] args) {
        configuration.setClassForTemplateLoading(HelloWithMongo.class, "/");
        MongoClient mongoClient = new MongoClient(new ServerAddress());
        MongoDatabase db = mongoClient.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("student");
        coll.insertOne(new Document("name", "zhangsan"));
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    helloTemplate.process(coll.find().first(), writer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }
}
