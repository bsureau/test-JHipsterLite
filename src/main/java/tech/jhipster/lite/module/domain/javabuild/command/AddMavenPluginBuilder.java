package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.Function;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyVersion;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;

class AddMavenPluginBuilder<T extends AddMavenPlugin> implements AddMavenPluginPluginBuilder<T>, AddMavenPluginOptionalBuilder<T> {

  private final Function<AddMavenPluginBuilder<T>, T> factory;
  private MavenPlugin plugin;
  private JavaDependencyVersion pluginVersion;
  private BuildProfileId buildProfile;
  private final Collection<JavaDependencyVersion> dependenciesVersions = new LinkedHashSet<>();

  AddMavenPluginBuilder(Function<AddMavenPluginBuilder<T>, T> factory) {
    this.factory = factory;
  }

  public MavenPlugin plugin() {
    return plugin;
  }

  public JavaDependencyVersion pluginVersion() {
    return pluginVersion;
  }

  public BuildProfileId buildProfile() {
    return buildProfile;
  }

  public Collection<JavaDependencyVersion> dependenciesVersions() {
    return dependenciesVersions;
  }

  @Override
  public AddMavenPluginBuilder<T> plugin(MavenPlugin plugin) {
    this.plugin = plugin;
    return this;
  }

  @Override
  public AddMavenPluginOptionalBuilder<T> pluginVersion(JavaDependencyVersion pluginVersion) {
    this.pluginVersion = pluginVersion;
    return this;
  }

  @Override
  public AddMavenPluginOptionalBuilder<T> buildProfile(BuildProfileId buildProfile) {
    this.buildProfile = buildProfile;
    return this;
  }

  @Override
  public AddMavenPluginOptionalBuilder<T> addDependencyVersion(JavaDependencyVersion dependencyVersion) {
    this.dependenciesVersions.add(dependencyVersion);
    return this;
  }

  @Override
  public T build() {
    return factory.apply(this);
  }
}
