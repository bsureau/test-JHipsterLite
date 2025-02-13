package tech.jhipster.lite.generator.server.javatool.frontendmaven.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.javatool.frontendmaven.domain.FrontendJavaBuildToolModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.npm.NpmVersions;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class FrontendJavaBuildToolApplicationService {

  private final FrontendJavaBuildToolModuleFactory factory;

  public FrontendJavaBuildToolApplicationService(NpmVersions npmVersions) {
    factory = new FrontendJavaBuildToolModuleFactory(npmVersions);
  }

  public JHipsterModule buildFrontendMavenModule(JHipsterModuleProperties properties) {
    return factory.buildFrontendMavenModule(properties);
  }

  public JHipsterModule buildFrontendGradleModule(JHipsterModuleProperties properties) {
    return factory.buildFrontendGradleModule(properties);
  }

  public JHipsterModule buildFrontendMavenCacheModule(JHipsterModuleProperties properties) {
    return factory.buildFrontendMavenCacheModule(properties);
  }
}
