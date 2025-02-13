package tech.jhipster.lite.module.domain.startupcommand;

import tech.jhipster.lite.shared.error.domain.Assert;

public record DockerComposeStartupCommandLine(DockerComposeFile dockerComposeFile) implements JHipsterStartupCommand {
  public DockerComposeStartupCommandLine {
    Assert.notNull("dockerComposeFile", dockerComposeFile);
  }

  @Override
  public StartupCommandLine commandLine() {
    return new StartupCommandLine("docker compose -f %s up -d".formatted(dockerComposeFile.path()));
  }
}
