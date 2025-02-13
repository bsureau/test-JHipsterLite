package tech.jhipster.lite.module.domain.javabuildprofile;

import java.util.Optional;
import tech.jhipster.lite.module.domain.buildproperties.JHipsterModuleBuildProperties;
import tech.jhipster.lite.module.domain.buildproperties.JHipsterModuleBuildProperties.JHipsterModuleBuildPropertiesBuilder;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileActivation.BuildProfileActivationBuilder;
import tech.jhipster.lite.module.domain.javabuildprofile.JHipsterModuleGradleProfilePlugins.JHipsterModuleGradleProfilePluginBuilder;
import tech.jhipster.lite.module.domain.javabuildprofile.JHipsterModuleJavaBuildProfiles.JHipsterModuleJavaBuildProfilesBuilder;
import tech.jhipster.lite.module.domain.javadependency.JHipsterModuleJavaDependencies;
import tech.jhipster.lite.module.domain.javadependency.JHipsterModuleJavaDependencies.JHipsterModuleJavaDependenciesBuilder;
import tech.jhipster.lite.module.domain.mavenplugin.JHipsterModuleMavenPlugins;
import tech.jhipster.lite.module.domain.mavenplugin.JHipsterModuleMavenPlugins.JHipsterModuleMavenPluginsBuilder;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModuleJavaBuildProfile {

  private final BuildProfileId buildProfileId;
  private final Optional<BuildProfileActivation> activation;
  private final JHipsterModuleBuildProperties properties;
  private final JHipsterModuleMavenPlugins mavenPlugins;
  private final JHipsterModuleGradleProfilePlugins gradleProfilePlugins;
  private final JHipsterModuleJavaDependencies javaDependencies;

  private JHipsterModuleJavaBuildProfile(JHipsterModuleJavaBuildProfileBuilder builder) {
    Assert.notNull("buildProfileId", builder.buildProfileId);
    Assert.notNull("propertiesBuilder", builder.propertiesBuilder);
    Assert.notNull("mavenPluginsBuilder", builder.mavenPluginsBuilder);
    Assert.notNull("gradlePluginsBuilder", builder.gradleProfilePluginsBuilder);
    Assert.notNull("javaDependenciesBuilder", builder.javaDependenciesBuilder);
    this.buildProfileId = builder.buildProfileId;
    this.activation = Optional.ofNullable(builder.activation);
    this.properties = builder.propertiesBuilder.build();
    this.mavenPlugins = builder.mavenPluginsBuilder.build();
    this.gradleProfilePlugins = builder.gradleProfilePluginsBuilder.build();
    this.javaDependencies = builder.javaDependenciesBuilder.build();
  }

  public static JHipsterModuleJavaBuildProfileBuilder builder(
    JHipsterModuleJavaBuildProfilesBuilder profiles,
    BuildProfileId buildProfileId
  ) {
    return new JHipsterModuleJavaBuildProfileBuilder(profiles, buildProfileId);
  }

  public BuildProfileId id() {
    return buildProfileId;
  }

  public Optional<BuildProfileActivation> activation() {
    return activation;
  }

  public JHipsterModuleBuildProperties properties() {
    return properties;
  }

  public JHipsterModuleMavenPlugins mavenPlugins() {
    return mavenPlugins;
  }

  public JHipsterModuleGradleProfilePlugins gradlePlugins() {
    return gradleProfilePlugins;
  }

  public JHipsterModuleJavaDependencies javaDependencies() {
    return javaDependencies;
  }

  public static final class JHipsterModuleJavaBuildProfileBuilder {

    private final JHipsterModuleJavaBuildProfilesBuilder profiles;
    private final BuildProfileId buildProfileId;
    private BuildProfileActivation activation;
    private final JHipsterModuleBuildPropertiesBuilder<JHipsterModuleJavaBuildProfileBuilder> propertiesBuilder =
      JHipsterModuleBuildProperties.builder(this);
    private final JHipsterModuleMavenPluginsBuilder<JHipsterModuleJavaBuildProfileBuilder> mavenPluginsBuilder =
      JHipsterModuleMavenPlugins.builder(this);
    private final JHipsterModuleGradleProfilePluginBuilder gradleProfilePluginsBuilder = JHipsterModuleGradleProfilePlugins.builder(this);
    private final JHipsterModuleJavaDependenciesBuilder<JHipsterModuleJavaBuildProfileBuilder> javaDependenciesBuilder =
      JHipsterModuleJavaDependencies.builder(this);

    private JHipsterModuleJavaBuildProfileBuilder(JHipsterModuleJavaBuildProfilesBuilder profiles, BuildProfileId buildProfileId) {
      Assert.notNull("profiles", profiles);
      Assert.notNull("buildProfileId", buildProfileId);

      this.profiles = profiles;
      this.buildProfileId = buildProfileId;
    }

    public JHipsterModuleJavaBuildProfilesBuilder and() {
      return profiles;
    }

    public JHipsterModuleJavaBuildProfile build() {
      return new JHipsterModuleJavaBuildProfile(this);
    }

    public JHipsterModuleJavaBuildProfileBuilder activation(BuildProfileActivation activation) {
      Assert.notNull("activation", activation);
      this.activation = activation;

      return this;
    }

    public JHipsterModuleJavaBuildProfileBuilder activation(BuildProfileActivationBuilder activationBuilder) {
      Assert.notNull("activationBuilder", activationBuilder);

      return activation(activationBuilder.build());
    }

    public JHipsterModuleBuildPropertiesBuilder<JHipsterModuleJavaBuildProfileBuilder> properties() {
      return propertiesBuilder;
    }

    public JHipsterModuleMavenPluginsBuilder<JHipsterModuleJavaBuildProfileBuilder> mavenPlugins() {
      return mavenPluginsBuilder;
    }

    public JHipsterModuleGradleProfilePluginBuilder gradleProfilePlugins() {
      return gradleProfilePluginsBuilder;
    }

    public JHipsterModuleJavaDependenciesBuilder<JHipsterModuleJavaBuildProfileBuilder> javaDependencies() {
      return javaDependenciesBuilder;
    }
  }
}
