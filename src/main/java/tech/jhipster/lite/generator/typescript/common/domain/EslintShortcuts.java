package tech.jhipster.lite.generator.typescript.common.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.lineAfterRegex;
import static tech.jhipster.lite.module.domain.JHipsterModule.path;

import java.util.function.Consumer;
import tech.jhipster.lite.module.domain.Indentation;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.replacement.MandatoryReplacer;

public final class EslintShortcuts {

  private EslintShortcuts() {}

  public static Consumer<JHipsterModule.JHipsterModuleBuilder> eslintTypescriptRule(String rule, Indentation indentation) {
    //@formatter:off
    return moduleBuilder -> moduleBuilder
      .mandatoryReplacements()
        .in(path("eslint.config.js"))
        .add(eslintTypescriptVueRuleReplacement(rule, indentation))
        .and()
      .and();
    //@formatter:on
  }

  private static MandatoryReplacer eslintTypescriptVueRuleReplacement(String rule, Indentation indentation) {
    return new MandatoryReplacer(lineAfterRegex("rules: \\{"), indentation.times(3) + rule + ",");
  }
}
