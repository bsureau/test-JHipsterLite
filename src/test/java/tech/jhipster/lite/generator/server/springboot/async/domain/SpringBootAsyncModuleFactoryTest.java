package tech.jhipster.lite.generator.server.springboot.async.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModule;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class SpringBootAsyncModuleFactoryTest {

  private static final SpringBootAsyncModuleFactory factory = new SpringBootAsyncModuleFactory();

  @Test
  void shouldCreateModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModule(module)
      .hasJavaSources("tech/jhipster/jhlitest/wire/async/infrastructure/secondary/AsyncConfiguration.java")
      .hasFile("src/main/resources/config/application.yml")
      .containing(
        """
        spring:
          task:
            execution:
              pool:
                keep-alive: 10s
                max-size: 16
                queue-capacity: 100
              thread-name-prefix: myapp-task-
            scheduling:
              pool:
                size: 2
              thread-name-prefix: myapp-scheduling-
        """
      );
  }
}
