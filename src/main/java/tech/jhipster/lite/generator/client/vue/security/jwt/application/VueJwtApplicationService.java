package tech.jhipster.lite.generator.client.vue.security.jwt.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.vue.security.jwt.domain.VueJwtModulesFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class VueJwtApplicationService {

  private final VueJwtModulesFactory factory;

  public VueJwtApplicationService() {
    factory = new VueJwtModulesFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
