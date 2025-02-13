package tech.jhipster.lite.generator.client.vue.core.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.vue.core.domain.VueModulesFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.npm.NpmLazyInstaller;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class VueApplicationService {

  private final VueModulesFactory factory;

  public VueApplicationService(NpmLazyInstaller npmLazyInstaller) {
    factory = new VueModulesFactory(npmLazyInstaller);
  }

  public JHipsterModule buildVueModule(JHipsterModuleProperties properties) {
    return factory.buildVueModule(properties);
  }

  public JHipsterModule buildPiniaModule(JHipsterModuleProperties properties) {
    return factory.buildPiniaModule(properties);
  }
}
