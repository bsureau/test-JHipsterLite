package tech.jhipster.lite.generator.nestjs.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcMainNestJs;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class NestJsModuleFactory {

  private static final JHipsterSource SOURCE = from("nestjs");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    //@formatter:off
    return moduleBuilder(properties)
      .files()
        .add(SOURCE.template("dummy.ts"), toSrcMainNestJs().append("dummy.ts"))
        .and()
      .build();
    //@formatter:on
  }
}
