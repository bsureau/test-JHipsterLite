package tech.jhipster.lite.module.domain.startupcommand;

import tech.jhipster.lite.shared.error.domain.Assert;

public record GradleStartupCommandLine(String commandLineParameters) implements JHipsterStartupCommand {
  public GradleStartupCommandLine {
    Assert.notNull("commandLineParameters", commandLineParameters);
  }

  @Override
  public StartupCommandLine commandLine() {
    return new StartupCommandLine("./gradlew " + commandLineParameters.trim());
  }
}
