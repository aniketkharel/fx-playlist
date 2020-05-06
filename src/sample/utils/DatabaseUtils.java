package sample.utils;

import com.mongodb.client.*;
import org.bson.Document;
import sample.config.DatabaseConfig;
import sample.model.ActivitiesModel;

import java.util.ArrayList;

public class DatabaseUtils {

    private ArrayList<String> contentList;

    public MongoDatabase getMongoDatabase(){
        MongoClient mongoClient = MongoClients.create(DatabaseConfig.MONGO_URL_REMOTE);
        return mongoClient.getDatabase("test");
    }
    public void insertDocument(ActivitiesModel activitiesModel){
        MongoCollection<Document> collection = getMongoDatabase().getCollection("activities");
        Document activityDocument = new Document("timeStamp",activitiesModel.getTimeStamp()).append("content",activitiesModel.getDescription()).append("username",activitiesModel.getUsername());
        collection.insertOne(activityDocument);
    }
    public ArrayList<String> getAllDocuments(){
        MongoCollection<Document> collection = getMongoDatabase().getCollection("activities");
        MongoCursor<Document> cursor = collection.find().iterator();
        contentList = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                contentList.add(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
        return contentList;
    }
}
