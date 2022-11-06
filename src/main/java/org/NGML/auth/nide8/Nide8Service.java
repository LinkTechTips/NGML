package org.NGML.auth.nide8;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import java.util.logging.Level;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.unmodifiableList;


public class Nide8Service {
    private static final String authenticateUrl = "https://auth.mc-user.com:233/{0}/authserver/authenticate";
    private static final String refreshUrl = "https://auth.mc-user.com:233/{0}/authserver/refresh";
    private static final String validateUrl = "https://auth.mc-user.com:233/{0}/authserver/validate";
    private static final String invalidateUrl = "https://auth.mc-user.com:233/{0}/authserver/invalidate";
    private static final String signoutUrl = "https://auth.mc-user.com:233/{0}/authserver/signout";
    private static final String profileUrl = "https://auth.mc-user.com:233/{0}/sessionserver/session/minecraft/profile/{1}";


    private static Nide8InjectorArtifactProvider downloader;

    private static Map<String, Object> createRequestWithCredentials(String accessToken, String clientToken) {
        Map<String, Object> request = new HashMap<>();
        request.put("accessToken", accessToken);
        request.put("clientToken", clientToken);
        return request;
    }

    private static class AuthenticationResponse extends ErrorResponse {
        public String accessToken;
        public String clientToken;
        public Nide8GameProfile selectedProfile;
        public List<Nide8GameProfile> availableProfiles;
        public Nide8User user;
    }

    private static class ErrorResponse {
        public String error;
        public String errorMessage;
        public String cause;
    }

    public static final String PROFILE_URL = "https://aka.ms/MinecraftMigration";
    public static final String MIGRATION_FAQ_URL = "https://help.minecraft.net/hc/en-us/articles/360050865492-JAVA-Account-Migration-FAQ";
    public static final String PURCHASE_URL = "https://www.minecraft.net/store/minecraft-java-bedrock-edition-pc";
}