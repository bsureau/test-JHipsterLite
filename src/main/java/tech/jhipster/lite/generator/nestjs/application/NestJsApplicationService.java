package tech.jhipster.lite.generator.nestjs.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.nestjs.domain.NestJsModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class NestJsApplicationService {

  private final NestJsModuleFactory factory;

  public NestJsApplicationService() {
    factory = new NestJsModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
