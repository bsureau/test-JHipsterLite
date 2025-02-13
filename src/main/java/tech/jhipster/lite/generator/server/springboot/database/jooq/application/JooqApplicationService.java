package tech.jhipster.lite.generator.server.springboot.database.jooq.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.database.jooq.domain.JooqModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class JooqApplicationService {

  private final JooqModuleFactory factory;

  public JooqApplicationService() {
    factory = new JooqModuleFactory();
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
