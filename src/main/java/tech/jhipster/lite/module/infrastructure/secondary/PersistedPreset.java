package tech.jhipster.lite.module.infrastructure.secondary;

import java.util.Collection;
import tech.jhipster.lite.module.domain.JHipsterModuleSlugs;
import tech.jhipster.lite.module.domain.preset.Preset;
import tech.jhipster.lite.module.domain.preset.PresetName;

record PersistedPreset(String name, Collection<String> modules) {
  public Preset toDomain() {
    return new Preset(PresetName.from(name), JHipsterModuleSlugs.from(modules));
  }
}
