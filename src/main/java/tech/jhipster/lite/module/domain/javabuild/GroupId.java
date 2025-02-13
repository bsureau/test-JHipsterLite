package tech.jhipster.lite.module.domain.javabuild;

import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public record GroupId(String groupId) {
  public GroupId {
    Assert.notBlank("groupId", groupId);
  }

  public String get() {
    return groupId();
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    return groupId();
  }
}
