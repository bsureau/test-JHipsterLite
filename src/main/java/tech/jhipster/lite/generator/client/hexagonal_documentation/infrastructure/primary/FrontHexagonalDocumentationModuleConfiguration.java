package tech.jhipster.lite.generator.client.hexagonal_documentation.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import tech.jhipster.lite.generator.client.hexagonal_documentation.application.FrontHexagonalArchitectureDocumentationApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;
import tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug;

@Component
class FrontHexagonalDocumentationModuleConfiguration {

  @Bean
  JHipsterModuleResource frontHexagonalDocumentationModule(FrontHexagonalArchitectureDocumentationApplicationService documentation) {
    return JHipsterModuleResource.builder()
      .slug(JHLiteModuleSlug.FRONT_HEXAGONAL_ARCHITECTURE)
      .withoutProperties()
      .apiDoc("Frontend", "Add front hexagonal architecture documentation")
      .standalone()
      .tags("client", "documentation")
      .factory(documentation::buildModule);
  }
}
