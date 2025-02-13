package tech.jhipster.lite.generator.client.rest_pagination.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.TS_PAGINATION_DOMAIN;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.rest_pagination.application.TSRestPaginationApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;
import tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug;

@Configuration
class TSRestPaginationModuleConfiguration {

  @Bean
  JHipsterModuleResource tsRestPaginationModule(TSRestPaginationApplicationService tsRestPagination) {
    return JHipsterModuleResource.builder()
      .slug(JHLiteModuleSlug.TS_REST_PAGINATION)
      .withoutProperties()
      .apiDoc("Pagination", "Add rest pagination to the frontend webapp")
      .organization(JHipsterModuleOrganization.builder().addDependency(TS_PAGINATION_DOMAIN).build())
      .tags("client", "webapp", "frontend")
      .factory(tsRestPagination::buildModule);
  }
}
