package tech.jhipster.lite.generator.client.vue.security.jwt.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class VueJwtModulesFactoryTest {

  private static final VueJwtModulesFactory factory = new VueJwtModulesFactory();

  @Test
  void shouldBuildVueJwtModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .projectBaseName("jhipster")
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    //@formatter:off
    assertThatModuleWithFiles(module, packageJsonFile(), mainFile())
      .hasFiles("documentation/vue-jwt-authentication-components.md")
      .hasFiles("src/main/webapp/app/auth/application/AuthProvider.ts")
      .hasFiles("src/main/webapp/app/auth/domain/AuthRepository.ts")
      .hasFiles("src/main/webapp/app/auth/domain/AuthenticatedUser.ts")
      .hasFiles("src/main/webapp/app/auth/domain/Authentication.ts")
      .hasFiles("src/main/webapp/app/auth/domain/LoginCredentials.ts")
      .hasFiles("src/main/webapp/app/auth/infrastructure/secondary/JwtAuthRepository.ts")
      .hasFiles("src/main/webapp/app/auth/infrastructure/secondary/RestAuthentication.ts")
      .hasFiles("src/main/webapp/app/auth/infrastructure/secondary/RestLoginCredentials.ts")
      .hasFile("src/main/webapp/app/main.ts")
        .containing("""
          import { provideForAuth } from '@/auth/application/AuthProvider';
          """
        )
        .containing("""
          import { AxiosHttp } from '@/shared/http/infrastructure/secondary/AxiosHttp';
          """
        )
        .containing("""
          import axios from 'axios';
          """
        )
        .containing("""
          const axiosInstance = axios.create({ baseURL: 'http://localhost:8080/' });
          """
        )
        .containing("""
          const axiosHttp = new AxiosHttp(axiosInstance);
          """
        )
        .containing("""
          provideForAuth(axiosHttp);
          """
        )
        .and()
      .hasFiles("src/test/webapp/unit/auth/application/AuthProvider.spec.ts")
      .hasFiles("src/test/webapp/unit/auth/infrastructure/secondary/JwtAuthRepository.spec.ts")
      .hasFiles("src/test/webapp/unit/shared/http/infrastructure/secondary/AxiosHttpStub.ts");
    //@formatter:on
  }

  private static ModuleFile mainFile() {
    return file("src/test/resources/projects/vue/main.ts.template", "src/main/webapp/app/main.ts");
  }
}
