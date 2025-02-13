package tech.jhipster.lite.generator.server.springboot.apidocumentation.openapicontract.domain;

import static tech.jhipster.lite.TestFileUtils.tmpDirForTest;
import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.propertiesBuilder;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class OpenApiContractModuleFactoryTest {

  private static final OpenApiContractModuleFactory factory = new OpenApiContractModuleFactory();

  @Test
  void shouldBuildOpenApiContractModule() {
    JHipsterModuleProperties properties = propertiesBuilder(tmpDirForTest()).basePackage("tech.jhipster.jhlitest").build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing(
        // language=xml
        """
              <plugin>
                <groupId>io.github.kbuntrock</groupId>
                <artifactId>openapi-maven-plugin</artifactId>
              </plugin>\
        """
      )
      .containing(
        // language=xml
        """
                <plugin>
                  <groupId>io.github.kbuntrock</groupId>
                  <artifactId>openapi-maven-plugin</artifactId>
                  <version>${openapi-maven-plugin.version}</version>
                  <executions>
                    <execution>
                      <id>generate-openapi-contract</id>
                      <goals>
                        <goal>documentation</goal>
                      </goals>
                    </execution>
                  </executions>
                  <configuration>
                    <apiConfiguration>
                      <library>SPRING_MVC</library>
                    </apiConfiguration>
                    <apis>
                      <api>
                        <filename>openapi-contract.yml</filename>
                        <locations>
                          <location>tech.jhipster.jhlitest</location>
                        </locations>
                        <tag>
                          <substitutions>
                            <sub>
                              <regex>Resource$</regex>
                              <substitute />
                            </sub>
                          </substitutions>
                        </tag>
                      </api>
                    </apis>
                  </configuration>
                </plugin>\
        """
      );
  }

  @Test
  void shouldBuildOpenApiBackwardsCompatibilityCheckModule() {
    JHipsterModuleProperties properties = propertiesBuilder(tmpDirForTest()).build();

    JHipsterModule module = factory.buildBackwardsCompatibilityCheckModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing(
        // language=xml
        """
              <plugin>
                <groupId>io.kemtoa.openapi</groupId>
                <artifactId>openapi-backwards-compat-maven-plugin</artifactId>
              </plugin>\
        """
      )
      .containing(
        // language=xml
        """
                <plugin>
                  <groupId>io.kemtoa.openapi</groupId>
                  <artifactId>openapi-backwards-compat-maven-plugin</artifactId>
                  <version>${openapi-backwards-compat-maven-plugin.version}</version>
                  <executions>
                    <execution>
                      <goals>
                        <goal>backwards-compatibility-check</goal>
                      </goals>
                    </execution>
                  </executions>
                  <configuration>
                    <openApiSourceDir>${project.build.directory}</openApiSourceDir>
                  </configuration>
                </plugin>\
        """
      );
  }
}
