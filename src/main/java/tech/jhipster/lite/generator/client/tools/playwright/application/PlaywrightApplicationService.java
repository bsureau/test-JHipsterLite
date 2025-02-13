package tech.jhipster.lite.generator.client.tools.playwright.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.tools.playwright.domain.PlaywrightModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class PlaywrightApplicationService {

  private final PlaywrightModuleFactory factory;

  public PlaywrightApplicationService() {
    factory = new PlaywrightModuleFactory();
  }

  public JHipsterModule buildComponentTestsModule(JHipsterModuleProperties properties) {
    return factory.buildComponentTestsModule(properties);
  }

  public JHipsterModule buildE2ETestsModule(JHipsterModuleProperties properties) {
    return factory.buildE2ETestsModule(properties);
  }
}
