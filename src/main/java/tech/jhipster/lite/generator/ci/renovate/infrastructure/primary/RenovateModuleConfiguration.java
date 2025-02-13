package tech.jhipster.lite.generator.ci.renovate.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.RENOVATE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.ci.renovate.application.RenovateApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;
import tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug;

@Configuration
class RenovateModuleConfiguration {

  private static final String CI_TAG = "ci";
  private static final String RENOVATE_TAG = "renovate";

  @Bean
  JHipsterModuleResource renovateMavenModule(RenovateApplicationService renovateApplicationService) {
    return JHipsterModuleResource.builder()
      .slug(RENOVATE)
      .withoutProperties()
      .apiDoc("Dependencies updates", "Add Renovate for automatic dependency updates")
      .organization(JHipsterModuleOrganization.builder().feature(JHLiteFeatureSlug.DEPENDENCIES_UPDATES).build())
      .tags(CI_TAG, RENOVATE_TAG)
      .factory(renovateApplicationService::buildRenovateModule);
  }
}
