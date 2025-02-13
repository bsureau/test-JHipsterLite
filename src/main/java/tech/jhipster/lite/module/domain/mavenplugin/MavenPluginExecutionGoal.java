package tech.jhipster.lite.module.domain.mavenplugin;

import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public record MavenPluginExecutionGoal(String goal) {
  public MavenPluginExecutionGoal {
    Assert.notBlank("goal", goal);
  }

  public String get() {
    return goal();
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    return get();
  }
}
