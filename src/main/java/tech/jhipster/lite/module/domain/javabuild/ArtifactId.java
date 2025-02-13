package tech.jhipster.lite.module.domain.javabuild;

import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public record ArtifactId(String artifactId) {
  public ArtifactId {
    Assert.notBlank("artifactId", artifactId);
  }

  public String get() {
    return artifactId();
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    return artifactId();
  }
}
