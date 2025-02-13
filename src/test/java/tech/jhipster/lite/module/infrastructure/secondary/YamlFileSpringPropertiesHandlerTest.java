package tech.jhipster.lite.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static tech.jhipster.lite.TestFileUtils.content;
import static tech.jhipster.lite.TestFileUtils.loadDefaultProperties;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyValue;

import java.nio.file.Path;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.Indentation;
import tech.jhipster.lite.module.domain.javaproperties.Comment;
import tech.jhipster.lite.shared.error.domain.GeneratorException;

@UnitTest
class YamlFileSpringPropertiesHandlerTest {

  public static final Path EXISTING_SPRING_CONFIGURATION = Path.of(
    "src/test/resources/projects/project-with-spring-application-yaml/application.yml"
  );

  @Nested
  class SetValue {

    @Test
    void shouldCreateUnknownFile() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setValue(propertyKey("springdoc.swagger-ui.operationsSorter"), propertyValue("alpha"));

      assertThat(content(yamlFile)).contains(
        """
        springdoc:
          swagger-ui:
            operationsSorter: alpha
        """
      );
    }

    @Test
    void shouldAppendPropertyToFileWithProperties() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      loadDefaultProperties(EXISTING_SPRING_CONFIGURATION, yamlFile);
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setValue(propertyKey("springdoc.swagger-ui.operationsSorter"), propertyValue("alpha"));

      assertThat(content(yamlFile)).contains(
        """
        spring:
          application:
            name: JHLite # This is the name of the application

        # Logging
        logging:
          level:
            tech.jhipster.lite: INFO
        springdoc:
          swagger-ui:
            operationsSorter: alpha
        """
      );
    }

    @Test
    void shouldKeepExistingOrderWhenReplacingAProperty() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      loadDefaultProperties(
        Path.of("src/test/resources/projects/project-with-spring-application-yaml/more-complex-application.yml"),
        yamlFile
      );
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setValue(propertyKey("spring.datasource.driver-class-name"), propertyValue("org.postgresql.Driver"));

      assertThat(content(yamlFile)).contains(
        """
        spring:
          data:
            jpa:
              repositories:
                bootstrap-mode: deferred
          datasource:
            driver-class-name: org.postgresql.Driver
            hikari:
              auto-commit: false
              poolName: Hikari
            password: ''
            type: com.zaxxer.hikari.HikariDataSource
            url: jdbc:postgresql://localhost:5432/myapp
            username: myapp
        """
      );
    }

    @Test
    void shouldRespectProjectIndentation() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.from(4));

      handler.setValue(propertyKey("springdoc.swagger-ui.operationsSorter"), propertyValue("alpha"));

      assertThat(content(yamlFile)).contains(
        """
        springdoc:
            swagger-ui:
                operationsSorter: alpha
        """
      );
    }

    @Test
    void shouldPreserveExistingComments() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      loadDefaultProperties(EXISTING_SPRING_CONFIGURATION, yamlFile);
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.from(4));

      handler.setValue(propertyKey("springdoc.swagger-ui.operationsSorter"), propertyValue("alpha"));

      assertThat(content(yamlFile)).contains(
        """
        # Logging
        logging:
        """
      );
    }

    @Test
    void shouldReplaceExistingProperty() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      loadDefaultProperties(EXISTING_SPRING_CONFIGURATION, yamlFile);
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setValue(propertyKey("spring.application.name"), propertyValue("alpha"));

      assertThat(content(yamlFile))
        .contains(
          """
          spring:
            application:
              name: alpha
          """
        )
        .doesNotContain(
          """
          spring:
            application:
              name: JHLite
          """
        );
    }

    @Test
    void shouldHandleEscapedKeyWithDot() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setValue(
        propertyKey("kafka.consumer.'[key.deserializer]'"),
        propertyValue("org.apache.kafka.common.serialization.StringDeserializer")
      );

      assertThat(content(yamlFile)).contains(
        """
        kafka:
          consumer:
            '[key.deserializer]': org.apache.kafka.common.serialization.StringDeserializer
        """
      );
    }

    @Test
    void shouldHandleBooleanValue() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setValue(propertyKey("coverage.enabled"), propertyValue(true));

      assertThat(content(yamlFile)).contains(
        """
        coverage:
          enabled: true
        """
      );
    }

    @Test
    void shouldHandleIntegerValue() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setValue(propertyKey("coverage.count"), propertyValue(10));

      assertThat(content(yamlFile)).contains(
        """
        coverage:
          count: 10
        """
      );
    }

    @Test
    void shouldHandleLongValue() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setValue(propertyKey("coverage.count"), propertyValue(10L));

      assertThat(content(yamlFile)).contains(
        """
        coverage:
          count: 10
        """
      );
    }

    @Test
    void shouldHandleDoubleValue() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setValue(propertyKey("coverage.count"), propertyValue(10.5));

      assertThat(content(yamlFile)).contains(
        """
        coverage:
          count: 10.5
        """
      );
    }

    @Test
    void shouldHandleFloatValue() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setValue(propertyKey("coverage.count"), propertyValue(10.5f));

      assertThat(content(yamlFile)).contains(
        """
        coverage:
          count: 10.5
        """
      );
    }

    @Test
    void shouldHandleCollectionValue() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setValue(propertyKey("coverage.count"), propertyValue(10, 50));

      assertThat(content(yamlFile)).contains(
        """
        coverage:
          count:
          - 10
          - 50
        """
      );
    }

    @Test
    void shouldGenerateExceptionWhenConfigurationIsInconsistent() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);
      handler.setValue(propertyKey("coverage.count"), propertyValue(10));

      assertThatExceptionOfType(GeneratorException.class).isThrownBy(() ->
        handler.setValue(propertyKey("coverage.count.value"), propertyValue(10))
      );
    }
  }

  @Nested
  class SetComment {

    @Test
    void shouldAddCommentToExistingPropertyKey() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      loadDefaultProperties(EXISTING_SPRING_CONFIGURATION, yamlFile);
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setComment(propertyKey("spring.application.name"), new Comment("Application Name"));

      assertThat(content(yamlFile)).contains(
        """
        spring:
          application:
            # Application Name
            name: JHLite # This is the name of the application
        """
      );
    }

    @Test
    void shouldAddMultiLineCommentToExistingPropertyKey() {
      Path yamlFile = Path.of(TestFileUtils.tmpDirForTest(), "src/main/resources/application.yml");
      loadDefaultProperties(EXISTING_SPRING_CONFIGURATION, yamlFile);
      YamlFileSpringPropertiesHandler handler = new YamlFileSpringPropertiesHandler(yamlFile, Indentation.DEFAULT);

      handler.setComment(
        propertyKey("spring.application.name"),
        new Comment(
          """
          This is a
          multiline
          comment
          """
        )
      );

      assertThat(content(yamlFile)).contains(
        """
        spring:
          application:
            # This is a
            # multiline
            # comment
            name: JHLite # This is the name of the application
        """
      );
    }
  }
}
