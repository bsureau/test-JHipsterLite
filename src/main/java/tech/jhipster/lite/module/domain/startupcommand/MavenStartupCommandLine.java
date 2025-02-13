package tech.jhipster.lite.module.domain.startupcommand;

import tech.jhipster.lite.shared.error.domain.Assert;

public record MavenStartupCommandLine(String commandLineParameters) implements JHipsterStartupCommand {
  public MavenStartupCommandLine {
    Assert.notNull("commandLineParameters", commandLineParameters);
  }

  @Override
  public StartupCommandLine commandLine() {
    return new StartupCommandLine("./mvnw " + commandLineParameters.trim());
  }
}
