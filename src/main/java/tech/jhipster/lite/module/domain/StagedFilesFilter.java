package tech.jhipster.lite.module.domain;

import tech.jhipster.lite.shared.error.domain.Assert;

public record StagedFilesFilter(String filter) {
  public StagedFilesFilter {
    Assert.notBlank("filter", filter);
  }

  @Override
  public String toString() {
    return filter;
  }
}
