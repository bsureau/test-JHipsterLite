package tech.jhipster.lite.module.domain.docker;

import java.util.Locale;
import tech.jhipster.lite.shared.error.domain.Assert;

public record DockerImageName(String imageName) {
  public DockerImageName(String imageName) {
    Assert.notBlank("imageName", imageName);

    this.imageName = imageName.toLowerCase(Locale.ROOT);
  }

  public String get() {
    return imageName();
  }
}
