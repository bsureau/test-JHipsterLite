package tech.jhipster.lite.module.domain.startupcommand;

public sealed interface JHipsterStartupCommand permits DockerComposeStartupCommandLine, GradleStartupCommandLine, MavenStartupCommandLine {
  StartupCommandLine commandLine();
}
