package org.NGML.auth.nide8;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Nide8GameProfile {

    private final Map<String, String> properties;
    private final UUID id;
    private final String name;
    private final String serverID;

    public Nide8GameProfile(String serverID, UUID id, String name, Map<String, String> properties) {
        this.id = id;
        this.name = name;
        this.serverID = serverID;
        this.properties = Objects.requireNonNull(properties);
    }

    public Nide8GameProfile(Nide8GameProfile profile, Map<String, String> properties) {
        this(profile.getServerID(), profile.getId(), profile.getName(), properties);
    }

    public Nide8GameProfile(String serverID, UUID id, String name) {
        this.id = id;
        this.name = name;
        this.serverID = serverID;
        properties = null;
    }

    public String getServerID() {
        return serverID;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}