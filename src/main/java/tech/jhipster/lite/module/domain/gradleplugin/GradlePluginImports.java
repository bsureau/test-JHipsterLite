package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.Collection;
import java.util.function.Consumer;
import tech.jhipster.lite.shared.error.domain.Assert;

public record GradlePluginImports(Collection<BuildGradleImport> pluginImports) {
  public GradlePluginImports {
    Assert.field("pluginImports", pluginImports).notNull().noNullElement();
  }

  public void forEach(Consumer<BuildGradleImport> consumer) {
    Assert.notNull("consumer", consumer);

    pluginImports.forEach(consumer);
  }
}
