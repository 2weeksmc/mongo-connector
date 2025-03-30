package com.twoweeksmc.connector.model;

import com.mongodb.client.model.Filters;
import com.twoweeksmc.connector.MongoConnector;
import org.bson.Document;

import java.util.UUID;

public class PlayerModel {
    private final MongoConnector connector;

    public PlayerModel(MongoConnector connector) {
        this.connector = connector;
    }

    public boolean addPlayer(UUID uuid) {
        if (this.getPlayer(uuid) != null) {
            return true;
        }
        Document document = new Document();
        document.put("uuid", uuid.toString());
        document.put("gems", 50);
        document.put("last-server-end", null);
        document.put("server", null);
        return this.connector.getPlayerCollection().insertOne(document).wasAcknowledged();
    }

    public Document getPlayer(UUID uuid) {
        return this.connector.getPlayerCollection().find(Filters.eq("uuid", uuid.toString())).first();
    }
}
