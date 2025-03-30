package com.twoweeksmc.connector;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import de.eztxm.ezlib.config.JsonConfig;

public class MongoConnector {
    private final MongoClient mongoClient;
    private final MongoCollection<Document> serverCollection;
    private final MongoCollection<Document> playerCollection;

    public MongoConnector(JsonConfig databaseConfiguration) {
        this.mongoClient = MongoClients.create(databaseConfiguration.get("url").asString());
        MongoDatabase mongoDatabase = this.mongoClient.getDatabase(databaseConfiguration.get("database").asString());
        this.serverCollection = mongoDatabase.getCollection("servers");
        this.playerCollection = mongoDatabase.getCollection("players");
    }

    public MongoCollection<Document> getPlayerCollection() {
        return playerCollection;
    }

    public MongoCollection<Document> getServerCollection() {
        return serverCollection;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }
}
