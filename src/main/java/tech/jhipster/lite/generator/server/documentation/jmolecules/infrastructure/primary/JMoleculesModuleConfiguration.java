package tech.jhipster.lite.generator.server.documentation.jmolecules.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.JMOLECULES;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.documentation.jmolecules.application.JMoleculesApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;
import tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug;

@Configuration
class JMoleculesModuleConfiguration {

  @Bean
  JHipsterModuleResource jMoleculesModule(JMoleculesApplicationService jMolecules) {
    return JHipsterModuleResource.builder()
      .slug(JMOLECULES)
      .withoutProperties()
      .apiDoc(
        "Documentation",
        "Add support for jMolecules documentation annotations based on DDD patterns such as @BoundedContext, @ValueObject, @Entity, @AggregateRoot..."
      )
      .organization(JHipsterModuleOrganization.builder().addDependency(JHLiteFeatureSlug.JAVA_BUILD_TOOL).build())
      .tags("server", "documentation")
      .factory(jMolecules::buildModule);
  }
}
