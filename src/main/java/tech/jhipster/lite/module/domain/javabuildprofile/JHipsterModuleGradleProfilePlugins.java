package tech.jhipster.lite.module.domain.javabuildprofile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import tech.jhipster.lite.module.domain.gradleplugin.GradleCommunityProfilePlugin;
import tech.jhipster.lite.module.domain.gradleplugin.GradleCorePlugin;
import tech.jhipster.lite.module.domain.gradleplugin.GradleProfilePlugin;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradlePlugin;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradlePlugin.AddGradlePluginOptionalBuilder;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommand;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;
import tech.jhipster.lite.module.domain.javabuildprofile.JHipsterModuleJavaBuildProfile.JHipsterModuleJavaBuildProfileBuilder;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesVersions;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModuleGradleProfilePlugins {

  private final Collection<GradleProfilePlugin> plugins;

  public JHipsterModuleGradleProfilePlugins(JHipsterModuleGradleProfilePluginBuilder builder) {
    Assert.notNull("builder", builder);

    this.plugins = builder.plugins;
  }

  public static JHipsterModuleGradleProfilePluginBuilder builder(JHipsterModuleJavaBuildProfileBuilder module) {
    return new JHipsterModuleGradleProfilePluginBuilder(module);
  }

  public JavaBuildCommands buildChanges(JavaDependenciesVersions versions, BuildProfileId buildProfile) {
    Assert.notNull("versions", versions);
    Assert.notNull("buildProfile", buildProfile);

    return new JavaBuildCommands(plugins.stream().map(toCommands(versions, Optional.of(buildProfile))).toList());
  }

  private Function<GradleProfilePlugin, JavaBuildCommand> toCommands(
    JavaDependenciesVersions versions,
    Optional<BuildProfileId> buildProfile
  ) {
    return plugin ->
      switch (plugin) {
        case GradleCorePlugin gradleCorePlugin -> mapCorePlugin(gradleCorePlugin, versions, buildProfile);
        case GradleCommunityProfilePlugin gradleCommunityProfilePlugin -> mapCommunityProfilePlugin(
          gradleCommunityProfilePlugin,
          versions,
          buildProfile
        );
      };
  }

  private JavaBuildCommand mapCorePlugin(
    GradleCorePlugin plugin,
    JavaDependenciesVersions versions,
    Optional<BuildProfileId> buildProfile
  ) {
    AddGradlePluginOptionalBuilder commandBuilder = AddGradlePlugin.builder().plugin(plugin);
    buildProfile.ifPresent(commandBuilder::buildProfile);
    plugin.toolVersionSlug().map(versions::get).ifPresent(commandBuilder::toolVersion);
    return commandBuilder.build();
  }

  private JavaBuildCommand mapCommunityProfilePlugin(
    GradleCommunityProfilePlugin gradleCommunityProfilePlugin,
    JavaDependenciesVersions versions,
    Optional<BuildProfileId> buildProfile
  ) {
    AddGradlePluginOptionalBuilder commandBuilder = AddGradlePlugin.builder().plugin(gradleCommunityProfilePlugin);
    buildProfile.ifPresent(commandBuilder::buildProfile);
    gradleCommunityProfilePlugin.versionSlug().map(versions::get).ifPresent(commandBuilder::pluginVersion);
    return commandBuilder.build();
  }

  public static final class JHipsterModuleGradleProfilePluginBuilder {

    private final JHipsterModuleJavaBuildProfileBuilder module;
    private final Collection<GradleProfilePlugin> plugins = new ArrayList<>();

    private JHipsterModuleGradleProfilePluginBuilder(JHipsterModuleJavaBuildProfileBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
    }

    public JHipsterModuleGradleProfilePluginBuilder plugin(GradleProfilePlugin plugin) {
      Assert.notNull("plugin", plugin);

      plugins.add(plugin);

      return this;
    }

    public JHipsterModuleJavaBuildProfileBuilder and() {
      return module;
    }

    public JHipsterModuleGradleProfilePlugins build() {
      return new JHipsterModuleGradleProfilePlugins(this);
    }
  }
}
