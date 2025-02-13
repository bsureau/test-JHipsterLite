package tech.jhipster.lite.module.infrastructure.secondary;

import static tech.jhipster.lite.module.domain.JHipsterModule.LINE_BREAK;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import org.springframework.stereotype.Service;
import tech.jhipster.lite.module.domain.JHipsterModuleContext;
import tech.jhipster.lite.module.domain.file.TemplateRenderer;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.module.domain.replacement.ContentReplacer;
import tech.jhipster.lite.module.domain.replacement.ContentReplacers;
import tech.jhipster.lite.shared.error.domain.Assert;

@Service
public class FileSystemReplacer {

  private final TemplateRenderer templateRenderer;

  public FileSystemReplacer(TemplateRenderer templateRenderer) {
    this.templateRenderer = templateRenderer;
  }

  public void handle(JHipsterProjectFolder projectFolder, ContentReplacers replacers, JHipsterModuleContext context) {
    Assert.notNull("projectFolder", projectFolder);
    Assert.notNull("replacers", replacers);

    replacers.forEach(applyReplacer(projectFolder, context));
  }

  private Consumer<ContentReplacer> applyReplacer(JHipsterProjectFolder projectFolder, JHipsterModuleContext context) {
    return replacement -> {
      Path filePath = projectFolder.filePath(replacement.file().get());

      try {
        String content = Files.readString(filePath);
        String updatedContent = replacement.apply(content);

        updatedContent = replacePlaceholders(updatedContent, context);

        Files.writeString(filePath, updatedContent.replace("\r\n", LINE_BREAK), StandardCharsets.UTF_8);
      } catch (IOException e) {
        replacement.handleError(e);
      }
    };
  }

  private String replacePlaceholders(String content, JHipsterModuleContext context) {
    return templateRenderer.render(content, context);
  }
}
