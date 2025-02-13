package tech.jhipster.lite.generator.ci.gitlab.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModule;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class GitLabCiModuleFactoryTest {

  private static final GitLabCiModuleFactory factory = new GitLabCiModuleFactory();

  @Test
  void buildGitLabCiMavenModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildGitLabCiMavenModule(properties);

    assertThatModule(module).hasFiles(".gitlab-ci.yml");
  }

  @Test
  void buildGitLabCiGradleModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildGitLabCiGradleModule(properties);

    assertThatModule(module).hasFiles(".gitlab-ci.yml");
  }
}
