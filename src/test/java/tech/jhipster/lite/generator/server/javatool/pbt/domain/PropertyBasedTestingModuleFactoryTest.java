package tech.jhipster.lite.generator.server.javatool.pbt.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class PropertyBasedTestingModuleFactoryTest {

  private final PropertyBasedTestingModuleFactory factory = new PropertyBasedTestingModuleFactory();

  @Test
  void shouldBuildJqwikModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.build(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFiles("documentation/property-based-testing.md")
      .hasFile(".gitignore")
      .containing(
        """
        # JQwik
        .jqwik-database\
        """
      )
      .and()
      .hasFile("pom.xml")
      .containing(
        """
            <dependency>
              <groupId>net.jqwik</groupId>
              <artifactId>jqwik</artifactId>
              <version>${jqwik.version}</version>
              <scope>test</scope>
            </dependency>
        """
      );
  }
}
