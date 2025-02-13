package tech.jhipster.lite.module.domain.javadependency;

import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public record Version(String version) {
  public Version {
    Assert.notBlank("version", version);
  }

  public String get() {
    return version();
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    return get();
  }
}
