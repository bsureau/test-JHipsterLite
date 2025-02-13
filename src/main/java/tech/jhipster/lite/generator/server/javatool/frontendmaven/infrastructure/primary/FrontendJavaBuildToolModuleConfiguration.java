package tech.jhipster.lite.generator.server.javatool.frontendmaven.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.*;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.javatool.frontendmaven.application.FrontendJavaBuildToolApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class FrontendJavaBuildToolModuleConfiguration {

  private static final String[] TAGS = { "server", "tools" };
  private static final String FRONTEND_JAVA_PLUGIN = "Frontend Java Plugin";

  @Bean
  JHipsterModuleResource frontendMavenModule(FrontendJavaBuildToolApplicationService frontendJavaBuildTool) {
    return JHipsterModuleResource.builder()
      .slug(FRONTEND_MAVEN_PLUGIN)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().addIndentation().build())
      .apiDoc(FRONTEND_JAVA_PLUGIN, "Add Frontend Maven Plugin")
      .organization(
        JHipsterModuleOrganization.builder()
          .feature(FRONTEND_JAVA_BUILD_TOOL_PLUGIN)
          .addDependency(SPRING_SERVER)
          .addDependency(SPRING_MVC_SERVER)
          .addDependency(CLIENT_CORE)
          .addDependency(MAVEN_JAVA)
          .build()
      )
      .tags(TAGS)
      .factory(frontendJavaBuildTool::buildFrontendMavenModule);
  }

  @Bean
  JHipsterModuleResource frontendMavenCacheModule(FrontendJavaBuildToolApplicationService frontendJavaBuildTool) {
    return JHipsterModuleResource.builder()
      .slug(FRONTEND_MAVEN_PLUGIN_CACHE)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.EMPTY)
      .apiDoc(FRONTEND_JAVA_PLUGIN, "Add cache - by computing resources checksum - to avoid rebuilding frontend on successive maven builds")
      .organization(JHipsterModuleOrganization.builder().addDependency(FRONTEND_MAVEN_PLUGIN).build())
      .tags(TAGS)
      .factory(frontendJavaBuildTool::buildFrontendMavenCacheModule);
  }

  @Bean
  JHipsterModuleResource frontendGradleModule(FrontendJavaBuildToolApplicationService frontendJavaBuildTool) {
    return JHipsterModuleResource.builder()
      .slug(NODE_GRADLE_PLUGIN)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().addIndentation().build())
      .apiDoc(FRONTEND_JAVA_PLUGIN, "Add node-gradle plugin for building frontend with Gradle")
      .organization(
        JHipsterModuleOrganization.builder()
          .feature(FRONTEND_JAVA_BUILD_TOOL_PLUGIN)
          .addDependency(SPRING_SERVER)
          .addDependency(SPRING_MVC_SERVER)
          .addDependency(CLIENT_CORE)
          .addDependency(GRADLE_JAVA)
          .build()
      )
      .tags(TAGS)
      .factory(frontendJavaBuildTool::buildFrontendGradleModule);
  }
}
