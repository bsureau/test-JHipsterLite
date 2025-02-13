package tech.jhipster.lite.module.domain.preset;

import tech.jhipster.lite.shared.error.domain.Assert;

public record PresetName(String name) {
  public PresetName {
    Assert.notBlank("name", name);
  }

  public static PresetName from(String name) {
    return new PresetName(name);
  }
}
