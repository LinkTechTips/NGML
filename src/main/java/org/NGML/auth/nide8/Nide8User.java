package org.NGML.auth.nide8;

import java.util.Map;

public class Nide8User {

    private final String id;


    private final Map<String, String> properties;

    public Nide8User() {
        properties = null;
        id = null;
    }


    public String getId() {
        return id;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

}