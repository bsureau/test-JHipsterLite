package tech.jhipster.lite.generator.setup.codespaces.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.GITHUB_CODESPACES;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.setup.codespaces.application.CodespacesApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class CodespaceModuleConfiguration {

  @Bean
  JHipsterModuleResource codespaceModule(CodespacesApplicationService codespaces) {
    return JHipsterModuleResource.builder()
      .slug(GITHUB_CODESPACES)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addServerPort().build())
      .apiDoc("Development environment", "Init GitHub Codespaces configuration files")
      .standalone()
      .tags("setup", "codespaces")
      .factory(codespaces::buildModule);
  }
}
