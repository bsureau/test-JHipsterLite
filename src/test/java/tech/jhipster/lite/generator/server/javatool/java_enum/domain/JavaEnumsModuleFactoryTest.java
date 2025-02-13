package tech.jhipster.lite.generator.server.javatool.java_enum.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class JavaEnumsModuleFactoryTest {

  private static final JavaEnumsModuleFactory factory = new JavaEnumsModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModule(module)
      .hasFiles("src/main/java/tech/jhipster/jhlitest/shared/enumeration/package-info.java")
      .hasPrefixedFiles("src/main/java/tech/jhipster/jhlitest/shared/enumeration/domain/", "Enums.java", "UnmappableEnumException.java")
      .hasFiles("src/test/java/tech/jhipster/jhlitest/shared/enumeration/domain/EnumsTest.java");
  }
}
