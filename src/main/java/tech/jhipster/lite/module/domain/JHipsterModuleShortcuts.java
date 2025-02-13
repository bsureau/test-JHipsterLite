package tech.jhipster.lite.module.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.lineAfterRegex;
import static tech.jhipster.lite.module.domain.JHipsterModule.lineAfterText;
import static tech.jhipster.lite.module.domain.JHipsterModule.lineBeforeText;
import static tech.jhipster.lite.module.domain.JHipsterModule.path;
import static tech.jhipster.lite.module.domain.JHipsterModule.regex;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;
import static tech.jhipster.lite.module.domain.replacement.ReplacementCondition.always;

import java.util.function.Consumer;
import java.util.regex.Pattern;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.replacement.OptionalReplacer;
import tech.jhipster.lite.module.domain.replacement.TextNeedleAfterReplacer;
import tech.jhipster.lite.module.domain.replacement.TextNeedleBeforeReplacer;
import tech.jhipster.lite.shared.error.domain.Assert;

final class JHipsterModuleShortcuts {

  private static final JHipsterProjectFilePath README = path("README.md");
  private static final TextNeedleBeforeReplacer JHIPSTER_DOCUMENTATION_NEEDLE = lineBeforeText("\n<!-- jhipster-needle-documentation -->");
  private static final TextNeedleBeforeReplacer JHIPSTER_LOCAL_ENVIRONMENT_NEEDLE = lineBeforeText(
    "\n<!-- jhipster-needle-localEnvironment -->"
  );

  private static final TextNeedleAfterReplacer JHIPSTER_PREREQUISITES = lineAfterText("\n## Prerequisites");

  private static final JHipsterProjectFilePath SPRING_MAIN_LOG_FILE = path("src/main/resources/logback-spring.xml");
  private static final JHipsterProjectFilePath SPRING_TEST_LOG_FILE = path("src/test/resources/logback.xml");
  private static final TextNeedleBeforeReplacer JHIPSTER_LOGGER_NEEDLE = lineBeforeText("<!-- jhipster-needle-logback-add-log -->");

  private static final Pattern DEFAULT_LINTSTAGED_CONFIGURATION_ENTRY = Pattern.compile("\\s*'\\*': \\[\\s*].*");

  private JHipsterModuleShortcuts() {}

  static Consumer<JHipsterModuleBuilder> documentation(DocumentationTitle title, JHipsterSource source) {
    Assert.notNull("title", title);
    Assert.notNull("source", source);

    return builder -> {
      String target = "documentation/" + title.filename() + source.extension();
      builder.files().add(source, to(target));

      String markdownLink = "- [" + title.get() + "](" + target + ")";
      builder.optionalReplacements().in(README).add(JHIPSTER_DOCUMENTATION_NEEDLE, markdownLink);
    };
  }

  static Consumer<JHipsterModuleBuilder> localEnvironment(LocalEnvironment localEnvironment) {
    Assert.notNull("localEnvironment", localEnvironment);
    return builder -> builder.optionalReplacements().in(README).add(JHIPSTER_LOCAL_ENVIRONMENT_NEEDLE, localEnvironment.get());
  }

  static Consumer<JHipsterModuleBuilder> prerequisites(String prerequisites) {
    Assert.notBlank("prerequisites", prerequisites);
    return builder -> builder.optionalReplacements().in(README).add(JHIPSTER_PREREQUISITES, prerequisites);
  }

  static Consumer<JHipsterModuleBuilder> springTestLogger(String name, LogLevel level) {
    Assert.notBlank("name", name);
    Assert.notNull("level", level);

    return builder ->
      builder.optionalReplacements().in(SPRING_TEST_LOG_FILE).add(logConfigurationEntry(name, level, builder.indentation()));
  }

  static Consumer<JHipsterModuleBuilder> springMainLogger(String name, LogLevel level) {
    Assert.notBlank("name", name);
    Assert.notNull("level", level);

    return builder ->
      builder.optionalReplacements().in(SPRING_MAIN_LOG_FILE).add(logConfigurationEntry(name, level, builder.indentation()));
  }

  private static OptionalReplacer logConfigurationEntry(String name, LogLevel level, Indentation indentation) {
    return new OptionalReplacer(JHIPSTER_LOGGER_NEEDLE, logger(name, level, indentation));
  }

  private static String logger(String name, LogLevel level, Indentation indentation) {
    return new StringBuilder()
      .append(indentation.spaces())
      .append("<logger name=\"")
      .append(name)
      .append("\" level=\"")
      .append(level.value())
      .append("\" />")
      .toString();
  }

  static Consumer<JHipsterModuleBuilder> integrationTestExtension(String extensionClass) {
    Assert.notBlank("extensionClass", extensionClass);

    return builder ->
      builder
        .mandatoryReplacements()
        .in(path("src/test/java").append(builder.packagePath()).append("IntegrationTest.java"))
        .add(
          lineBeforeText("import org.springframework.boot.test.context.SpringBootTest;"),
          "import org.junit.jupiter.api.extension.ExtendWith;"
        )
        .add(lineBeforeText("public @interface"), "@ExtendWith(" + extensionClass + ".class)");
  }

  static Consumer<JHipsterModuleBuilder> preCommitActions(StagedFilesFilter stagedFilesFilter, PreCommitCommands preCommitCommands) {
    Assert.notNull("stagedFilesFilter", stagedFilesFilter);
    Assert.notNull("preCommitCommands", preCommitCommands);

    return builder -> {
      String newLintStagedConfigurationEntry =
        "%s'%s': %s,".formatted(builder.properties().indentation().times(1), stagedFilesFilter, preCommitCommands);

      builder
        .optionalReplacements()
        .in(path(".lintstagedrc.cjs"))
        .add(regex(always(), DEFAULT_LINTSTAGED_CONFIGURATION_ENTRY), "")
        .add(lineAfterRegex("module.exports = \\{"), newLintStagedConfigurationEntry);
    };
  }
}
