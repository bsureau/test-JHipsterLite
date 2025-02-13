package tech.jhipster.lite.module.domain.mavenplugin;

import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public record MavenPluginExecutionId(String executionId) {
  public MavenPluginExecutionId {
    Assert.notBlank("executionId", executionId);
  }

  public String get() {
    return executionId();
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    return get();
  }
}
