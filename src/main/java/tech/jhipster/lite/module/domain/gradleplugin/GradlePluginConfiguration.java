package tech.jhipster.lite.module.domain.gradleplugin;

import tech.jhipster.lite.shared.error.domain.Assert;

public record GradlePluginConfiguration(String configuration) {
  public GradlePluginConfiguration {
    Assert.notBlank("configuration", configuration);
  }

  public String get() {
    return configuration();
  }
}
