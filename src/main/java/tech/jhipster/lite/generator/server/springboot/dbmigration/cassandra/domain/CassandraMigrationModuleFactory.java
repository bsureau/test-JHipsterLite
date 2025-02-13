package tech.jhipster.lite.generator.server.springboot.dbmigration.cassandra.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.documentationTitle;
import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.javaDependency;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyValue;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcMainDocker;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcMainResources;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcTestJava;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class CassandraMigrationModuleFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/dbmigration/cassandra");
  private static final String CASSANDRA = "cassandra";
  private final DockerImages dockerImages;

  public CassandraMigrationModuleFactory(DockerImages dockerImages) {
    this.dockerImages = dockerImages;
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String packagePath = properties.packagePath();
    String packageName = properties.basePackage().get() + ".";

    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
        .addDependency(cassandraUnitDependency())
        .and()
      .context()
        .put("cassandraDockerImage", dockerImages.get(CASSANDRA).fullName())
        .and()
      .documentation(documentationTitle("Cassandra Migration"), SOURCE.file("cassandra-migration.md"))
      .startupCommands()
        .dockerCompose("src/main/docker/cassandra-migration.yml")
        .and()
      .files()
        .add(SOURCE.template("TestCassandraMigrationLoader.java"), toSrcTestJava().append(packagePath).append("TestCassandraMigrationLoader.java"))
        .add(SOURCE.template("Cassandra-Migration.Dockerfile"), toSrcMainDocker().append(CASSANDRA).append("Cassandra-Migration.Dockerfile"))
        .add(SOURCE.file("cassandra-migration.yml"), toSrcMainDocker().append("cassandra-migration.yml"))
        .add(SOURCE.file("create-migration-keyspace.cql"), toSrcMainResourcesCql().append("create-migration-keyspace.cql"))
        .add(SOURCE.file("README.md"), toSrcMainResourcesCql().append("changelog").append("README.md"))
        .batch(SOURCE, toSrcMainDockerScripts())
          .addFile("autoMigrate.sh")
          .addFile("execute-cql.sh")
          .and()
        .and()
      .springTestFactories()
        .append(propertyKey("org.springframework.context.ApplicationListener"), propertyValue(packageName + "TestCassandraMigrationLoader"))
        .and()
      .build();
    //@formatter:on
  }

  private JavaDependency cassandraUnitDependency() {
    return javaDependency()
      .groupId("org.cassandraunit")
      .artifactId("cassandra-unit")
      .versionSlug("cassandraunit")
      .scope(JavaDependencyScope.TEST)
      .build();
  }

  private JHipsterDestination toSrcMainResourcesCql() {
    return toSrcMainResources().append("config").append("cql");
  }

  private JHipsterDestination toSrcMainDockerScripts() {
    return toSrcMainDocker().append(CASSANDRA).append("scripts");
  }
}
