package tech.jhipster.lite.module.infrastructure.secondary;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import tech.jhipster.lite.module.domain.JHipsterPresetRepository;
import tech.jhipster.lite.module.domain.ProjectFiles;
import tech.jhipster.lite.module.domain.preset.Preset;
import tech.jhipster.lite.module.domain.preset.PresetName;
import tech.jhipster.lite.shared.error.domain.GeneratorException;

@Repository
class FileSystemJHipsterPresetRepository implements JHipsterPresetRepository {

  private static final String PRESET_FOLDER = "/";

  private final ObjectMapper json;
  private final ProjectFiles projectFiles;
  private final PresetName presetName;

  public FileSystemJHipsterPresetRepository(
    ObjectMapper json,
    ProjectFiles projectFiles,
    @Value("${jhlite.preset-file.name:preset.json}") String presetFileName
  ) {
    this.json = json;
    this.projectFiles = projectFiles;
    this.presetName = PresetName.from(presetFileName);
  }

  @Override
  public Collection<Preset> getPresets() {
    try {
      return json.readValue(projectFiles.readBytes(presetFilePath()), PersistedPresets.class).toDomain();
    } catch (IOException e) {
      throw GeneratorException.technicalError("Can't read presets: " + e.getMessage(), e);
    }
  }

  private String presetFilePath() {
    return PRESET_FOLDER + presetName.name();
  }
}
