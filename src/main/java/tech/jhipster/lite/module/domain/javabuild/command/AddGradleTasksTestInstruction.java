package tech.jhipster.lite.module.domain.javabuild.command;

import tech.jhipster.lite.shared.error.domain.Assert;

public record AddGradleTasksTestInstruction(String instruction) implements JavaBuildCommand {
  public AddGradleTasksTestInstruction {
    Assert.notNull("instruction", instruction);
  }

  public String get() {
    return instruction;
  }
}
