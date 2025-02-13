package tech.jhipster.lite.module.domain.startupcommand;

import tech.jhipster.lite.shared.error.domain.Assert;

public record StartupCommandLine(String commandLine) {
  public StartupCommandLine(String commandLine) {
    Assert.notBlank("commandLine", commandLine);
    this.commandLine = commandLine.trim();
  }

  public String get() {
    return commandLine();
  }
}
