package org.NGML.auth.nide8;

public abstract class Nide8ClassicAccount {

    protected final String serverID;

    public Nide8ClassicAccount(String serverID) {
        this.serverID = serverID;
    }

    public String getServerID() {
        return serverID;
    }

}