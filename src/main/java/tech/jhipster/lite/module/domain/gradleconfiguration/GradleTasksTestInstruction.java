package tech.jhipster.lite.module.domain.gradleconfiguration;

import tech.jhipster.lite.shared.error.domain.Assert;

record GradleTasksTestInstruction(String instruction) {
  public GradleTasksTestInstruction {
    Assert.notBlank("instruction", instruction);
  }

  public String get() {
    return instruction();
  }
}
