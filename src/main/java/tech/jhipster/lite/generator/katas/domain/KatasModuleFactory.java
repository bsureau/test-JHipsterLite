package tech.jhipster.lite.generator.katas.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class KatasModuleFactory {

  private static final JHipsterSource SOURCE = from("katas");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    //@formatter:off
    return moduleBuilder(properties)
      .files()
        .add(SOURCE.template("katas.spec.ts"), to("./src/katas.spec.ts"))
        .add(SOURCE.template("vitest.config.ts"), to("./vitest.config.ts"))
        .add(SOURCE.template("README.md"), to("./README.md"))
      .and()
      .build();
    //@formatter:on
  }
}
