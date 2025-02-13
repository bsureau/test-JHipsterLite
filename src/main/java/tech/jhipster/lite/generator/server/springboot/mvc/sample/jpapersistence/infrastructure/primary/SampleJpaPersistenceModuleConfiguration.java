package tech.jhipster.lite.generator.server.springboot.mvc.sample.jpapersistence.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.SAMPLE_PERSISTENCE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.SAMPLE_SCHEMA;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SAMPLE_JPA_PERSISTENCE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SPRING_BOOT_CUCUMBER_JPA_RESET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.mvc.sample.jpapersistence.application.SampleJpaPersistenceApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class SampleJpaPersistenceModuleConfiguration {

  @Bean
  JHipsterModuleResource sampleJpaPersistenceModule(SampleJpaPersistenceApplicationService sampleJpaPersistence) {
    return JHipsterModuleResource.builder()
      .slug(SAMPLE_JPA_PERSISTENCE)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().build())
      .apiDoc("Sample Feature", "Add JPA persistence for sample feature")
      .organization(
        JHipsterModuleOrganization.builder()
          .feature(SAMPLE_PERSISTENCE)
          .addDependency(SAMPLE_SCHEMA)
          .addDependency(SPRING_BOOT_CUCUMBER_JPA_RESET)
          .build()
      )
      .tags("server")
      .factory(sampleJpaPersistence::buildModule);
  }
}
