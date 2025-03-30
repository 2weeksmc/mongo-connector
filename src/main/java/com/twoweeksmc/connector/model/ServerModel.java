package com.twoweeksmc.connector.model;

import com.mongodb.client.model.Filters;
import com.twoweeksmc.connector.MongoConnector;
import de.eztxm.ezlib.config.object.JsonObject;
import org.bson.Document;

import java.time.Instant;
import java.util.UUID;

public class ServerModel {
    private final MongoConnector connector;

    public ServerModel(MongoConnector connector) {
        this.connector = connector;
    }

    public boolean addServer(UUID ownerId, JsonObject serverObject) {
        if (this.getServer(ownerId) != null) {
            return true;
        }
        Document document = new Document();
        document.put("ownerId", ownerId.toString());
        document.put("start", Instant.now());
        document.put("weeks", serverObject.get("weeks").asString());
        document.put("max-players", serverObject.get("max-players").asInteger());
        document.put("max-memory", serverObject.get("max-memory").asInteger());
        document.put("plugins", serverObject.get("plugins").asJsonArray());
        return this.connector.getServerCollection().insertOne(document).wasAcknowledged();
    }

    public Document getServer(UUID ownerId) {
        return this.connector.getServerCollection().find(Filters.eq("ownerId", ownerId.toString())).first();
    }
}
