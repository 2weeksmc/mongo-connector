package com.twoweeksmc.connector;

import com.twoweeksmc.connector.model.PlayerModel;
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
    private final MongoCollection<Document> playerCollection;
    private final PlayerModel playerModel;
    private final ServerModel serverModel;

    public MongoConnector(JsonConfig databaseConfiguration) {
        this.mongoClient = MongoClients.create(databaseConfiguration.get("url").asString());
        MongoDatabase mongoDatabase = this.mongoClient.getDatabase(databaseConfiguration.get("database").asString());
        this.serverCollection = mongoDatabase.getCollection("servers");
        this.playerCollection = mongoDatabase.getCollection("players");
        this.playerModel = new PlayerModel(this);
        this.serverModel = new ServerModel(this);
    }

    public ServerModel getServerModel() {
        return serverModel;
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
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
