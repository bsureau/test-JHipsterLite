package tech.jhipster.lite.generator.server.springboot.mvc.sample.liquibase.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.SAMPLE_SCHEMA;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.LIQUIBASE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SAMPLE_FEATURE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SAMPLE_LIQUIBASE_CHANGELOG;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.mvc.sample.liquibase.application.SampleLiquibaseApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class SampleLiquibaseModuleConfiguration {

  @Bean
  JHipsterModuleResource sampleLiquibaseModule(SampleLiquibaseApplicationService sampleLiquibase) {
    return JHipsterModuleResource.builder()
      .slug(SAMPLE_LIQUIBASE_CHANGELOG)
      .withoutProperties()
      .apiDoc("Sample Feature", "Add liquibase changelog for sample feature")
      .organization(
        JHipsterModuleOrganization.builder().feature(SAMPLE_SCHEMA).addDependency(LIQUIBASE).addDependency(SAMPLE_FEATURE).build()
      )
      .tags("server")
      .factory(sampleLiquibase::buildModule);
  }
}
