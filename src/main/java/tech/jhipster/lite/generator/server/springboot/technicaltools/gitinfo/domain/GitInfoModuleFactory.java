package tech.jhipster.lite.generator.server.springboot.technicaltools.gitinfo.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.comment;
import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.mavenPlugin;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.pluginExecution;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyValue;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcMainJava;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.gradleplugin.GradleCommunityPlugin;
import tech.jhipster.lite.module.domain.gradleplugin.GradleMainBuildPlugin;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class GitInfoModuleFactory {

  private static final String PACKAGE_INFO = "package-info.java";

  private static final JHipsterSource SOURCE = from("server/springboot/technicaltools/gitinfo");
  private static final JHipsterSource MAIN_SOURCE = SOURCE.append("main");
  private static final String PRIMARY = "/infrastructure/primary";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String packagePath = properties.packagePath();
    JHipsterDestination mainDestination = toSrcMainJava().append(packagePath).append("wire/gitinfo");

    //@formatter:off
    return moduleBuilder(properties)
      .files()
        .add(
          MAIN_SOURCE.template("GitInfoConfiguration.java"),
          mainDestination.append(PRIMARY).append("GitInfoConfiguration.java")
        )
        .add(MAIN_SOURCE.template(PACKAGE_INFO), mainDestination.append(PACKAGE_INFO))
        .and()
      .springMainProperties()
        .comment(propertyKey("management.info.git"), comment("Git Information"))
        .set(propertyKey("management.info.git.mode"), propertyValue("full"))
        .set(propertyKey("management.info.git.enabled"), propertyValue(true))
        .set(propertyKey("management.info.env.enabled"), propertyValue(true))
        .and()
      .mavenPlugins()
        .plugin(gitCommitIdPlugin())
        .pluginManagement(gitCommitIdPluginManagement())
        .and()
      .gradlePlugins()
        .plugin(gradleGitPropertiesPlugin())
        .and()
      .build();
    //@formatter:on
  }

  private GradleMainBuildPlugin gradleGitPropertiesPlugin() {
    return GradleCommunityPlugin.builder()
      .id("com.gorylenko.gradle-git-properties")
      .pluginSlug("git-properties")
      .versionSlug("git-properties")
      .configuration(
        """
        gitProperties {
          failOnNoGitDirectory = false
          keys = listOf("git.branch", "git.commit.id.abbrev", "git.commit.id.describe", "git.build.version")
        }
        """
      )
      .build();
  }

  private MavenPlugin gitCommitIdPluginManagement() {
    return MavenPlugin.builder()
      .groupId("io.github.git-commit-id")
      .artifactId("git-commit-id-maven-plugin")
      .versionSlug("git-commit-id-plugin")
      .configuration(
        """
          <failOnNoGitDirectory>false</failOnNoGitDirectory>
          <failOnUnableToExtractRepoInfo>false</failOnUnableToExtractRepoInfo>
          <generateGitPropertiesFile>true</generateGitPropertiesFile>
          <includeOnlyProperties>
            <includeOnlyProperty>^git.commit.id.abbrev$</includeOnlyProperty>
            <includeOnlyProperty>^git.commit.id.describe$</includeOnlyProperty>
            <includeOnlyProperty>^git.branch$</includeOnlyProperty>
            <includeOnlyProperty>^git.build.(time|version)$</includeOnlyProperty>
          </includeOnlyProperties>
          <verbose>false</verbose>
        """
      )
      .addExecution(pluginExecution().goals("revision"))
      .build();
  }

  private MavenPlugin gitCommitIdPlugin() {
    return mavenPlugin().groupId("io.github.git-commit-id").artifactId("git-commit-id-maven-plugin").build();
  }
}
