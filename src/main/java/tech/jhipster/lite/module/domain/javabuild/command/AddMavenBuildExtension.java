package tech.jhipster.lite.module.domain.javabuild.command;

import tech.jhipster.lite.module.domain.javabuild.MavenBuildExtension;
import tech.jhipster.lite.shared.error.domain.Assert;

public record AddMavenBuildExtension(MavenBuildExtension buildExtension) implements JavaBuildCommand {
  public AddMavenBuildExtension {
    Assert.notNull("buildExtension", buildExtension);
  }
}
