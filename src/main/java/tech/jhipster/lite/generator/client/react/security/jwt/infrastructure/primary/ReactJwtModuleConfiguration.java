package tech.jhipster.lite.generator.client.react.security.jwt.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.REACT_CORE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.REACT_JWT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.react.security.jwt.application.ReactJwtApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class ReactJwtModuleConfiguration {

  @Bean
  JHipsterModuleResource reactJwtModule(ReactJwtApplicationService reactJwt) {
    return JHipsterModuleResource.builder()
      .slug(REACT_JWT)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addIndentation().build())
      .apiDoc("Frontend - React", "Add JWT Login React")
      .organization(JHipsterModuleOrganization.builder().addDependency(REACT_CORE).build())
      .tags("client", "react", "jwt")
      .factory(reactJwt::buildModule);
  }
}
