package org.NGML.auth.nide8;

import java.util.*;
import java.util.stream.Collectors;


public class Nide8Session {
    private final String serverID;
    private final String clientToken;
    private final String accessToken;
    private final Nide8GameProfile selectedProfile;
    private final List<Nide8GameProfile> availableProfiles;

    private final Map<String, String> userProperties;
    private static Nide8InjectorArtifactProvider downloader;

    public Nide8Session(Nide8InjectorArtifactProvider downloader, String serverID, String clientToken, String accessToken, Nide8GameProfile selectedProfile, List<Nide8GameProfile> availableProfiles, Map<String, String> userProperties) {
        this.serverID = serverID;
        this.clientToken = clientToken;
        this.accessToken = accessToken;
        this.selectedProfile = selectedProfile;
        this.availableProfiles = availableProfiles;
        this.userProperties = userProperties;
        Nide8Session.downloader = downloader;

    }

    public String getClientToken() {
        return clientToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @return nullable (null if no character is selected)
     */
    public Nide8GameProfile getSelectedProfile() {
        return selectedProfile;
    }

    /**
     * @return nullable (null if the YggdrasilSession is loaded from storage)
     */
    public List<Nide8GameProfile> getAvailableProfiles() {
        return availableProfiles;
    }

    public Map<String, String> getUserProperties() {
        return userProperties;
    }

    public String getServerID() {
        return serverID;
    }


}