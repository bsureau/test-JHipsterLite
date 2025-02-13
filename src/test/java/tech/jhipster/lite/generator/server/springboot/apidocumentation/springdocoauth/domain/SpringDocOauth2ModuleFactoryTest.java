package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdocoauth.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class SpringDocOauth2ModuleFactoryTest {

  private static final SpringDocOauth2ModuleFactory factory = new SpringDocOauth2ModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .put("keycloakRealmName", "beer")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    //@formatter:off
    assertThatModuleWithFiles(module, pomFile())
      .hasFiles("src/main/java/tech/jhipster/jhlitest/wire/springdoc/infrastructure/primary/SpringdocOAuth2Configuration.java")
      .hasFile("src/main/resources/config/application.yml")
      .containing(
        """
        springdoc:
          oauth2:
            authorization-url: http://localhost:9080/realms/beer/protocol/openid-connect/auth
          swagger-ui:
            oauth:
              client-id: web_app
              realm: beer
        """
      )
      .and()
      .hasFile("src/test/resources/config/application-test.yml")
      .containing(
        """
        springdoc:
          oauth2:
            authorization-url: http://localhost:9080/realms/beer/protocol/openid-connect/auth
          swagger-ui:
            oauth:
              client-id: web_app
              realm: beer
        """
      );
    //@formatter:on
  }
}
