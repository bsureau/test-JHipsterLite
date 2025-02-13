package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Optional;
import tech.jhipster.lite.module.domain.gradleplugin.GradlePlugin;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyVersion;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class AddGradlePlugin implements JavaBuildCommand {

  private final GradlePlugin plugin;
  private final Optional<JavaDependencyVersion> pluginVersion;
  private final Optional<JavaDependencyVersion> toolVersion;
  private final Optional<BuildProfileId> buildProfile;

  private AddGradlePlugin(AddGradlePlugin.AddGradlePluginBuilder builder) {
    Assert.notNull("plugin", builder.plugin);
    this.plugin = builder.plugin;
    this.pluginVersion = Optional.ofNullable(builder.pluginVersion);
    this.toolVersion = Optional.ofNullable(builder.toolVersion);
    this.buildProfile = Optional.ofNullable(builder.buildProfile);
  }

  public GradlePlugin plugin() {
    return plugin;
  }

  public Optional<JavaDependencyVersion> pluginVersion() {
    return pluginVersion;
  }

  public Optional<JavaDependencyVersion> toolVersion() {
    return toolVersion;
  }

  public Optional<BuildProfileId> buildProfile() {
    return buildProfile;
  }

  public static AddGradlePluginPluginBuilder builder() {
    return new AddGradlePluginBuilder();
  }

  private static final class AddGradlePluginBuilder implements AddGradlePluginPluginBuilder, AddGradlePluginOptionalBuilder {

    private GradlePlugin plugin;
    private JavaDependencyVersion pluginVersion;
    private JavaDependencyVersion toolVersion;
    private BuildProfileId buildProfile;

    @Override
    public AddGradlePluginBuilder plugin(GradlePlugin plugin) {
      this.plugin = plugin;
      return this;
    }

    @Override
    public AddGradlePluginOptionalBuilder pluginVersion(JavaDependencyVersion pluginVersion) {
      this.pluginVersion = pluginVersion;
      return this;
    }

    @Override
    public AddGradlePluginOptionalBuilder toolVersion(JavaDependencyVersion toolVersion) {
      this.toolVersion = toolVersion;
      return this;
    }

    @Override
    public AddGradlePluginOptionalBuilder buildProfile(BuildProfileId buildProfile) {
      this.buildProfile = buildProfile;
      return this;
    }

    @Override
    public AddGradlePlugin build() {
      return new AddGradlePlugin(this);
    }
  }

  public interface AddGradlePluginPluginBuilder {
    AddGradlePluginOptionalBuilder plugin(GradlePlugin plugin);
  }

  public interface AddGradlePluginOptionalBuilder {
    AddGradlePluginOptionalBuilder pluginVersion(JavaDependencyVersion version);

    AddGradlePluginOptionalBuilder toolVersion(JavaDependencyVersion version);

    AddGradlePluginOptionalBuilder buildProfile(BuildProfileId buildProfile);

    AddGradlePlugin build();
  }
}
