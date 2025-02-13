package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileActivation;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.shared.error.domain.Assert;

public record AddJavaBuildProfile(BuildProfileId buildProfileId, Optional<BuildProfileActivation> activation) implements JavaBuildCommand {
  public AddJavaBuildProfile {
    Assert.notNull("buildProfileId", buildProfileId);
    Assert.notNull("activation", activation);
  }

  public AddJavaBuildProfile(BuildProfileId buildProfileId) {
    this(buildProfileId, Optional.empty());
  }

  public AddJavaBuildProfile(BuildProfileId buildProfileId, BuildProfileActivation activation) {
    this(buildProfileId, Optional.of(activation));
  }
}
