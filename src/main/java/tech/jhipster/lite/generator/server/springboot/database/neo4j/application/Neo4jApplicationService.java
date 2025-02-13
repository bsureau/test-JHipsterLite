package tech.jhipster.lite.generator.server.springboot.database.neo4j.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.database.neo4j.domain.Neo4jModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class Neo4jApplicationService {

  private final Neo4jModuleFactory factory;

  public Neo4jApplicationService(DockerImages dockerImages) {
    factory = new Neo4jModuleFactory(dockerImages);
  }

  public JHipsterModule build(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
