package tech.jhipster.lite.generator.nestjs.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.SERVER_CORE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.NESTJS_CORE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.PRETTIER;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.TYPESCRIPT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.nestjs.application.NestJsApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class NestJsCoreModulesConfiguration {

  public static final String NESTJS = "nestjs";

  @Bean
  JHipsterModuleResource nestJsModule(NestJsApplicationService nestjs) {
    return JHipsterModuleResource.builder()
      .slug(NESTJS_CORE)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().build())
      .apiDoc("Backend - NestJS", "Add NestJS API")
      .organization(JHipsterModuleOrganization.builder().feature(SERVER_CORE).addDependency(TYPESCRIPT).addDependency(PRETTIER).build())
      .tags("server", NESTJS)
      .factory(nestjs::buildModule);
  }
}
