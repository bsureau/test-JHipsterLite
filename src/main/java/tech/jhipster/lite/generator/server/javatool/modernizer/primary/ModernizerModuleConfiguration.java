package tech.jhipster.lite.generator.server.javatool.modernizer.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.javatool.modernizer.application.ModernizerApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;
import tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug;
import tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug;

@Configuration
class ModernizerModuleConfiguration {

  @Bean
  JHipsterModuleResource modernizerModule(ModernizerApplicationService modernizer) {
    return JHipsterModuleResource.builder()
      .slug(JHLiteModuleSlug.MODERNIZER)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().build())
      .apiDoc(
        "Java",
        "Add Modernizer build plugin for detecting uses of legacy APIs which modern Java versions supersede. These modern APIs are often more performant, safer, and idiomatic than the legacy equivalents."
      )
      .organization(JHipsterModuleOrganization.builder().addDependency(JHLiteFeatureSlug.JAVA_BUILD_TOOL).build())
      .tags("server", "quality")
      .factory(modernizer::build);
  }
}
