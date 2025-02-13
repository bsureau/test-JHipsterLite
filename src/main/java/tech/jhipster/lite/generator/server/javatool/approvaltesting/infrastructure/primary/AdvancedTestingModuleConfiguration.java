package tech.jhipster.lite.generator.server.javatool.approvaltesting.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.javatool.approvaltesting.application.ApprovalTestingApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;
import tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug;
import tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug;

@Configuration
class AdvancedTestingModuleConfiguration {

  @Bean
  JHipsterModuleResource approvalTestsModule(ApprovalTestingApplicationService approvalTesting) {
    return JHipsterModuleResource.builder()
      .slug(JHLiteModuleSlug.APPROVAL_TESTS)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().build())
      .apiDoc("Advanced testing", "Add ApprovalTests library for Approval testing")
      .organization(JHipsterModuleOrganization.builder().addDependency(JHLiteFeatureSlug.JAVA_BUILD_TOOL).build())
      .tags("server", "testing")
      .factory(approvalTesting::build);
  }
}
