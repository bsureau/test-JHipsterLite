package tech.jhipster.lite.generator.client.svelte.core.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.CLIENT_CORE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.INIT;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.PRETTIER;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SVELTE_CORE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.svelte.core.application.SvelteApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
public class SvelteModuleConfiguration {

  @Bean
  JHipsterModuleResource svelteModule(SvelteApplicationService svelteApplicationService) {
    return JHipsterModuleResource.builder()
      .slug(SVELTE_CORE)
      .withoutProperties()
      .apiDoc("Frontend - Svelte", "Add Svelte")
      .organization(JHipsterModuleOrganization.builder().feature(CLIENT_CORE).addDependency(INIT).addDependency(PRETTIER).build())
      .tags("client", "svelte")
      .factory(svelteApplicationService::buildModule);
  }
}
