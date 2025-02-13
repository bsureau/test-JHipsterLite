package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Collection;
import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyVersion;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class AddDirectMavenPlugin implements JavaBuildCommand, AddMavenPlugin {

  private final MavenPlugin plugin;
  private final Optional<JavaDependencyVersion> pluginVersion;
  private final Optional<BuildProfileId> buildProfile;
  private final Collection<JavaDependencyVersion> dependenciesVersions;

  private AddDirectMavenPlugin(AddMavenPluginBuilder<?> builder) {
    Assert.notNull("plugin", builder.plugin());
    Assert.notNull("dependenciesVersions", builder.dependenciesVersions());
    this.plugin = builder.plugin();
    this.dependenciesVersions = builder.dependenciesVersions();
    this.pluginVersion = Optional.ofNullable(builder.pluginVersion());
    this.buildProfile = Optional.ofNullable(builder.buildProfile());
  }

  @Override
  public MavenPlugin plugin() {
    return plugin;
  }

  public Optional<BuildProfileId> buildProfile() {
    return buildProfile;
  }

  @Override
  public Optional<JavaDependencyVersion> pluginVersion() {
    return pluginVersion;
  }

  public Collection<JavaDependencyVersion> dependenciesVersions() {
    return dependenciesVersions;
  }

  public static AddMavenPluginPluginBuilder<AddDirectMavenPlugin> builder() {
    return new AddMavenPluginBuilder<>(AddDirectMavenPlugin::new);
  }
}
