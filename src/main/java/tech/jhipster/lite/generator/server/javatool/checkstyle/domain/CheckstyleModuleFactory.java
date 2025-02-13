package tech.jhipster.lite.generator.server.javatool.checkstyle.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.gradleCorePlugin;
import static tech.jhipster.lite.module.domain.JHipsterModule.javaDependency;
import static tech.jhipster.lite.module.domain.JHipsterModule.mavenPlugin;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.pluginExecution;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;
import static tech.jhipster.lite.module.domain.mavenplugin.MavenBuildPhase.VALIDATE;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.gradleplugin.GradleMainBuildPlugin;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class CheckstyleModuleFactory {

  private static final JHipsterSource TEMPLATES_SOURCE = from("server/javatool/checkstyle/main");
  private static final String CHECKSTYLE = "checkstyle";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .mavenPlugins()
        .plugin(checkstyleMavenPlugin())
        .and()
      .gradlePlugins()
        .plugin(checkstyleGradlePlugin())
        .and()
      .files()
        .add(TEMPLATES_SOURCE.template("checkstyle.xml"), to("checkstyle.xml"))
        .and()
      .build();
    //@formatter:on
  }

  private MavenPlugin checkstyleMavenPlugin() {
    return mavenPlugin()
      .groupId("org.apache.maven.plugins")
      .artifactId("maven-checkstyle-plugin")
      .versionSlug("maven-checkstyle-plugin")
      .configuration(
        """
        <configLocation>checkstyle.xml</configLocation>
        <includeTestSourceDirectory>true</includeTestSourceDirectory>
        <consoleOutput>true</consoleOutput>
        <failsOnError>true</failsOnError>
        """
      )
      .addDependency(checkstyleDependency())
      .addExecution(pluginExecution().goals("check").id("validate").phase(VALIDATE))
      .build();
  }

  private JavaDependency checkstyleDependency() {
    return javaDependency().groupId("com.puppycrawl.tools").artifactId(CHECKSTYLE).versionSlug(CHECKSTYLE).build();
  }

  private GradleMainBuildPlugin checkstyleGradlePlugin() {
    VersionSlug toolVersionSlug = new VersionSlug(CHECKSTYLE);
    return gradleCorePlugin()
      .id(CHECKSTYLE)
      .toolVersionSlug(toolVersionSlug)
      .configuration(
        """
        checkstyle {
          configFile = rootProject.file("checkstyle.xml")
          toolVersion = libs.versions.%s.get()
        }
        """.formatted(toolVersionSlug.slug())
      )
      .build();
  }
}
