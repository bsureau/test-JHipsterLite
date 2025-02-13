package tech.jhipster.lite.generator.server.springboot.springcloud.consul.domain;

import static org.mockito.Mockito.when;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.docker.DockerImageVersion;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
@ExtendWith(MockitoExtension.class)
class ConsulModuleFactoryTest {

  @Mock
  private DockerImages dockerImages;

  @InjectMocks
  private ConsulModuleFactory factory;

  @Test
  void shouldCreateModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.burger")
      .projectBaseName("burger")
      .build();
    when(dockerImages.get("consul")).thenReturn(new DockerImageVersion("consul", "1.12.2"));
    when(dockerImages.get("jhipster/consul-config-loader")).thenReturn(new DockerImageVersion("jhipster/consul-config-loader", "v0.4.1"));

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile(), logbackFile(), testLogbackFile(), readmeFile())
      .hasFile("src/main/docker/consul.yml")
      .containing("consul:1.12.2")
      .containing("jhipster/consul-config-loader:v0.4.1")
      .and()
      .hasFile("src/main/docker/central-server-config/application.yml")
      .and()
      .hasFile("pom.xml")
      .containing(
        """
              <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
              </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-bootstrap</artifactId>
            </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-consul-discovery</artifactId>
            </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-consul-config</artifactId>
            </dependency>
        """
      )
      .and()
      .hasFile("src/main/resources/config/bootstrap.yml")
      .containing(
        """
        spring:
          application:
            name: burger
          cloud:
            compatibility-verifier:
              enabled: false
            consul:
              config:
                format: yaml
                profile-separator: '-'
                watch:
                  enabled: false
              discovery:
                health-check-path: ${server.servlet.context-path:}/management/health
                instance-id: burger:${spring.application.instance-id:${random.value}}
                prefer-ip-address: true
                service-name: burger
                tags[0]: version=@project.version@
                tags[1]: context-path=${server.servlet.context-path:}
                tags[2]: profile=${spring.profiles.active:}
                tags[3]: git-version=${git.build.version:}
                tags[4]: git-commit=${git.commit.id.abbrev:}
                tags[5]: git-branch=${git.branch:}
              host: localhost
              port: 8500
        """
      )
      .and()
      .hasFile("src/test/resources/config/bootstrap.yml")
      .containing(
        """
        spring:
          cloud:
            compatibility-verifier:
              enabled: false
            consul:
              enabled: false
        """
      )
      .and()
      .hasFile("README.md")
      .containing(
        """
        ```bash
        docker compose -f src/main/docker/consul.yml up -d
        ```
        """
      )
      .and()
      .hasFile("src/main/resources/logback-spring.xml")
      .containing("  <logger name=\"org.apache\" level=\"ERROR\" />")
      .and()
      .hasFile("src/test/resources/logback.xml")
      .containing("  <logger name=\"org.apache\" level=\"ERROR\" />");
  }
}
