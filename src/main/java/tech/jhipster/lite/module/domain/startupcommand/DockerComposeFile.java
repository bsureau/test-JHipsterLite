package tech.jhipster.lite.module.domain.startupcommand;

import tech.jhipster.lite.shared.error.domain.Assert;

public record DockerComposeFile(String path) {
  public DockerComposeFile {
    Assert.notBlank("path", path);
  }
}
