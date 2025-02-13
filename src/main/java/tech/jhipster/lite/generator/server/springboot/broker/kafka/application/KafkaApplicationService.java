package tech.jhipster.lite.generator.server.springboot.broker.kafka.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.broker.kafka.domain.KafkaModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class KafkaApplicationService {

  private final KafkaModuleFactory kafkaModuleFactory;

  public KafkaApplicationService(DockerImages dockerImages) {
    this.kafkaModuleFactory = new KafkaModuleFactory(dockerImages);
  }

  public JHipsterModule init(JHipsterModuleProperties properties) {
    return kafkaModuleFactory.buildModuleInit(properties);
  }

  public JHipsterModule addSampleProducerConsumer(JHipsterModuleProperties properties) {
    return kafkaModuleFactory.buildModuleSampleProducerConsumer(properties);
  }

  public JHipsterModule addAkhq(JHipsterModuleProperties properties) {
    return kafkaModuleFactory.buildModuleAkhq(properties);
  }
}
