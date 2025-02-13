package tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class JwtBasicAuthModuleFactoryTest {

  private static final JwtBasicAuthModuleFactory factory = new JwtBasicAuthModuleFactory();

  @Test
  void shouldBuildBasicAuthModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .projectBaseName("jhipster")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFiles("documentation/jwt-basic-auth.md")
      .hasFile("src/main/resources/config/application.yml")
      .containing(
        """
        application:
          security:
            content-security-policy: 'default-src ''self''; frame-src ''self'' data:; script-src
              ''self'' ''unsafe-inline'' ''unsafe-eval'' https://storage.googleapis.com; style-src
              ''self'' ''unsafe-inline'' https://fonts.googleapis.com; img-src ''self'' data:;
              font-src ''self'' data: https://fonts.gstatic.com;'
            remember-me-token-validity: P365D
            token-validity: P1D
        spring:
          security:
            user:
              name: admin
              password: $2a$12$cRKS9ZURbdJIaRsKDTDUmOrH4.B.2rokv8rrkrQXr2IR2Hkna484O
              roles: ADMIN
        """
      )
      .and()
      .hasFiles("src/main/java/tech/jhipster/jhlitest/account/package-info.java")
      .hasFiles("src/main/java/tech/jhipster/jhlitest/account/application/AccountApplicationService.java")
      .hasPrefixedFiles(
        "src/main/java/tech/jhipster/jhlitest/account/domain",
        "AuthenticationQuery.java",
        "Token.java",
        "TokensRepository.java"
      )
      .hasPrefixedFiles(
        "src/main/java/tech/jhipster/jhlitest/account/infrastructure/primary",
        "AccountResource.java",
        "AuthenticationResource.java",
        "Authenticator.java",
        "RestAccount.java",
        "RestAuthenticationQuery.java",
        "RestToken.java"
      )
      .hasPrefixedFiles(
        "src/main/java/tech/jhipster/jhlitest/account/infrastructure/secondary",
        "JwtTokensConfiguration.java",
        "JwtTokensProperties.java",
        "JwtTokensRepository.java"
      )
      .hasFiles("src/test/java/tech/jhipster/jhlitest/account/domain/TokensFixture.java")
      .hasPrefixedFiles(
        "src/test/java/tech/jhipster/jhlitest/account/infrastructure/primary",
        "AccountResourceIT.java",
        "AuthenticationResourceIT.java",
        "RestAccountTest.java",
        "RestAuthenticationQueryTest.java",
        "RestTokenTest.java"
      );
  }
}
