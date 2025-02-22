package tech.jhipster.lite.generator.katas.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.KATAS_DOJO;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.KATAS_TS_VITEST;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.PRETTIER;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.TYPESCRIPT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.katas.application.KatasApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class KatasCoreModulesConfiguration {

  public static final String KATAS = "katas-dojo";

  @Bean
  JHipsterModuleResource katasModule(KatasApplicationService katas) {
    return JHipsterModuleResource.builder()
      .slug(KATAS_TS_VITEST)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().build())
      .apiDoc("TS + Vitest", "Make a kata using TypeScript and Vitest")
      .organization(JHipsterModuleOrganization.builder().feature(KATAS_DOJO).addDependency(TYPESCRIPT).addDependency(PRETTIER).build())
      .tags("katas-dojo", KATAS)
      .factory(katas::buildModule);
  }
}
