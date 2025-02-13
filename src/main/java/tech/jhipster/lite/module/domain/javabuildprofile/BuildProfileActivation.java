package tech.jhipster.lite.module.domain.javabuildprofile;

import java.util.Optional;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class BuildProfileActivation {

  private final Optional<Boolean> activeByDefault;

  public BuildProfileActivation(BuildProfileActivationBuilder builder) {
    this.activeByDefault = Optional.ofNullable(builder.activeByDefault);
  }

  public Optional<Boolean> activeByDefault() {
    return activeByDefault;
  }

  public static BuildProfileActivationBuilder builder() {
    return new BuildProfileActivationBuilder();
  }

  public static final class BuildProfileActivationBuilder {

    private Boolean activeByDefault;

    private BuildProfileActivationBuilder() {}

    public BuildProfileActivationBuilder activeByDefault() {
      return activeByDefault(true);
    }

    public BuildProfileActivationBuilder activeByDefault(Boolean activeByDefault) {
      Assert.notNull("activeByDefault", activeByDefault);
      this.activeByDefault = activeByDefault;
      return this;
    }

    public BuildProfileActivation build() {
      return new BuildProfileActivation(this);
    }
  }
}
