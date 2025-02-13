package tech.jhipster.lite.module.domain.javabuild.command;

import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;

public interface AddMavenPluginPluginBuilder<T extends AddMavenPlugin> {
  AddMavenPluginOptionalBuilder<T> plugin(MavenPlugin plugin);
}
