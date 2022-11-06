package org.NGML.auth.nide8;

import java.io.IOException;
import java.util.Optional;

public interface Nide8InjectorArtifactProvider {
    Nide8InjectorArtifactInfo getArtifactInfo() throws IOException;
    Optional<Nide8InjectorArtifactInfo> getArtifactInfoImmediately();
}