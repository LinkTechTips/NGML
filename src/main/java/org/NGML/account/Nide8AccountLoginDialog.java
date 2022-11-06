package org.NGML.account;



import org.NGML.auth.nide8.Nide8AuthInfo;
import org.NGML.auth.nide8.Nide8ClassicAccount;

import java.util.function.Consumer;
import java.util.logging.Level;

public class Nide8AccountLoginDialog  {
    private final Nide8ClassicAccount oldAccount;
    private final Consumer<Nide8AuthInfo> success;
    private final Runnable failed;



    public Nide8AccountLoginDialog(Nide8ClassicAccount oldAccount, Consumer<Nide8AuthInfo> success, Runnable failed) {
        this.oldAccount = oldAccount;
        this.success = success;
        this.failed = failed;
    }
}
