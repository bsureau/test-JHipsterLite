package tech.jhipster.lite.generator.server.springboot.mvc.sample.feature.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.mvc.sample.feature.domain.SampleFeatureModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class SampleFeatureApplicationService {

  private final SampleFeatureModuleFactory factory;

  public SampleFeatureApplicationService() {
    factory = new SampleFeatureModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
