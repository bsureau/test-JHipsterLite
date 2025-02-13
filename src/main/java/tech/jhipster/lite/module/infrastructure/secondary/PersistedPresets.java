package tech.jhipster.lite.module.infrastructure.secondary;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import tech.jhipster.lite.module.domain.preset.Preset;

record PersistedPresets(@JsonProperty("presets") Collection<PersistedPreset> presets) {
  public Collection<Preset> toDomain() {
    return presets.stream().map(PersistedPreset::toDomain).toList();
  }
}
