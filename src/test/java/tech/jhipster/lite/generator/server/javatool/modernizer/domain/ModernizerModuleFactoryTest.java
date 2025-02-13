package tech.jhipster.lite.generator.server.javatool.modernizer.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.gradleBuildFile;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.gradleLibsVersionFile;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class ModernizerModuleFactoryTest {

  private final ModernizerModuleFactory factory = new ModernizerModuleFactory();

  @Test
  void shouldBuildModuleForMaven() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.build(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing(
        """
                <plugin>
                  <groupId>org.gaul</groupId>
                  <artifactId>modernizer-maven-plugin</artifactId>
                  <version>${modernizer-maven-plugin.version}</version>
                  <executions>
                    <execution>
                      <id>modernizer</id>
                      <phase>verify</phase>
                      <goals>
                        <goal>modernizer</goal>
                      </goals>
                    </execution>
                  </executions>
                  <configuration>
                    <javaVersion>${java.version}</javaVersion>
                    <failOnViolations>true</failOnViolations>
                  </configuration>
                </plugin>\
        """
      )
      .containing(
        """
              <plugin>
                <groupId>org.gaul</groupId>
                <artifactId>modernizer-maven-plugin</artifactId>
              </plugin>\
        """
      );
  }

  @Test
  void shouldBuildModuleForGradle() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.build(properties);

    assertThatModuleWithFiles(module, gradleBuildFile(), gradleLibsVersionFile())
      .hasFile("gradle/libs.versions.toml")
      .containing(
        """
        \t[plugins.modernizer]
        \t\tid = "com.github.andygoossens.modernizer"

        \t\t[plugins.modernizer.version]
        \t\t\tref = "modernizer"
        """
      )
      .and()
      .hasFile("build.gradle.kts")
      .containing(
        """
          alias(libs.plugins.modernizer)
          // jhipster-needle-gradle-plugins
        """
      )
      .containing(
        """
        modernizer {
          failOnViolations = true
          includeTestClasses = true
        }
        """
      );
  }
}
