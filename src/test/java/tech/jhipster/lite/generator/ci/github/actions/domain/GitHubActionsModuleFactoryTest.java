package tech.jhipster.lite.generator.ci.github.actions.domain;

import static org.mockito.Mockito.when;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModule;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.npm.NpmPackageVersion;
import tech.jhipster.lite.module.domain.npm.NpmVersions;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
@ExtendWith(MockitoExtension.class)
class GitHubActionsModuleFactoryTest {

  @Mock
  private NpmVersions npmVersions;

  @InjectMocks
  private GitHubActionsModuleFactory factory;

  @Test
  void shouldBuildGitHubActionsMavenModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();
    mockNodeVersion();

    JHipsterModule module = factory.buildGitHubActionsMavenModule(properties);

    assertThatModule(module)
      .hasFiles(".github/workflows/github-actions.yml")
      .hasFile(".github/actions/setup/action.yml")
      .containing("java-version: 21")
      .containing("node-version: 44");
  }

  private void mockNodeVersion() {
    when(npmVersions.nodeVersion()).thenReturn(new NpmPackageVersion("44"));
  }

  @Test
  void shouldBuildGitHubActionsGradleModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();
    mockNodeVersion();

    JHipsterModule module = factory.buildGitHubActionsGradleModule(properties);

    assertThatModule(module)
      .hasFiles(".github/workflows/github-actions.yml")
      .hasFile(".github/actions/setup/action.yml")
      .containing("java-version: 21")
      .containing("node-version: 44");
  }
}
