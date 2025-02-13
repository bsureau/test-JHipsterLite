package tech.jhipster.lite.generator.server.springboot.mvc.sample.mongopersistence.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class SampleMongoDBPersistenceModuleFactoryTest {

  private static final SampleMongoDBPersistenceModuleFactory factory = new SampleMongoDBPersistenceModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, sampleInMemoryRepository(), inMemoryBeersResetter())
      .hasPrefixedFiles(
        "src/main/java/tech/jhipster/jhlitest/sample/infrastructure/secondary",
        "BeerDocument.java",
        "MongoDBBeersRepository.java",
        "SpringDataBeersRepository.java",
        "BeersCollectionChangeUnit.java"
      )
      .hasPrefixedFiles(
        "src/test/java/tech/jhipster/jhlitest/sample/infrastructure/secondary",
        "BeerDocumentTest.java",
        "MongoDBBeersRepositoryIT.java",
        "MongoDBBeersResetter.java"
      )
      .doNotHaveFiles(
        "src/main/java/tech/jhipster/jhlitest/sample/infrastructure/secondary/InMemoryBeersRepository.java",
        "src/test/java/tech/jhipster/jhlitest/sample/infrastructure/secondary/InMemoryBeersResetter.java"
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
