package tech.jhipster.lite.module.domain.javabuild.command;

import tech.jhipster.lite.shared.error.domain.Assert;

public record AddGradleConfiguration(String configuration) implements JavaBuildCommand {
  public AddGradleConfiguration {
    Assert.notNull("configuration", configuration);
  }

  public String get() {
    return configuration;
  }
}
