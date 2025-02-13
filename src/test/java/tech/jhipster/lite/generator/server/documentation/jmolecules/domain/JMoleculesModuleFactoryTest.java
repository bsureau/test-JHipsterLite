package tech.jhipster.lite.generator.server.documentation.jmolecules.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class JMoleculesModuleFactoryTest {

  private static final JMoleculesModuleFactory factory = new JMoleculesModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing("<jmolecules-bom.version>")
      .containing(
        """
              <dependency>
                <groupId>org.jmolecules</groupId>
                <artifactId>jmolecules-bom</artifactId>
                <version>${jmolecules-bom.version}</version>
                <type>pom</type>
                <scope>import</scope>
              </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>org.jmolecules</groupId>
              <artifactId>jmolecules-ddd</artifactId>
            </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>org.jmolecules</groupId>
              <artifactId>jmolecules-hexagonal-architecture</artifactId>
            </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>org.jmolecules</groupId>
              <artifactId>jmolecules-events</artifactId>
            </dependency>
        """
      );
  }
}
