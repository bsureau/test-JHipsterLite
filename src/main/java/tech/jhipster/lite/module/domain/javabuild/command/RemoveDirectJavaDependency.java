package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.module.domain.javadependency.DependencyId;
import tech.jhipster.lite.shared.error.domain.Assert;

public record RemoveDirectJavaDependency(DependencyId dependency, Optional<BuildProfileId> buildProfile) implements JavaBuildCommand {
  public RemoveDirectJavaDependency {
    Assert.notNull("dependency", dependency);
    Assert.notNull("buildProfile", buildProfile);
  }

  public RemoveDirectJavaDependency(DependencyId dependency) {
    this(dependency, Optional.empty());
  }

  public RemoveDirectJavaDependency(DependencyId dependency, BuildProfileId buildProfile) {
    this(dependency, Optional.of(buildProfile));
  }
}
