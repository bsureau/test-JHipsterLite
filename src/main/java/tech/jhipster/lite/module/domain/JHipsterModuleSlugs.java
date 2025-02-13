package tech.jhipster.lite.module.domain;

import java.util.Collection;
import tech.jhipster.lite.shared.error.domain.Assert;

public record JHipsterModuleSlugs(Collection<JHipsterModuleSlug> modules) {
  public JHipsterModuleSlugs {
    Assert.notEmpty("modules", modules);
  }

  public static JHipsterModuleSlugs from(Collection<String> modules) {
    return new JHipsterModuleSlugs(modules.stream().map(JHipsterModuleSlug::new).toList());
  }
}
