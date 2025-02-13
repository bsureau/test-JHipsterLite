package tech.jhipster.lite.generator.server.webjars.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.groupId;
import static tech.jhipster.lite.module.domain.JHipsterModule.javaDependency;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class WebjarsModuleFactory {

  private static final String PROPERTIES_FIELD = "properties";
  private static final GroupId WEBJARS_GROUP = groupId("org.webjars");
  private static final GroupId WEBJARS_NPM_GROUP = groupId("org.webjars.npm");

  public JHipsterModule buildWebjarsLocatorModule(JHipsterModuleProperties properties) {
    Assert.notNull(PROPERTIES_FIELD, properties);

    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
      .addDependency(javaDependency().groupId(WEBJARS_GROUP).artifactId("webjars-locator").versionSlug("webjars-locator.version").build())
      .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildWebjarsHtmxModule(JHipsterModuleProperties properties) {
    Assert.notNull(PROPERTIES_FIELD, properties);
    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
      .addDependency(javaDependency().groupId(WEBJARS_NPM_GROUP).artifactId("htmx.org").versionSlug("htmx-webjars.version").build())
      .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildWebjarsAlpineJSModule(JHipsterModuleProperties properties) {
    Assert.notNull(PROPERTIES_FIELD, properties);

    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
      .addDependency(javaDependency().groupId(WEBJARS_NPM_GROUP).artifactId("alpinejs").versionSlug("alpinejs-webjars.version").build())
      .and()
      .build();
    //@formatter:on
  }
}
