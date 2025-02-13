package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradlePlugin;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradlePlugin.AddGradlePluginOptionalBuilder;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommand;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesVersions;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModuleGradlePlugins {

  private final Collection<GradleMainBuildPlugin> plugins;

  private JHipsterModuleGradlePlugins(JHipsterModuleGradlePluginBuilder builder) {
    Assert.notNull("plugins", builder.plugins);
    plugins = builder.plugins;
  }

  public static JHipsterModuleGradlePluginBuilder builder(JHipsterModuleBuilder module) {
    return new JHipsterModuleGradlePluginBuilder(module);
  }

  public JavaBuildCommands buildChanges(JavaDependenciesVersions versions) {
    Assert.notNull("versions", versions);

    return new JavaBuildCommands(plugins.stream().map(toCommands(versions)).toList());
  }

  private Function<GradleMainBuildPlugin, JavaBuildCommand> toCommands(JavaDependenciesVersions versions) {
    return plugin ->
      switch (plugin) {
        case GradleCorePlugin corePlugin -> mapCorePlugin(corePlugin, versions);
        case GradleCommunityPlugin gradleCommunityPlugin -> mapCommunityPlugin(gradleCommunityPlugin, versions);
      };
  }

  private JavaBuildCommand mapCorePlugin(GradleCorePlugin plugin, JavaDependenciesVersions versions) {
    AddGradlePluginOptionalBuilder commandBuilder = AddGradlePlugin.builder().plugin(plugin);
    plugin.toolVersionSlug().map(versions::get).ifPresent(commandBuilder::toolVersion);
    return commandBuilder.build();
  }

  private JavaBuildCommand mapCommunityPlugin(GradleCommunityPlugin plugin, JavaDependenciesVersions versions) {
    AddGradlePluginOptionalBuilder commandBuilder = AddGradlePlugin.builder().plugin(plugin);
    plugin.versionSlug().map(versions::get).ifPresent(commandBuilder::pluginVersion);
    return commandBuilder.build();
  }

  public static final class JHipsterModuleGradlePluginBuilder {

    private final JHipsterModuleBuilder module;
    private final Collection<GradleMainBuildPlugin> plugins = new ArrayList<>();

    private JHipsterModuleGradlePluginBuilder(JHipsterModuleBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
    }

    public JHipsterModuleGradlePluginBuilder plugin(GradleMainBuildPlugin plugin) {
      Assert.notNull("plugin", plugin);

      plugins.add(plugin);

      return this;
    }

    public JHipsterModuleBuilder and() {
      return module;
    }

    public JHipsterModuleGradlePlugins build() {
      return new JHipsterModuleGradlePlugins(this);
    }
  }
}
