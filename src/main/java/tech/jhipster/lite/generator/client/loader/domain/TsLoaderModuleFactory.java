package tech.jhipster.lite.generator.client.loader.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class TsLoaderModuleFactory {

  private static final JHipsterSource SOURCE = from("client/loader");
  private static final String DESTINATION = "shared/loader/infrastructure/primary";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    //@formatter:off
    return moduleBuilder(properties)
      .files()
        .add(SOURCE.template("Loader.ts"), to("src/main/webapp/app").append(DESTINATION).append("Loader.ts"))
        .add(SOURCE.template("Loader.spec.ts"), to("src/test/webapp/unit").append(DESTINATION).append("Loader.spec.ts"))
        .and()
      .build();
    //@formatter:on
  }
}
