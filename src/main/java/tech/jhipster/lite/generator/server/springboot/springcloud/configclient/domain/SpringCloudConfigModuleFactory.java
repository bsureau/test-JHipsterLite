package tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain;

import static tech.jhipster.lite.generator.server.springboot.springcloud.common.domain.SpringCloudModuleDependencies.SPRING_CLOUD_GROUP;
import static tech.jhipster.lite.generator.server.springboot.springcloud.common.domain.SpringCloudModuleDependencies.springCloudDependenciesManagement;
import static tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.artifactId;
import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyValue;
import static tech.jhipster.lite.module.domain.JHipsterModule.springProfile;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcMainDocker;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.javaproperties.JHipsterModuleSpringProperties.JHipsterModuleSpringPropertiesBuilder;
import tech.jhipster.lite.module.domain.javaproperties.PropertyValue;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.base64.domain.Base64Utils;
import tech.jhipster.lite.shared.error.domain.Assert;

public class SpringCloudConfigModuleFactory {

  private static final String JWT_BASE_64_SECRET = "jwtBase64Secret";

  private static final JHipsterSource SOURCE = from("server/springboot/springcloud/configclient");

  private static final PropertyValue FALSE_VALUE = propertyValue(false);

  private final DockerImages dockerImages;

  public SpringCloudConfigModuleFactory(DockerImages dockerImages) {
    this.dockerImages = dockerImages;
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    PropertyValue baseNameValue = propertyValue(properties.projectBaseName().get());

    JHipsterModuleBuilder builder = initBuilder(properties);

    appendCommonProperties(builder.springMainBootstrapProperties(), baseNameValue);
    appendCommonProperties(builder.springMainBootstrapProperties(springProfile("local")), baseNameValue);

    //@formatter:off
    return builder
      .springMainBootstrapProperties()
        .set(propertyKey("spring.cloud.config.fail-fast"), propertyValue(true))
        .and()
      .springMainBootstrapProperties(springProfile("local"))
        .set(propertyKey("spring.cloud.config.fail-fast"), FALSE_VALUE)
        .and()
      .springTestBootstrapProperties()
        .set(propertyKey("spring.application.name"), baseNameValue)
        .set(propertyKey("spring.cloud.config.enabled"), FALSE_VALUE)
        .and()
      .build();
    //@formatter:on
  }

  private JHipsterModuleBuilder initBuilder(JHipsterModuleProperties properties) {
    String jwtBase64secret = properties.getOrDefaultString(JWT_BASE_64_SECRET, Base64Utils.getBase64Secret());

    //@formatter:off
    return moduleBuilder(properties)
      .context()
        .put("jhipsterRegistryDockerImage", dockerImages.get("jhipster/jhipster-registry").fullName())
        .put("base64JwtSecret", jwtBase64secret)
        .and()
      .javaDependencies()
        .addDependencyManagement(springCloudDependenciesManagement())
        .addDependency(SPRING_CLOUD_GROUP, artifactId("spring-cloud-starter-bootstrap"))
        .addDependency(SPRING_CLOUD_GROUP, artifactId("spring-cloud-starter-config"))
        .and()
      .files()
        .add(SOURCE.template("jhipster-registry.yml"), toSrcMainDocker().append("jhipster-registry.yml"))
        .add(
          SOURCE.template("application.config.properties"),
          toSrcMainDocker().append("central-server-config/localhost-config/application.properties")
        )
      .and();
    //@formatter:on
  }

  private void appendCommonProperties(JHipsterModuleSpringPropertiesBuilder builder, PropertyValue baseNameValue) {
    builder
      .set(propertyKey("spring.application.name"), baseNameValue)
      .set(propertyKey("jhipster.registry.password"), propertyValue("admin"))
      .set(propertyKey("spring.cloud.compatibility-verifier.enabled"), FALSE_VALUE)
      .set(propertyKey("spring.cloud.config.label"), propertyValue("main"))
      .set(propertyKey("spring.cloud.config.name"), baseNameValue)
      .set(propertyKey("spring.cloud.config.retry.initial-interval"), propertyValue(1000))
      .set(propertyKey("spring.cloud.config.retry.max-attempts"), propertyValue(100))
      .set(propertyKey("spring.cloud.config.retry.max-interval"), propertyValue(2000))
      .set(propertyKey("spring.cloud.config.uri"), propertyValue("http://admin:${jhipster.registry.password}@localhost:8761/config"));
  }
}
