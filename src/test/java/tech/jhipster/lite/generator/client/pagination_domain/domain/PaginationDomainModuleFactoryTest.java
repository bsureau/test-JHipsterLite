package tech.jhipster.lite.generator.client.pagination_domain.domain;

import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.*;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class PaginationDomainModuleFactoryTest {

  private static final TSPaginationDomainModuleFactory factory = new TSPaginationDomainModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, packageJsonFile()).hasPrefixedFiles(
      "src/main/webapp/app/shared/pagination/domain",
      "Page.ts",
      "DisplayedOnPage.ts"
    );
  }
}
