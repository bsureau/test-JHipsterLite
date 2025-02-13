package tech.jhipster.lite.generator.typescript.common.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.path;
import static tech.jhipster.lite.module.domain.JHipsterModule.text;

import java.util.function.Consumer;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.replacement.MandatoryReplacer;

public final class VitestShortcuts {

  private VitestShortcuts() {}

  public static Consumer<JHipsterModule.JHipsterModuleBuilder> vitestCoverageExclusion(String exclusionFilePattern) {
    //@formatter:off
    return moduleBuilder -> moduleBuilder
      .mandatoryReplacements()
        .in(path("vitest.config.ts"))
        .add(vitestCoverageExclusionReplacement(exclusionFilePattern));
    //@formatter:on
  }

  private static MandatoryReplacer vitestCoverageExclusionReplacement(String filePattern) {
    return new MandatoryReplacer(
      text("(configDefaults.coverage.exclude as string[])"),
      "(configDefaults.coverage.exclude as string[])" + ", '%s'".formatted(filePattern)
    );
  }
}
