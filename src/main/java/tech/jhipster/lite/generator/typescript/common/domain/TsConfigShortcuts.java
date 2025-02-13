package tech.jhipster.lite.generator.typescript.common.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.lineAfterRegex;
import static tech.jhipster.lite.module.domain.JHipsterModule.path;

import java.util.function.Consumer;
import tech.jhipster.lite.module.domain.Indentation;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.replacement.MandatoryReplacer;

public final class TsConfigShortcuts {

  private TsConfigShortcuts() {}

  public static Consumer<JHipsterModule.JHipsterModuleBuilder> tsConfigCompilerOption(
    String optionName,
    boolean optionValue,
    Indentation indentation
  ) {
    //@formatter:off
    return moduleBuilder -> moduleBuilder
      .mandatoryReplacements()
        .in(path("tsconfig.json"))
        .add(tsConfigCompilerOptionReplacement(optionName, optionValue, indentation))
        .and();
    //@formatter:on
  }

  private static MandatoryReplacer tsConfigCompilerOptionReplacement(String optionName, boolean optionValue, Indentation indentation) {
    String compilerOption = indentation.times(2) + "\"%s\": %s,".formatted(optionName, optionValue);
    return new MandatoryReplacer(lineAfterRegex("\"compilerOptions\":"), compilerOption);
  }
}
