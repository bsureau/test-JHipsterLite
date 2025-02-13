package tech.jhipster.lite.module.domain.gradleplugin;

import tech.jhipster.lite.shared.error.domain.Assert;

public record BuildGradleImport(String gradleImport) {
  public BuildGradleImport {
    Assert.notBlank("gradleImport", gradleImport);
  }

  public String get() {
    return gradleImport();
  }
}
