package tech.jhipster.lite.generator.ci.renovate.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.ci.renovate.domain.RenovateModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class RenovateApplicationService {

  private final RenovateModuleFactory factory;

  public RenovateApplicationService() {
    factory = new RenovateModuleFactory();
  }

  public JHipsterModule buildRenovateModule(JHipsterModuleProperties properties) {
    return factory.buildRenovateModule(properties);
  }
}
