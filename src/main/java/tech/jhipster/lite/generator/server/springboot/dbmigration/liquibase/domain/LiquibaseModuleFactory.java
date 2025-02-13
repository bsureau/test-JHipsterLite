package tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.artifactId;
import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.groupId;
import static tech.jhipster.lite.module.domain.JHipsterModule.mavenPlugin;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.pluginExecution;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyValue;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcMainJava;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcTestJava;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcTestResources;
import static tech.jhipster.lite.module.domain.JHipsterModule.versionSlug;
import static tech.jhipster.lite.module.domain.properties.SpringConfigurationFormat.PROPERTIES;
import static tech.jhipster.lite.module.domain.properties.SpringConfigurationFormat.YAML;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.LogLevel;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin.MavenPluginOptionalBuilder;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class LiquibaseModuleFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/dbmigration/liquibase");

  private static final String LIQUIBASE_SECONDARY = "wire/liquibase/infrastructure/secondary";

  private static final String LIQUIBASE = "liquibase";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
        .addDependency(groupId("org.liquibase"), artifactId("liquibase-core"), versionSlug(LIQUIBASE))
        .and()
      .springMainProperties()
        .set(propertyKey("spring.liquibase.change-log"), propertyValue("classpath:config/liquibase/master.xml"))
        .and()
      .springTestProperties()
        .set(propertyKey("spring.liquibase.contexts"), propertyValue("test"))
        .and()
      .files()
        .add(SOURCE.file("resources/master.xml"), to("src/main/resources/config/liquibase/master.xml"))
        .add(SOURCE.file("resources/0000000000_example.xml"), to("src/main/resources/config/liquibase/changelog/0000000000_example.xml"))
        .and()
      .springMainLogger(LIQUIBASE, LogLevel.WARN)
      .springMainLogger("LiquibaseSchemaResolver", LogLevel.INFO)
      .springMainLogger("com.zaxxer.hikari", LogLevel.WARN)
      .springTestLogger(LIQUIBASE, LogLevel.WARN)
      .springTestLogger("LiquibaseSchemaResolver", LogLevel.INFO)
      .springTestLogger("com.zaxxer.hikari", LogLevel.WARN)
      .build();
    //@formatter:on
  }

  public JHipsterModule buildAsyncModule(JHipsterModuleProperties properties) {
    String packagePath = properties.packagePath();

    //@formatter:off
    return moduleBuilder(properties)
      .context()
        .put("yamlSpringConfigurationFormat", properties.springConfigurationFormat() == YAML)
        .put("propertiesSpringConfigurationFormat", properties.springConfigurationFormat() == PROPERTIES)
        .and()
      .files()
        .batch(SOURCE.append("main"), toSrcMainJava().append(packagePath).append(LIQUIBASE_SECONDARY))
          .addTemplate("AsyncSpringLiquibase.java")
          .addTemplate("LiquibaseConfiguration.java")
          .addTemplate("SpringLiquibaseUtil.java")
          .and()
        .batch(SOURCE.append("test"), toSrcTestJava().append(packagePath).append(LIQUIBASE_SECONDARY))
          .addTemplate("AsyncSpringLiquibaseTest.java")
          .addTemplate("LiquibaseConfigurationIT.java")
          .addTemplate("SpringLiquibaseUtilTest.java")
          .and()
        .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildLinterModule(JHipsterModuleProperties properties) {
    //@formatter:off
    return moduleBuilder(properties)
      .mavenPlugins()
        .plugin(liquibaseLinterMavenPlugin().build())
        .pluginManagement(liquibaseLinterMavenPluginManagement())
        .and()
      .files()
        .add(SOURCE.append("liquibase-linter.jsonc"), toSrcTestResources().append("liquibase-linter.jsonc"))
        .and()
      .build();
    //@formatter:on
  }

  private MavenPlugin liquibaseLinterMavenPluginManagement() {
    return liquibaseLinterMavenPlugin()
      .versionSlug("liquibase-linter-maven-plugin")
      .configuration(
        """
        <changeLogFile>src/main/resources/config/liquibase/master.xml</changeLogFile>
        <configurationFile>src/test/resources/liquibase-linter.jsonc</configurationFile>
        """
      )
      .addExecution(pluginExecution().goals("lint"))
      .build();
  }

  private static MavenPluginOptionalBuilder liquibaseLinterMavenPlugin() {
    return mavenPlugin().groupId("io.github.liquibase-linter").artifactId("liquibase-linter-maven-plugin");
  }
}
