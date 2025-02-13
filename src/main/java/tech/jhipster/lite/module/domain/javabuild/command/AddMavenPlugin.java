package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Collection;
import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.module.domain.javadependency.DependencyId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyVersion;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPluginConfiguration;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPluginExecution;

public interface AddMavenPlugin {
  MavenPlugin plugin();

  default Optional<VersionSlug> versionSlug() {
    return plugin().versionSlug();
  }

  default Optional<MavenPluginConfiguration> configuration() {
    return plugin().configuration();
  }

  default Collection<MavenPluginExecution> executions() {
    return plugin().executions();
  }

  default Collection<JavaDependency> dependencies() {
    return plugin().dependencies();
  }

  default DependencyId dependencyId() {
    return plugin().dependencyId();
  }

  Optional<JavaDependencyVersion> pluginVersion();
}
