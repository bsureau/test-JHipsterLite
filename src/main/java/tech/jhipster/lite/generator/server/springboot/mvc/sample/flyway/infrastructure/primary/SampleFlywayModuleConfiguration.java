package tech.jhipster.lite.generator.server.springboot.mvc.sample.flyway.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.SAMPLE_SCHEMA;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.FLYWAY;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.FLYWAY_POSTGRESQL;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.JPA_POSTGRESQL;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SAMPLE_FEATURE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SAMPLE_NOT_POSTGRESQL_FLYWAY_CHANGELOG;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SAMPLE_POSTGRESQL_FLYWAY_CHANGELOG;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.mvc.sample.flyway.application.SampleFlywayApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class SampleFlywayModuleConfiguration {

  @Bean
  JHipsterModuleResource sampleFlywayPostgresqlModule(SampleFlywayApplicationService sampleFlyway) {
    return JHipsterModuleResource.builder()
      .slug(SAMPLE_POSTGRESQL_FLYWAY_CHANGELOG)
      .withoutProperties()
      .apiDoc("Sample Feature", "Add postgresql flyway changelog for sample feature")
      .organization(
        JHipsterModuleOrganization.builder()
          .feature(SAMPLE_SCHEMA)
          .addDependency(FLYWAY_POSTGRESQL)
          .addDependency(SAMPLE_FEATURE)
          .addDependency(JPA_POSTGRESQL)
          .build()
      )
      .tags("server")
      .factory(sampleFlyway::buildPostgresqlModule);
  }

  @Bean
  JHipsterModuleResource sampleFlywayNotPostgresqlModule(SampleFlywayApplicationService sampleFlyway) {
    return JHipsterModuleResource.builder()
      .slug(SAMPLE_NOT_POSTGRESQL_FLYWAY_CHANGELOG)
      .withoutProperties()
      .apiDoc("Sample Feature", "Add not postgresql flyway changelog for sample feature")
      .organization(JHipsterModuleOrganization.builder().feature(SAMPLE_SCHEMA).addDependency(FLYWAY).addDependency(SAMPLE_FEATURE).build())
      .tags("server")
      .factory(sampleFlyway::buildNotPostgresqlModule);
  }
}
