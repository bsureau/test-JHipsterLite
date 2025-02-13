package tech.jhipster.lite.module.domain.properties;

import java.nio.file.Files;
import java.nio.file.Path;
import tech.jhipster.lite.shared.error.domain.Assert;

public record JHipsterProjectFolder(String folder) {
  public JHipsterProjectFolder {
    Assert.notBlank("folder", folder);
  }

  public Path filePath(String file) {
    Assert.notNull("file", file);

    return Path.of(folder(), file);
  }

  public boolean fileExists(String fileName) {
    return Files.exists(filePath(fileName));
  }

  public String get() {
    return folder();
  }
}
