package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Optional;
import tech.jhipster.lite.module.domain.buildproperties.BuildProperty;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.shared.error.domain.Assert;

public record SetBuildProperty(BuildProperty property, Optional<BuildProfileId> buildProfile) implements JavaBuildCommand {
  public SetBuildProperty {
    Assert.notNull("property", property);
    Assert.notNull("buildProfile", buildProfile);
  }

  public SetBuildProperty(BuildProperty property) {
    this(property, Optional.empty());
  }

  public SetBuildProperty(BuildProperty property, BuildProfileId buildProfile) {
    this(property, Optional.of(buildProfile));
  }
}
