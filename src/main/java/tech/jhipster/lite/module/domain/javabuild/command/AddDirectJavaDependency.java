package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.shared.error.domain.Assert;

public record AddDirectJavaDependency(JavaDependency dependency, Optional<BuildProfileId> buildProfile)
  implements JavaBuildCommand, AddJavaDependency {
  public AddDirectJavaDependency {
    Assert.notNull("dependency", dependency);
    Assert.notNull("buildProfile", buildProfile);
  }

  public AddDirectJavaDependency(JavaDependency dependency) {
    this(dependency, Optional.empty());
  }

  public AddDirectJavaDependency(JavaDependency dependency, BuildProfileId buildProfile) {
    this(dependency, Optional.of(buildProfile));
  }
}
