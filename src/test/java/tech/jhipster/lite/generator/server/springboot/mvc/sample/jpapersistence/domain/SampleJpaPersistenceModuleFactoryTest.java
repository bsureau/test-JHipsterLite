package tech.jhipster.lite.generator.server.springboot.mvc.sample.jpapersistence.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class SampleJpaPersistenceModuleFactoryTest {

  private static final SampleJpaPersistenceModuleFactory factory = new SampleJpaPersistenceModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, beersApplicationService(), sampleInMemoryRepository(), inMemoryBeersResetter())
      .hasPrefixedFiles(
        "src/main/java/tech/jhipster/jhlitest/sample/infrastructure/secondary",
        "BeerEntity.java",
        "JpaBeersRepository.java",
        "SpringDataBeersRepository.java"
      )
      .hasPrefixedFiles(
        "src/test/java/tech/jhipster/jhlitest/sample/infrastructure/secondary",
        "BeerEntityTest.java",
        "JpaBeersRepositoryIT.java"
      )
      .hasFile("src/main/java/tech/jhipster/jhlitest/sample/application/BeersApplicationService.java")
      .containing("import org.springframework.transaction.annotation.Transactional;")
      .containing(
        """
          @Transactional(readOnly = true)
          public Beers catalog() {
        """
      )
      .containing(
        """
          @Transactional
          @PreAuthorize("can('create', #beerToCreate)")
          public Beer create(BeerToCreate beerToCreate) {
        """
      )
      .containing(
        """
          @Transactional
          @PreAuthorize("can('remove', #beer)")
          public void remove(BeerId beer) {
        """
      )
      .and()
      .doNotHaveFiles(
        "src/main/java/tech/jhipster/jhlitest/sample/infrastructure/secondary/InMemoryBeersRepository.java",
        "src/test/java/tech/jhipster/jhlitest/sample/infrastructure/secondary/InMemoryBeersResetter.java"
      );
  }

  private ModuleFile beersApplicationService() {
    return file(
      "src/test/resources/projects/sample-feature/BeersApplicationService.java",
      "src/main/java/tech/jhipster/jhlitest/sample/application/BeersApplicationService.java"
    );
  }

  private ModuleFile sampleInMemoryRepository() {
    return file(
      "src/test/resources/projects/sample-feature/InMemoryBeersRepository.java",
      "src/main/java/tech/jhipster/jhlitest/sample/infrastructure/secondary/InMemoryBeersRepository.java"
    );
  }

  private ModuleFile inMemoryBeersResetter() {
    return file(
      "src/test/resources/projects/sample-feature/InMemoryBeersResetter.java",
      "src/test/java/tech/jhipster/jhlitest/sample/infrastructure/secondary/InMemoryBeersResetter.java"
    );
  }
}
