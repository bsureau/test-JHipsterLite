package tech.jhipster.lite.module.infrastructure.primary;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collection;
import tech.jhipster.lite.module.domain.preset.Preset;

@Schema(name = "Presets", description = "Information on the predefined configurations")
record RestPresets(Collection<RestPreset> presets) {
  public static RestPresets from(Collection<Preset> presets) {
    return new RestPresets(presets.stream().map(RestPreset::from).toList());
  }
}
