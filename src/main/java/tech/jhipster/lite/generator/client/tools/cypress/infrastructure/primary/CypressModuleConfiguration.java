package tech.jhipster.lite.generator.client.tools.cypress.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.CLIENT_CORE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.E2E_TESTS;
import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.FRONTEND_COMPONENT_TESTS;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.CYPRESS_COMPONENT_TESTS;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.CYPRESS_E2E;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.tools.cypress.application.CypressApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class CypressModuleConfiguration {

  @Bean
  JHipsterModuleResource cypressComponentTestsModule(CypressApplicationService cypress) {
    return JHipsterModuleResource.builder()
      .slug(CYPRESS_COMPONENT_TESTS)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addIndentation().build())
      .apiDoc("Frontend - Component tests", "Setup frontend component tests using Cypress")
      .organization(JHipsterModuleOrganization.builder().feature(FRONTEND_COMPONENT_TESTS).addDependency(CLIENT_CORE).build())
      .tags("client", "test", "cypress", "frontend")
      .factory(cypress::buildComponentTestsModule);
  }

  @Bean
  JHipsterModuleResource cypressE2ETestsModule(CypressApplicationService cypress) {
    return JHipsterModuleResource.builder()
      .slug(CYPRESS_E2E)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addIndentation().build())
      .apiDoc("E2E", "Setup E2E tests using Cypress")
      .organization(JHipsterModuleOrganization.builder().feature(E2E_TESTS).addDependency(CLIENT_CORE).build())
      .tags("client", "test", "cypress", "e2e")
      .factory(cypress::buildE2ETestsModule);
  }
}
