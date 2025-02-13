package tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class MongockModuleFactoryTest {

  private static final MongockModuleFactory factory = new MongockModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFiles("documentation/mongock.md")
      .hasFile("pom.xml")
      .containing(
        """
              <dependency>
                <groupId>io.mongock</groupId>
                <artifactId>mongock-bom</artifactId>
                <version>${mongock.version}</version>
                <type>pom</type>
                <scope>import</scope>
              </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>io.mongock</groupId>
              <artifactId>mongock-springboot</artifactId>
            </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>io.mongock</groupId>
              <artifactId>mongodb-springdata-v4-driver</artifactId>
            </dependency>
        """
      )
      .and()
      .hasFiles("src/main/java/tech/jhipster/jhlitest/wire/mongock/infrastructure/secondary/MongockDatabaseConfiguration.java")
      .hasFile("src/main/resources/config/application.yml")
      .containing(
        """
        mongock:
          migration-scan-package: tech.jhipster.jhlitest
        """
      );
  }
}
