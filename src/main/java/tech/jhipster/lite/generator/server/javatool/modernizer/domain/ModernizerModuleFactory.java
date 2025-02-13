package tech.jhipster.lite.generator.server.javatool.modernizer.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.gradleCommunityPlugin;
import static tech.jhipster.lite.module.domain.JHipsterModule.mavenPlugin;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.pluginExecution;
import static tech.jhipster.lite.module.domain.mavenplugin.MavenBuildPhase.VERIFY;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.gradleplugin.GradleMainBuildPlugin;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class ModernizerModuleFactory {

  private static final String MODERNIZER = "modernizer";

  public JHipsterModule build(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .mavenPlugins()
        .pluginManagement(modernizerMavenPluginManagement())
        .plugin(modernizerMavenPlugin().build())
        .and()
      .gradlePlugins()
        .plugin(modernizerGradlePlugin())
        .and()
      .build();
    //@formatter:on
  }

  private MavenPlugin.MavenPluginOptionalBuilder modernizerMavenPlugin() {
    return mavenPlugin().groupId("org.gaul").artifactId("modernizer-maven-plugin");
  }

  private MavenPlugin modernizerMavenPluginManagement() {
    return modernizerMavenPlugin()
      .versionSlug("modernizer-maven-plugin")
      .configuration(
        """
        <javaVersion>${java.version}</javaVersion>
        <failOnViolations>true</failOnViolations>
        """
      )
      .addExecution(pluginExecution().goals(MODERNIZER).id(MODERNIZER).phase(VERIFY))
      .build();
  }

  private GradleMainBuildPlugin modernizerGradlePlugin() {
    return gradleCommunityPlugin()
      .id("com.github.andygoossens.modernizer")
      .pluginSlug(MODERNIZER)
      .versionSlug(MODERNIZER)
      .configuration(
        """
        modernizer {
          failOnViolations = true
          includeTestClasses = true
        }
        """
      )
      .build();
  }
}
