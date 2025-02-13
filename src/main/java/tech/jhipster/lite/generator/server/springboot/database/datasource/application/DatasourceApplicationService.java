package tech.jhipster.lite.generator.server.springboot.database.datasource.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.database.datasource.domain.DatasourceModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class DatasourceApplicationService {

  private final DatasourceModuleFactory factory;

  public DatasourceApplicationService(DockerImages dockerImages) {
    factory = new DatasourceModuleFactory(dockerImages);
  }

  public JHipsterModule buildPostgresql(JHipsterModuleProperties properties) {
    return factory.buildPostgresql(properties);
  }

  public JHipsterModule buildMariaDB(JHipsterModuleProperties properties) {
    return factory.buildMariaDB(properties);
  }

  public JHipsterModule buildMsSQL(JHipsterModuleProperties properties) {
    return factory.buildMsSQL(properties);
  }

  public JHipsterModule buildMySQL(JHipsterModuleProperties properties) {
    return factory.buildMySQL(properties);
  }
}
