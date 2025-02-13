package tech.jhipster.lite.module.domain.file;

import java.util.Map;
import tech.jhipster.lite.module.domain.JHipsterModuleContext;
import tech.jhipster.lite.shared.error.domain.Assert;

public interface TemplateRenderer {
  TemplateRenderer NOOP = (templateContent, context) -> templateContent;

  String render(String templateContent, Map<String, ?> context);

  default String render(String templateContent, JHipsterModuleContext context) {
    Assert.notNull("context", context);

    return render(templateContent, context.get());
  }
}
