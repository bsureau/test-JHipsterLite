package tech.jhipster.lite.module.domain.gradleplugin;

import tech.jhipster.lite.shared.error.domain.Assert;

public record GradlePluginId(String id) {
  public GradlePluginId {
    Assert.notNull("id", id);
  }

  public String get() {
    return id();
  }
}
