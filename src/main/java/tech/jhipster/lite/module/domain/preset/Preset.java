package tech.jhipster.lite.module.domain.preset;

import tech.jhipster.lite.module.domain.JHipsterModuleSlugs;
import tech.jhipster.lite.shared.error.domain.Assert;

public record Preset(PresetName name, JHipsterModuleSlugs modules) {
  public Preset {
    Assert.notNull("name", name);
    Assert.notNull("modules", modules);
  }
}
