package tech.jhipster.lite.generator.server.springboot.thymeleaf.template.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.ALPINE_JS_WEBJARS;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.HTMX_WEBJARS;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SPRING_BOOT_THYMELEAF;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.THYMELEAF_TEMPLATE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.THYMELEAF_TEMPLATE_ALPINEJS_WEBJAR;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.THYMELEAF_TEMPLATE_HTMX_WEBJAR;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.THYMELEAF_TEMPLATE_TAILWINDCSS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.thymeleaf.template.application.ThymeleafTemplateModuleApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class ThymeleafTemplateModuleConfiguration {

  private static final String GROUP_SPRING_BOOT_THYMELEAF = "Spring Boot - Thymeleaf";
  private static final String TAG_SERVER = "server";
  private static final String TAG_SPRING = "spring";
  private static final String TAG_BOOT = "spring-boot";
  private static final String TAG_THYMELEAF = "thymeleaf";
  private static final String TAG_TAILWINDCSS = "tailwindcss";
  private static final String TAG_WEBJAR = "webjar";

  @Bean
  public JHipsterModuleResource thymeleafTemplateModule(ThymeleafTemplateModuleApplicationService thymeleafTemplate) {
    return JHipsterModuleResource.builder()
      .slug(THYMELEAF_TEMPLATE)
      .propertiesDefinition(
        JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().addSpringConfigurationFormat().build()
      )
      .apiDoc(GROUP_SPRING_BOOT_THYMELEAF, "Add thymeleaf skeleton layout files to the project")
      .organization(JHipsterModuleOrganization.builder().addDependency(SPRING_BOOT_THYMELEAF).build())
      .tags(TAG_SERVER, TAG_SPRING, TAG_BOOT, TAG_THYMELEAF)
      .factory(thymeleafTemplate::buildThymeleafTemplateModule);
  }

  @Bean
  public JHipsterModuleResource thymeleafTemplateTailwindcssModule(ThymeleafTemplateModuleApplicationService thymeleafTemplate) {
    return JHipsterModuleResource.builder()
      .slug(THYMELEAF_TEMPLATE_TAILWINDCSS)
      .propertiesDefinition(
        JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().addSpringConfigurationFormat().build()
      )
      .apiDoc(GROUP_SPRING_BOOT_THYMELEAF, "Add tailwindcss to the thymeleaf template")
      .organization(JHipsterModuleOrganization.builder().addDependency(THYMELEAF_TEMPLATE).build())
      .tags(TAG_SERVER, TAG_SPRING, TAG_BOOT, TAG_THYMELEAF, TAG_TAILWINDCSS)
      .factory(thymeleafTemplate::buildThymeleafTemplateTailwindcssModule);
  }

  @Bean
  public JHipsterModuleResource thymeleafTemplateHtmxWebjarsModule(ThymeleafTemplateModuleApplicationService thymeleafTemplate) {
    return JHipsterModuleResource.builder()
      .slug(THYMELEAF_TEMPLATE_HTMX_WEBJAR)
      .propertiesDefinition(
        JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().addSpringConfigurationFormat().build()
      )
      .apiDoc(GROUP_SPRING_BOOT_THYMELEAF, "Add htmx webjars scripts to thymeleaf layout")
      .organization(JHipsterModuleOrganization.builder().addDependency(HTMX_WEBJARS).addDependency(THYMELEAF_TEMPLATE).build())
      .tags(TAG_SERVER, TAG_SPRING, TAG_BOOT, TAG_THYMELEAF, TAG_WEBJAR)
      .factory(thymeleafTemplate::buildThymeleafHtmxWebjarsModule);
  }

  @Bean
  public JHipsterModuleResource thymeleafTemplateAlpineWebjarsModule(ThymeleafTemplateModuleApplicationService thymeleafTemplate) {
    return JHipsterModuleResource.builder()
      .slug(THYMELEAF_TEMPLATE_ALPINEJS_WEBJAR)
      .propertiesDefinition(
        JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().addSpringConfigurationFormat().build()
      )
      .apiDoc(GROUP_SPRING_BOOT_THYMELEAF, "Add alpine webjars scripts to thymeleaf layout")
      .organization(JHipsterModuleOrganization.builder().addDependency(ALPINE_JS_WEBJARS).addDependency(THYMELEAF_TEMPLATE).build())
      .tags(TAG_SERVER, TAG_SPRING, TAG_BOOT, TAG_THYMELEAF, TAG_WEBJAR)
      .factory(thymeleafTemplate::buildThymeleafAlpinejsWebjarsModule);
  }
}
