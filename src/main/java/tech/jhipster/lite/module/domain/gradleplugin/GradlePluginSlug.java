package tech.jhipster.lite.module.domain.gradleplugin;

import tech.jhipster.lite.shared.error.domain.Assert;

public record GradlePluginSlug(String slug) {
  public GradlePluginSlug {
    Assert.notBlank("slug", slug);
  }

  public String get() {
    return slug();
  }
}
