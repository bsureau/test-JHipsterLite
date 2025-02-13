package tech.jhipster.lite.generator.server.springboot.apidocumentation.openapicontract.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.apidocumentation.openapicontract.domain.OpenApiContractModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class OpenApiContractApplicationService {

  private final OpenApiContractModuleFactory factory;

  public OpenApiContractApplicationService() {
    factory = new OpenApiContractModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }

  public JHipsterModule buildBackwardsCompatibilityCheckModule(JHipsterModuleProperties properties) {
    return factory.buildBackwardsCompatibilityCheckModule(properties);
  }
}
