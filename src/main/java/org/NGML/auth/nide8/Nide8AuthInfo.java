package org.NGML.auth.nide8;

import java.util.UUID;

public class Nide8AuthInfo {

    private final String serverID;
    private final Nide8InjectorArtifactInfo info;

    public Nide8AuthInfo(Nide8InjectorArtifactInfo info, String serverID, String username, UUID uuid, String accessToken, String userProperties) {
        this.serverID = serverID;
        this.info = info;
    }

    public String getServerID() {
        return serverID;
    }
}