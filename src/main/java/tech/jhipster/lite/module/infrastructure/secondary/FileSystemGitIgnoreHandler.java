package tech.jhipster.lite.module.infrastructure.secondary;

import static tech.jhipster.lite.module.domain.replacement.ReplacementCondition.notContainingReplacement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import org.springframework.stereotype.Service;
import tech.jhipster.lite.module.domain.JHipsterModuleContext;
import tech.jhipster.lite.module.domain.JHipsterProjectFilePath;
import tech.jhipster.lite.module.domain.gitignore.GitIgnoreEntry;
import tech.jhipster.lite.module.domain.gitignore.JHipsterModuleGitIgnore;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.module.domain.replacement.*;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.error.domain.GeneratorException;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

@Service
class FileSystemGitIgnoreHandler {

  private static final String GIT_IGNORE_FILE_PATH = ".gitignore";
  private final FileSystemReplacer fileReplacer;

  public FileSystemGitIgnoreHandler(FileSystemReplacer fileReplacer) {
    this.fileReplacer = fileReplacer;
  }

  public void handle(JHipsterProjectFolder projectFolder, JHipsterModuleGitIgnore gitIgnore) {
    Assert.notNull("projectFolder", projectFolder);
    Assert.notNull("gitIgnore", gitIgnore);

    if (gitIgnore.isNotEmpty()) {
      createGitIgnoreFileIfNeeded(projectFolder);
    }
    gitIgnore.forEach(handleIgnorePattern(projectFolder));
  }

  @ExcludeFromGeneratedCodeCoverage(reason = "IOException is hard to test")
  private static void createGitIgnoreFileIfNeeded(JHipsterProjectFolder projectFolder) {
    Path gitIgnoreFilePath = projectFolder.filePath(GIT_IGNORE_FILE_PATH);

    if (Files.notExists(gitIgnoreFilePath)) {
      try {
        Files.createFile(gitIgnoreFilePath);
      } catch (IOException exception) {
        throw GeneratorException.technicalError(
          "Error creating %s file: %s".formatted(GIT_IGNORE_FILE_PATH, exception.getMessage()),
          exception
        );
      }
    }
  }

  private Consumer<GitIgnoreEntry> handleIgnorePattern(JHipsterProjectFolder projectFolder) {
    return gitIgnoreEntry -> {
      MandatoryReplacer replacer = new MandatoryReplacer(new EndOfFileReplacer(notContainingReplacement()), gitIgnoreEntry.get());
      fileReplacer.handle(
        projectFolder,
        ContentReplacers.of(new MandatoryFileReplacer(new JHipsterProjectFilePath(GIT_IGNORE_FILE_PATH), replacer)),
        JHipsterModuleContext.empty()
      );
    };
  }
}
