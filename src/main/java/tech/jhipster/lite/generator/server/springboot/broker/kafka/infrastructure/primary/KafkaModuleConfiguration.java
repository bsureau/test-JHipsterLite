package tech.jhipster.lite.generator.server.springboot.broker.kafka.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SPRING_BOOT;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SPRING_BOOT_KAFKA;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SPRING_BOOT_KAFKA_AKHQ;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SPRING_BOOT_KAFKA_SAMPLE_PRODUCER_CONSUMER;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.broker.kafka.application.KafkaApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class KafkaModuleConfiguration {

  private static final String TAG = "Spring Boot - Broker";
  private static final String BROKER = "broker";
  private static final String SPRING_BOOT_TAG = "spring-boot";
  private static final String SPRING = "spring";
  private static final String SERVER = "server";

  @Bean
  JHipsterModuleResource kafkaResourceInit(KafkaApplicationService kafkaApplicationService) {
    return JHipsterModuleResource.builder()
      .slug(SPRING_BOOT_KAFKA)
      .propertiesDefinition(
        JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().addSpringConfigurationFormat().build()
      )
      .apiDoc(TAG, "Add Kafka dependencies, with testcontainers")
      .organization(JHipsterModuleOrganization.builder().addDependency(SPRING_BOOT).build())
      .tags(SERVER, SPRING, SPRING_BOOT_TAG, BROKER)
      .factory(kafkaApplicationService::init);
  }

  @Bean
  JHipsterModuleResource kafkaResourceSampleProducerConsumer(KafkaApplicationService kafkaApplicationService) {
    return JHipsterModuleResource.builder()
      .slug(SPRING_BOOT_KAFKA_SAMPLE_PRODUCER_CONSUMER)
      .propertiesDefinition(
        JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().addSpringConfigurationFormat().build()
      )
      .apiDoc("Sample Feature", "Add sample Kafka producer and consumer")
      .organization(kafkaDependency())
      .tags(SERVER, SPRING, SPRING_BOOT_TAG, BROKER)
      .factory(kafkaApplicationService::addSampleProducerConsumer);
  }

  @Bean
  JHipsterModuleResource kafkaResourceAkhq(KafkaApplicationService kafkaApplicationService) {
    return JHipsterModuleResource.builder()
      .slug(SPRING_BOOT_KAFKA_AKHQ)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addIndentation().build())
      .apiDoc(TAG, "Add AKHQ")
      .organization(kafkaDependency())
      .tags(SERVER, SPRING, SPRING_BOOT_TAG, BROKER)
      .factory(kafkaApplicationService::addAkhq);
  }

  private JHipsterModuleOrganization kafkaDependency() {
    return JHipsterModuleOrganization.builder().addDependency(SPRING_BOOT_KAFKA).build();
  }
}
