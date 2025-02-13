package tech.jhipster.lite.generator.server.springboot.localeprofile.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.server.springboot.locale_profile.domain.LocalProfileModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class LocalProfileModuleFactoryTest {

  private static final LocalProfileModuleFactory factory = new LocalProfileModuleFactory();

  @Nested
  class Maven {

    @Test
    void shouldBuildModule() {
      JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

      JHipsterModule module = factory.buildModule(properties);

      assertThatModuleWithFiles(module, pomFile())
        .hasFile("pom.xml")
        .containing(
          """
            <profiles>
              <profile>
                <id>local</id>
                <activation>
                  <activeByDefault>true</activeByDefault>
                </activation>
                <properties>
                  <spring.profiles.active>local</spring.profiles.active>
                </properties>
              </profile>
            </profiles>
          """
        )
        .containing(
          """
                  <plugin>
                    <artifactId>maven-resources-plugin</artifactId>
                    <version>${maven-resources-plugin.version}</version>
                    <configuration>
                      <useDefaultDelimiters>false</useDefaultDelimiters>
                      <delimiters>
                        <delimiter>@</delimiter>
                      </delimiters>
                    </configuration>
                  </plugin>
          """
        )
        .and()
        .hasFile("src/main/resources/config/application.yml")
        .containing(
          """
          spring:
            profiles:
              active: '@spring.profiles.active@'
          """
        );
    }

    @Test
    void shouldReplaceCIActions() {
      JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

      JHipsterModule module = factory.buildModule(properties);

      assertThatModuleWithFiles(module, pomFile(), githubActionsBuild(), gitlabCI())
        .hasFile("pom.xml")
        .and()
        .hasFile(".github/workflows/github-actions.yml")
        .containing(
          """
          mvnw clean verify -P'!local' $MAVEN_CLI_OPTS
          """
        )
        .and()
        .hasFile(".gitlab-ci.yml")
        .containing(
          """
          mvnw clean verify -P'!local' $MAVEN_CLI_OPTS
          """
        );
    }

    private static ModuleFile githubActionsBuild() {
      return file("src/test/resources/projects/ci/github-actions-maven.yml", ".github/workflows/github-actions.yml");
    }

    private static ModuleFile gitlabCI() {
      return file("src/test/resources/projects/ci/.gitlab-ci-maven.yml", ".gitlab-ci.yml");
    }
  }

  @Nested
  class Gradle {

    @Test
    void shouldBuildModule() {
      JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

      JHipsterModule module = factory.buildModule(properties);

      assertThatModuleWithFiles(module, gradleBuildFile(), gradleLibsVersionFile())
        .hasFile("build.gradle.kts")
        .containing(
          """
          val springProfilesActive by extra("")
          // jhipster-needle-gradle-properties
          """
        )
        .containing(
          """
          val profiles = (project.findProperty("profiles") as String? ?: "")
            .split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
          if (profiles.isEmpty() || profiles.contains("local")) {
            apply(plugin = "profile-local")
          }
          // jhipster-needle-profile-activation\
          """
        )
        .containing(
          """
          tasks.build {
            dependsOn("processResources")
          }

          tasks.processResources {
            filesMatching("**/*.yml") {
              filter { it.replace("@spring.profiles.active@", springProfilesActive) }
            }
            filesMatching("**/*.properties") {
              filter { it.replace("@spring.profiles.active@", springProfilesActive) }
            }
          }

          // jhipster-needle-gradle-free-configuration-blocks\
          """
        )
        .and()
        .hasFile("buildSrc/src/main/kotlin/profile-local.gradle.kts")
        .containing(
          """
          val springProfilesActive by extra("local")
          // jhipster-needle-gradle-properties
          """
        )
        .and()
        .hasFile("src/main/resources/config/application.yml")
        .containing(
          """
          spring:
            profiles:
              active: '@spring.profiles.active@'
          """
        );
    }

    @Test
    void shouldReplaceCIActions() {
      JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

      JHipsterModule module = factory.buildModule(properties);

      assertThatModuleWithFiles(module, pomFile(), githubActionsBuild(), gitlabCI())
        .hasFile("pom.xml")
        .and()
        .hasFile(".github/workflows/github-actions.yml")
        .containing(
          """
          ./gradlew clean integrationTest -Pprofile=local --no-daemon
          """
        )
        .and()
        .hasFile(".gitlab-ci.yml")
        .containing(
          """
          ./gradlew clean integrationTest -Pprofile=local $GRADLE_CLI_OPTS
          """
        );
    }

    private static ModuleFile githubActionsBuild() {
      return file("src/test/resources/projects/ci/github-actions-gradle.yml", ".github/workflows/github-actions.yml");
    }

    private static ModuleFile gitlabCI() {
      return file("src/test/resources/projects/ci/.gitlab-ci-gradle.yml", ".gitlab-ci.yml");
    }
  }
}
