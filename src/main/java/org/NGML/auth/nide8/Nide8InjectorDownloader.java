package org.NGML.auth.nide8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class Nide8InjectorDownloader implements Nide8InjectorArtifactProvider {

    private static final String LATEST_BUILD_URL = "https://login.mc-user.com:233/download/nide8auth.jar";

    private final Path artifactLocation = null;



    @Override
    public Nide8InjectorArtifactInfo getArtifactInfo() throws IOException {
        Optional<Nide8InjectorArtifactInfo> cached = getArtifactInfoImmediately();
        if (cached.isPresent()) {
            return cached.get();
        }

        synchronized (this) {
            Optional<Nide8InjectorArtifactInfo> local = getLocalArtifact();
            if (local.isPresent()) {
                return local.get();
            }
            updateChecked.set(true);
            update();
            local = getLocalArtifact();
            return local.orElseThrow(() -> new IOException("The downloaded nide8auth cannot be recognized"));
        }
    }

    @Override
    public Optional<Nide8InjectorArtifactInfo> getArtifactInfoImmediately() {
        return getLocalArtifact();
    }

    private final AtomicBoolean updateChecked = new AtomicBoolean(false);

    public void checkUpdate() throws IOException {
        // this method runs only once
        if (updateChecked.compareAndSet(false, true)) {
            synchronized (this) {
                update();
            }
        }
    }

    private void update() throws IOException {
        Optional<Nide8InjectorArtifactInfo> local = getLocalArtifact();
        if (local.isPresent()) {
            return;
        }

        try {

        } catch (Exception e) {
            throw new IOException("Failed to download nide8auth.jar", e);
        }

    }

    private Optional<Nide8InjectorArtifactInfo> getLocalArtifact() {
        return parseArtifact(artifactLocation);
    }

    protected static Optional<Nide8InjectorArtifactInfo> parseArtifact(Path path) {
        if (!Files.isRegularFile(path)) {
            return Optional.empty();
        }
        try {
            return Optional.of(Nide8InjectorArtifactInfo.from(path));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

}