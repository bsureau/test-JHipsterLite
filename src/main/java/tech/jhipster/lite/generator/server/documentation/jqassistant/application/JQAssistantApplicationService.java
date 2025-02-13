package tech.jhipster.lite.generator.server.documentation.jqassistant.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.documentation.jqassistant.domain.JQAssistantModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class JQAssistantApplicationService {

  private final JQAssistantModuleFactory factory;

  public JQAssistantApplicationService() {
    factory = new JQAssistantModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }

  public JHipsterModule buildJMoleculesModule(JHipsterModuleProperties properties) {
    return factory.buildJMoleculesModule(properties);
  }

  public JHipsterModule buildSpringModule(JHipsterModuleProperties properties) {
    return factory.buildSpringModule(properties);
  }
}
