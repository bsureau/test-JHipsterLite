package tech.jhipster.lite.generator.server.springboot.technicaltools.gitinfo.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class GitInfoModuleFactoryTest {

  private static final GitInfoModuleFactory factory = new GitInfoModuleFactory();

  @Nested
  class Maven {

    @Test
    void shouldAddGitInformation() {
      JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
        .basePackage("tech.jhipster.myapp")
        .projectBaseName("myapp")
        .build();

      JHipsterModule module = factory.buildModule(properties);

      //@formatter:off
    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
        .containing(
          """
                <plugin>
                  <groupId>io.github.git-commit-id</groupId>
                  <artifactId>git-commit-id-maven-plugin</artifactId>
                </plugin>
          """
        )
        .and()
      .hasFile("src/main/resources/config/application.yml")
        .containing(
          """
          management:
            info:
              env:
                enabled: true
              # Git Information
              git:
                enabled: true
                mode: full
          """
        )
        .and()
      .hasPrefixedFiles("src/main/java/tech/jhipster/myapp/wire/gitinfo", "infrastructure/primary/GitInfoConfiguration.java", "package-info.java");
      //@formatter:on
    }
  }

  @Nested
  class Gradle {

    @Test
    void shouldAddGitInformation() {
      JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
        .basePackage("tech.jhipster.myapp")
        .projectBaseName("myapp")
        .build();

      JHipsterModule module = factory.buildModule(properties);

      assertThatModuleWithFiles(module, gradleBuildFile(), gradleLibsVersionFile())
        .hasFile("gradle/libs.versions.toml")
        .containing(
          """
          id = "com.gorylenko.gradle-git-properties"
          """
        )
        .and()
        .hasFile("build.gradle.kts")
        .containing(
          """
          alias(libs.plugins.git.properties)
                  """
        );
    }
  }
}
