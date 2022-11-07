package org.NGML.until;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class Login {
    public static String login (String username, String password) throws IOException, InterruptedException {
        var values = new HashMap<String, String>() {{
            put("username", username);
            put ("password", password);
            put("requestUser","false");
        }};


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://114514.com/api/yggdrasil/authserver/authenticate"))
                .header("Content-Type","application/json")
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());
        return response.body();
    }
    public static String refreshToken(String token) throws IOException, InterruptedException {
        var values = new HashMap<String, String>() {{
            put("accessToken", token);
        }};

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://114514.com/api/yggdrasil/authserver/refresh"))
                .header("Content-Type","application/json")
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());
        return response.body();
    }
    public static Boolean checkToken(String token) throws IOException, InterruptedException {
        var values = new HashMap<String, String>() {{
            put("accessToken", token);
        }};

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://114514.com/api/yggdrasil/authserver/validate"))
                .header("Content-Type","application/json")
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        if (response.statusCode()==204){
            return true;
        }
        //System.out.println(response.body());
        return false;
    }
}