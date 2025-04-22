package com.twoweeksmc.connector;

import com.twoweeksmc.connector.model.UserModel;
import com.twoweeksmc.connector.model.ServerModel;
import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import de.eztxm.ezlib.config.JsonConfig;

public class MongoConnector {
    private final MongoClient mongoClient;
    private final MongoCollection<Document> serverCollection;
    private final MongoCollection<Document> userCollection;
    private final UserModel userModel;
    private final ServerModel serverModel;

    public MongoConnector(JsonConfig databaseConfiguration) {
        this.mongoClient = MongoClients.create(
                databaseConfiguration.get("protocol").asString()
                        + "://" + databaseConfiguration.get("username").asString()
                        + ":" + databaseConfiguration.get("password").asString()
                + "@" + databaseConfiguration.get("host").asString()
                + ":" + databaseConfiguration.get("port").asString()
                + "/?authSource=" + databaseConfiguration.get("database").asString()
        );
        MongoDatabase mongoDatabase = this.mongoClient.getDatabase(databaseConfiguration.get("database").asString());
        this.serverCollection = mongoDatabase.getCollection("servers");
        this.userCollection = mongoDatabase.getCollection("users");
        this.userModel = new UserModel(this);
        this.serverModel = new ServerModel(this);
    }

    public ServerModel getServerModel() {
        return serverModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public MongoCollection<Document> getUserCollection() {
        return userCollection;
    }

    public MongoCollection<Document> getServerCollection() {
        return serverCollection;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }
}
