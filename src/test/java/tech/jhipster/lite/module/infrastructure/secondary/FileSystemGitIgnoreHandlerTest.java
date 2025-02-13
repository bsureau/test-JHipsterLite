package tech.jhipster.lite.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.TestFileUtils.projectFrom;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.allProperties;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.file.TemplateRenderer;
import tech.jhipster.lite.module.domain.gitignore.JHipsterModuleGitIgnore;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

@UnitTest
class FileSystemGitIgnoreHandlerTest {

  private final FileSystemGitIgnoreHandler handler = new FileSystemGitIgnoreHandler(new FileSystemReplacer(TemplateRenderer.NOOP));

  @Test
  void shouldNotCreateGitIgnoreFileIfPatternsAreEmpty() {
    JHipsterProjectFolder projectFolder = projectFrom("src/test/resources/projects/empty");

    handler.handle(projectFolder, JHipsterModuleGitIgnore.builder(moduleBuilder(allProperties())).build());

    assertThat(projectFolder.filePath(".gitignore")).doesNotExist();
  }

  @Test
  void shouldAutomaticallyCreateMissingGitIgnoreFileIfPatternsIsNotEmpty() {
    JHipsterProjectFolder projectFolder = projectFrom("src/test/resources/projects/empty");

    handler.handle(projectFolder, JHipsterModuleGitIgnore.builder(moduleBuilder(allProperties())).pattern("target/").build());

    assertThat(projectFolder.filePath(".gitignore")).exists().content().contains("target/");
  }

  @Test
  void shouldNotAddAgainAnExistingEntry() {
    JHipsterProjectFolder projectFolder = projectFrom("src/test/resources/projects/empty");
    handler.handle(projectFolder, JHipsterModuleGitIgnore.builder(moduleBuilder(allProperties())).pattern("target/").build());

    handler.handle(projectFolder, JHipsterModuleGitIgnore.builder(moduleBuilder(allProperties())).pattern("target/").build());

    assertThat(projectFolder.filePath(".gitignore")).content().containsOnlyOnce("target/");
  }
}
