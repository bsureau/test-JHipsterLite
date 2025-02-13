package tech.jhipster.lite.generator.server.javatool.archunit.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.javatool.archunit.domain.ArchUnitModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class JavaArchUnitApplicationService {

  private final ArchUnitModuleFactory factory;

  public JavaArchUnitApplicationService() {
    factory = new ArchUnitModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
