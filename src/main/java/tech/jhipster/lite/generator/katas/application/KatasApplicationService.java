package tech.jhipster.lite.generator.katas.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.katas.domain.KatasModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class KatasApplicationService {

  private final KatasModuleFactory factory;

  public KatasApplicationService() {
    factory = new KatasModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
