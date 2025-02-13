package tech.jhipster.lite.module.domain.javabuild;

import tech.jhipster.lite.module.domain.JHipsterProjectFilePath;

public enum JavaBuildTool {
  MAVEN("target"),
  GRADLE("build");

  private final JHipsterProjectFilePath buildDirectory;

  JavaBuildTool(String buildDirectory) {
    this.buildDirectory = new JHipsterProjectFilePath(buildDirectory);
  }

  public JHipsterProjectFilePath buildDirectory() {
    return buildDirectory;
  }
}
