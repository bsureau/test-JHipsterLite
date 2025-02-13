package tech.jhipster.lite.generator.server.springboot.logging.logstash.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class LogstashModuleFactoryTest {

  private static final LogstashModuleFactory factory = new LogstashModuleFactory();

  @Test
  void shouldCreateModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile(), testLogbackFile())
      .hasFile("pom.xml")
      .containing(
        """
            <dependency>
              <groupId>net.logstash.logback</groupId>
              <artifactId>logstash-logback-encoder</artifactId>
              <version>${logstash-logback-encoder.version}</version>
            </dependency>
        """
      )
      .and()
      .hasPrefixedFiles(
        "src/main/java/tech/jhipster/jhlitest/wire/logstash/infrastructure/secondary",
        "LogstashTcpConfiguration.java",
        "LogstashTcpLifeCycle.java",
        "LogstashTcpProperties.java"
      )
      .hasPrefixedFiles(
        "src/test/java/tech/jhipster/jhlitest/wire/logstash/infrastructure/secondary",
        "LogstashTcpConfigurationIT.java",
        "LogstashTcpConfigurationTest.java",
        "LogstashTcpLifeCycleTest.java",
        "LogstashTcpPropertiesTest.java"
      )
      .hasFile("src/main/resources/config/application.yml")
      .containing(
        """
        application:
          logging:
            logstash:
              tcp:
                enabled: false
                host: localhost
                port: 5000
                ring-buffer-size: 8192
                shutdown_grace_period: PT1M
        """
      )
      .and()
      .hasFile("src/test/resources/logback.xml")
      .containing("<logger name=\"net.logstash.logback\" level=\"ERROR\" />")
      .containing("<logger name=\"org.jboss.logging\" level=\"WARN\" />");
  }
}
