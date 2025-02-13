package tech.jhipster.lite.generator.setup.license.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;

import java.time.Year;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class LicenseModuleFactoryTest {

  private static final LicenseModuleFactory factory = new LicenseModuleFactory();

  @Test
  void shouldBuildMitModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();
    int year = Year.now(ZoneId.systemDefault()).getValue();

    JHipsterModule module = factory.buildMitModule(properties);

    assertThatModuleWithFiles(module).hasFile("LICENSE.txt").containing("Copyright %d".formatted(year));
  }

  @Test
  void shouldBuildApacheModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildApacheModule(properties);

    assertThatModuleWithFiles(module).hasFile("LICENSE.txt").containing("Apache License");
  }
}
