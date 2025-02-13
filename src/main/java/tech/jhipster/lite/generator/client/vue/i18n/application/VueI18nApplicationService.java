package tech.jhipster.lite.generator.client.vue.i18n.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.vue.i18n.domain.VueI18nModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class VueI18nApplicationService {

  private final VueI18nModuleFactory factory;

  public VueI18nApplicationService() {
    factory = new VueI18nModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
