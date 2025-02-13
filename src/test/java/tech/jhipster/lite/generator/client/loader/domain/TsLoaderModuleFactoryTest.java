package tech.jhipster.lite.generator.client.loader.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class TsLoaderModuleFactoryTest {

  private static TsLoaderModuleFactory factory = new TsLoaderModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, packageJsonFile())
      .hasFile("src/main/webapp/app/shared/loader/infrastructure/primary/Loader.ts")
      .and()
      .hasFile("src/test/webapp/unit/shared/loader/infrastructure/primary/Loader.spec.ts");
  }
}
