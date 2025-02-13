package tech.jhipster.lite.generator.server.springboot.springcloud.eureka.domain;

import static tech.jhipster.lite.generator.server.springboot.springcloud.common.domain.SpringCloudModuleDependencies.SPRING_CLOUD_GROUP;
import static tech.jhipster.lite.generator.server.springboot.springcloud.common.domain.SpringCloudModuleDependencies.springCloudDependenciesManagement;
import static tech.jhipster.lite.module.domain.JHipsterModule.artifactId;
import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyValue;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;
import static tech.jhipster.lite.module.domain.JHipsterModule.versionSlug;

import java.util.Locale;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.javaproperties.PropertyValue;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.base64.domain.Base64Utils;
import tech.jhipster.lite.shared.error.domain.Assert;

public class EurekaModuleFactory {

  private static final String JWT_BASE_64_SECRET = "jwtBase64Secret";
  private static final PropertyValue TRUE_VALUE = propertyValue(true);
  private static final PropertyValue FALSE_VALUE = propertyValue(false);
  private static final JHipsterSource SPRING_CLOUD_SOURCE = from("server/springboot/springcloud/configclient");
  private static final JHipsterSource EUREKA_SOURCE = from("server/springboot/springcloud/eureka");

  private final DockerImages dockerImages;

  public EurekaModuleFactory(DockerImages dockerImages) {
    this.dockerImages = dockerImages;
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String jwtBase64secret = properties.getOrDefaultString(JWT_BASE_64_SECRET, Base64Utils.getBase64Secret());
    String baseName = properties.projectBaseName().get();
    String lowerCaseBaseName = baseName.toLowerCase(Locale.ROOT);

    //@formatter:off
    return moduleBuilder(properties)
      .context()
        .put("jhipsterRegistryDockerImage", dockerImages.get("jhipster/jhipster-registry").fullName())
        .put("base64JwtSecret", jwtBase64secret)
        .and()
      .javaDependencies()
        .addDependencyManagement(springCloudDependenciesManagement())
        .addDependency(SPRING_CLOUD_GROUP, artifactId("spring-cloud-starter-bootstrap"))
        .addDependency(
          SPRING_CLOUD_GROUP,
          artifactId("spring-cloud-starter-netflix-eureka-client"),
          versionSlug("spring-cloud-netflix-eureka-client")
        )
        .and()
      .files()
        .add(SPRING_CLOUD_SOURCE.template("jhipster-registry.yml"), to("src/main/docker/jhipster-registry.yml"))
        .add(
            EUREKA_SOURCE.template("application.config.properties"),
            to("src/main/docker/central-server-config/localhost-config/application.properties")
        )
        .and()
      .springMainBootstrapProperties()
        .set(propertyKey("spring.application.name"), propertyValue(baseName))
        .set(propertyKey("spring.cloud.compatibility-verifier.enabled"), FALSE_VALUE)
        .set(propertyKey("eureka.client.service-url.defaultZone"), propertyValue("http://admin:admin@localhost:8761/eureka"))
        .set(propertyKey("eureka.client.enabled"), TRUE_VALUE)
        .set(propertyKey("eureka.client.healthcheck.enabled"), TRUE_VALUE)
        .set(propertyKey("eureka.client.fetch-registry"), TRUE_VALUE)
        .set(propertyKey("eureka.client.register-with-eureka"), TRUE_VALUE)
        .set(propertyKey("eureka.client.instance-info-replication-interval-seconds"), propertyValue(10))
        .set(propertyKey("eureka.client.registry-fetch-interval-seconds"), propertyValue(10))
        .set(propertyKey("eureka.instance.appname"), propertyValue(lowerCaseBaseName))
        .set(propertyKey("eureka.instance.instance-id"), propertyValue(instanceId(lowerCaseBaseName)))
        .set(propertyKey("eureka.instance.lease-renewal-interval-in-seconds"), propertyValue(5))
        .set(propertyKey("eureka.instance.lease-expiration-duration-in-seconds"), propertyValue(10))
        .set(propertyKey("eureka.instance.status-page-url-path"), propertyValue("${management.endpoints.web.base-path}/info"))
        .set(propertyKey("eureka.instance.health-check-url-path"), propertyValue("${management.endpoints.web.base-path}/health"))
        .and()
      .springTestBootstrapProperties()
        .set(propertyKey("spring.application.name"), propertyValue(baseName))
        .set(propertyKey("eureka.client.enabled"), FALSE_VALUE)
        .set(propertyKey("spring.cloud.compatibility-verifier.enabled"), FALSE_VALUE)
        .and()
      .build();
    //@formatter:on
  }

  private String instanceId(String lowerCaseBaseName) {
    return lowerCaseBaseName + ":${spring.application.instance-id:${random.value}}";
  }
}
