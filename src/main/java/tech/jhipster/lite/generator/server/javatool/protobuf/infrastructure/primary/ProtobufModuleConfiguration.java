package tech.jhipster.lite.generator.server.javatool.protobuf.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.javatool.protobuf.application.ProtobufApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;
import tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug;

@Configuration
class ProtobufModuleConfiguration {

  @Bean
  JHipsterModuleResource protobufModule(ProtobufApplicationService protobuf) {
    return JHipsterModuleResource.builder()
      .slug(JHLiteModuleSlug.PROTOBUF)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().build())
      .apiDoc("Java", "Add protobuf support")
      .organization(JHipsterModuleOrganization.builder().addDependency(JHLiteModuleSlug.JAVA_BASE).build())
      .tags("server", "protobuf")
      .factory(protobuf::buildProtobufModule);
  }

  @Bean
  JHipsterModuleResource protobufBackwardsCompatibilityCheckModule(ProtobufApplicationService protobuf) {
    return JHipsterModuleResource.builder()
      .slug(JHLiteModuleSlug.PROTOBUF_BACKWARDS_COMPATIBILITY_CHECK)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().build())
      .apiDoc("Java", "Add protobuf backwards compatibility check")
      .organization(
        JHipsterModuleOrganization.builder().addDependency(JHLiteModuleSlug.PROTOBUF).addDependency(JHLiteModuleSlug.MAVEN_JAVA).build()
      )
      .tags("server", "protobuf")
      .factory(protobuf::buildProtobufBackwardsCompatibilityCheckModule);
  }
}
