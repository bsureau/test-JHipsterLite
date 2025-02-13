package tech.jhipster.lite.generator.client.vue.security.oauth2_keycloak.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.vue.security.oauth2_keycloak.domain.VueOAuth2KeycloakModulesFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class VueOAuth2KeycloakApplicationService {

  private final VueOAuth2KeycloakModulesFactory factory;

  public VueOAuth2KeycloakApplicationService() {
    factory = new VueOAuth2KeycloakModulesFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
