package tech.jhipster.lite.generator.server.javatool.jacoco.application;

import org.springframework.stereotype.Component;
import tech.jhipster.lite.generator.server.javatool.jacoco.domain.JacocoModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Component
public class JacocoApplicationService {

  private final JacocoModuleFactory factory;

  public JacocoApplicationService() {
    factory = new JacocoModuleFactory();
  }

  public JHipsterModule buildJacocoModule(JHipsterModuleProperties properties) {
    return factory.buildJacocoModule(properties);
  }

  public JHipsterModule buildJacocoWithMinCoverageCheckModule(JHipsterModuleProperties properties) {
    return factory.buildJacocoWithMinCoverageCheckModule(properties);
  }
}
