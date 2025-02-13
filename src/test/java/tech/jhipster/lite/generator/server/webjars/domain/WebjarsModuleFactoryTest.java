package tech.jhipster.lite.generator.server.webjars.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class WebjarsModuleFactoryTest {

  private static final WebjarsModuleFactory factory = new WebjarsModuleFactory();

  @Test
  void shouldCreateWebjarsLocatorModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildWebjarsLocatorModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing(
        """
            <dependency>
              <groupId>org.webjars</groupId>
              <artifactId>webjars-locator</artifactId>
              <version>${webjars-locator.version}</version>
            </dependency>
        """
      );
  }

  @Test
  void shouldCreateHtmxWebjarsModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildWebjarsHtmxModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing(
        """
            <dependency>
              <groupId>org.webjars.npm</groupId>
              <artifactId>htmx.org</artifactId>
              <version>${htmx-webjars.version}</version>
            </dependency>
        """
      );
  }

  @Test
  void shouldCreateAlpineJSWebjarsModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildWebjarsAlpineJSModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing(
        """
            <dependency>
              <groupId>org.webjars.npm</groupId>
              <artifactId>alpinejs</artifactId>
              <version>${alpinejs-webjars.version}</version>
            </dependency>
        """
      );
  }
}
