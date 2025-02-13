package tech.jhipster.lite.generator.client.pagination_domain.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class TSPaginationDomainModuleFactory {

  private static final JHipsterSource SOURCE = from("client/pagination/domain");
  private static final JHipsterDestination DESTINATION = to("src/main/webapp/app/shared/pagination/domain");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    // @formatter:off
    return moduleBuilder(properties)
      .files()
        .add(SOURCE.template("Page.ts"), DESTINATION.append("Page.ts"))
        .add(SOURCE.template("DisplayedOnPage.ts"), DESTINATION.append("DisplayedOnPage.ts"))
        .and()
      .build();
    //@formatter:on
  }
}
