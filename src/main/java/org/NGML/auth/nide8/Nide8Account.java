package org.NGML.auth.nide8;

import java.util.UUID;

public class Nide8Account extends Nide8ClassicAccount {
    protected UUID characterUUID;
    protected boolean authenticated = false;
    public Nide8Account(String serverID) {
        super(serverID);
    }
}
