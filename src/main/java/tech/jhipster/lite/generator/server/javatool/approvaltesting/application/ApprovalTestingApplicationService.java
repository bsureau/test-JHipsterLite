package tech.jhipster.lite.generator.server.javatool.approvaltesting.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.javatool.approvaltesting.domain.ApprovalTestingModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class ApprovalTestingApplicationService {

  private final ApprovalTestingModuleFactory approvalTesting;

  public ApprovalTestingApplicationService() {
    approvalTesting = new ApprovalTestingModuleFactory();
  }

  public JHipsterModule build(JHipsterModuleProperties properties) {
    return approvalTesting.build(properties);
  }
}
