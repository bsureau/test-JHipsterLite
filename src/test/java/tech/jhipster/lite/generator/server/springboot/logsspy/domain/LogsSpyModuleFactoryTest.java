package tech.jhipster.lite.generator.server.springboot.logsspy.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class LogsSpyModuleFactoryTest {

  private static final LogsSpyModuleFactory factory = new LogsSpyModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFiles("documentation/logs-spy.md")
      .hasJavaTests(
        "tech/jhipster/jhlitest/Logs.java",
        "tech/jhipster/jhlitest/LogsSpy.java",
        "tech/jhipster/jhlitest/LogsSpyExtension.java"
      );
  }
}
