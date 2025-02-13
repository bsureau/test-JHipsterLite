package tech.jhipster.lite.module.infrastructure.secondary.javadependency.gradle;

import static tech.jhipster.lite.module.domain.JHipsterModule.LINE_BREAK;
import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.regex;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;
import static tech.jhipster.lite.module.domain.replacement.ReplacementCondition.always;
import static tech.jhipster.lite.module.domain.replacement.ReplacementCondition.notMatchingRegex;
import static tech.jhipster.lite.module.infrastructure.secondary.javadependency.gradle.VersionsCatalog.libraryAlias;
import static tech.jhipster.lite.module.infrastructure.secondary.javadependency.gradle.VersionsCatalog.pluginAlias;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tech.jhipster.lite.module.domain.Indentation;
import tech.jhipster.lite.module.domain.JHipsterModuleContext;
import tech.jhipster.lite.module.domain.JHipsterProjectFilePath;
import tech.jhipster.lite.module.domain.buildproperties.BuildProperty;
import tech.jhipster.lite.module.domain.buildproperties.PropertyKey;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterFileContent;
import tech.jhipster.lite.module.domain.file.JHipsterModuleFile;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.file.JHipsterTemplatedFile;
import tech.jhipster.lite.module.domain.file.JHipsterTemplatedFiles;
import tech.jhipster.lite.module.domain.gradleplugin.BuildGradleImport;
import tech.jhipster.lite.module.domain.gradleplugin.GradleCommunityPlugin;
import tech.jhipster.lite.module.domain.gradleplugin.GradleCommunityProfilePlugin;
import tech.jhipster.lite.module.domain.gradleplugin.GradleCorePlugin;
import tech.jhipster.lite.module.domain.gradleplugin.GradlePluginConfiguration;
import tech.jhipster.lite.module.domain.javabuild.DependencySlug;
import tech.jhipster.lite.module.domain.javabuild.command.AddDirectJavaDependency;
import tech.jhipster.lite.module.domain.javabuild.command.AddDirectMavenPlugin;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradleConfiguration;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradlePlugin;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradleTasksTestInstruction;
import tech.jhipster.lite.module.domain.javabuild.command.AddJavaBuildProfile;
import tech.jhipster.lite.module.domain.javabuild.command.AddJavaDependencyManagement;
import tech.jhipster.lite.module.domain.javabuild.command.AddMavenBuildExtension;
import tech.jhipster.lite.module.domain.javabuild.command.AddMavenPluginManagement;
import tech.jhipster.lite.module.domain.javabuild.command.RemoveDirectJavaDependency;
import tech.jhipster.lite.module.domain.javabuild.command.RemoveJavaDependencyManagement;
import tech.jhipster.lite.module.domain.javabuild.command.SetBuildProperty;
import tech.jhipster.lite.module.domain.javabuild.command.SetVersion;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileActivation;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.module.domain.replacement.ContentReplacers;
import tech.jhipster.lite.module.domain.replacement.MandatoryFileReplacer;
import tech.jhipster.lite.module.domain.replacement.MandatoryReplacer;
import tech.jhipster.lite.module.domain.replacement.RegexNeedleBeforeReplacer;
import tech.jhipster.lite.module.domain.replacement.RegexReplacer;
import tech.jhipster.lite.module.infrastructure.secondary.FileSystemJHipsterModuleFiles;
import tech.jhipster.lite.module.infrastructure.secondary.FileSystemReplacer;
import tech.jhipster.lite.module.infrastructure.secondary.javadependency.JavaDependenciesCommandHandler;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.error.domain.GeneratorException;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public class GradleCommandHandler implements JavaDependenciesCommandHandler {

  private static final String COMMAND = "command";
  private static final String BUILD_GRADLE_FILE = "build.gradle.kts";
  private static final String PLUGIN_BUILD_GRADLE_FILE = "buildSrc/build.gradle.kts";
  private static final Pattern GRADLE_IMPORT_NEEDLE = Pattern.compile("^// jhipster-needle-gradle-imports$", Pattern.MULTILINE);
  private static final Pattern GRADLE_PLUGIN_NEEDLE = Pattern.compile("^\\s+// jhipster-needle-gradle-plugins$", Pattern.MULTILINE);
  private static final Pattern GRADLE_PLUGIN_PROJECT_EXTENSION_CONFIGURATION_NEEDLE = Pattern.compile(
    "^// jhipster-needle-gradle-plugins-configurations$",
    Pattern.MULTILINE
  );
  private static final Pattern GRADLE_IMPLEMENTATION_DEPENDENCY_NEEDLE = Pattern.compile(
    "^\\s+// jhipster-needle-gradle-implementation-dependencies$",
    Pattern.MULTILINE
  );
  private static final Pattern GRADLE_COMPILE_DEPENDENCY_NEEDLE = Pattern.compile(
    "^\\s+// jhipster-needle-gradle-compile-dependencies$",
    Pattern.MULTILINE
  );
  private static final Pattern GRADLE_RUNTIME_DEPENDENCY_NEEDLE = Pattern.compile(
    "^\\s+// jhipster-needle-gradle-runtime-dependencies$",
    Pattern.MULTILINE
  );
  private static final Pattern GRADLE_TEST_DEPENDENCY_NEEDLE = Pattern.compile(
    "^\\s+// jhipster-needle-gradle-test-dependencies$",
    Pattern.MULTILINE
  );
  private static final Pattern GRADLE_PROFILE_ACTIVATION_NEEDLE = Pattern.compile(
    "^// jhipster-needle-profile-activation$",
    Pattern.MULTILINE
  );
  private static final Pattern GRADLE_PROPERTY_NEEDLE = Pattern.compile("^// jhipster-needle-gradle-properties$", Pattern.MULTILINE);
  private static final Pattern GRADLE_FREE_CONFIGURATION_BLOCKS_NEEDLE = Pattern.compile(
    "^// jhipster-needle-gradle-free-configuration-blocks$",
    Pattern.MULTILINE
  );
  private static final Pattern GRADLE_TASKS_TEST_NEEDLE = Pattern.compile("^\\s+// jhipster-needle-gradle-tasks-test$", Pattern.MULTILINE);
  private static final String PROFILE_CONDITIONAL_TEMPLATE =
    """
    if (profiles.contains("%s")) {
      apply(plugin = "profile-%s")
    }\
    """;
  private static final String PROFILE_DEFAULT_ACTIVATION_CONDITIONAL_TEMPLATE =
    """
    if (profiles.isEmpty() || profiles.contains("%s")) {
      apply(plugin = "profile-%s")
    }\
    """;
  private static final String BUILD_GRADLE_PROFILE_PATH_TEMPLATE = "buildSrc/src/main/kotlin/profile-%s.gradle.kts";

  private final Indentation indentation;
  private final JHipsterProjectFolder projectFolder;
  private final JHipsterModuleContext context;
  private final VersionsCatalog versionsCatalog;
  private final FileSystemReplacer fileReplacer;
  private final FileSystemJHipsterModuleFiles files;

  public GradleCommandHandler(
    Indentation indentation,
    JHipsterProjectFolder projectFolder,
    JHipsterModuleContext context,
    FileSystemJHipsterModuleFiles files,
    FileSystemReplacer fileReplacer
  ) {
    Assert.notNull("indentation", indentation);
    Assert.notNull("projectFolder", projectFolder);
    Assert.notNull("context", context);

    this.indentation = indentation;
    this.projectFolder = projectFolder;
    this.context = context;
    this.versionsCatalog = new VersionsCatalog(projectFolder);
    this.fileReplacer = fileReplacer;
    this.files = files;
  }

  @Override
  public void handle(SetVersion command) {
    Assert.notNull(COMMAND, command);

    versionsCatalog.setVersion(command.version());
  }

  @Override
  public void handle(AddDirectJavaDependency command) {
    Assert.notNull(COMMAND, command);

    versionsCatalog.addLibrary(command.dependency());
    addDependencyToBuildGradle(command.dependency(), buildGradleFile(command.buildProfile()), command.buildProfile().isPresent());
  }

  private void addDependencyToBuildGradle(JavaDependency dependency, Path buildGradleFile, boolean forBuildProfile) {
    GradleDependencyScope gradleScope = gradleDependencyScope(dependency);

    String dependencyDeclaration = dependencyDeclaration(dependency, forBuildProfile);
    MandatoryReplacer replacer = new MandatoryReplacer(
      new RegexNeedleBeforeReplacer(
        (contentBeforeReplacement, newText) -> !contentBeforeReplacement.contains(newText),
        needleForGradleDependencyScope(gradleScope)
      ),
      dependencyDeclaration
    );
    fileReplacer.handle(
      projectFolder,
      ContentReplacers.of(new MandatoryFileReplacer(projectFolderRelativePathFrom(buildGradleFile), replacer)),
      context
    );
  }

  private Pattern needleForGradleDependencyScope(GradleDependencyScope gradleScope) {
    return switch (gradleScope) {
      case GradleDependencyScope.IMPLEMENTATION -> GRADLE_IMPLEMENTATION_DEPENDENCY_NEEDLE;
      case GradleDependencyScope.COMPILE_ONLY -> GRADLE_COMPILE_DEPENDENCY_NEEDLE;
      case GradleDependencyScope.RUNTIME_ONLY -> GRADLE_RUNTIME_DEPENDENCY_NEEDLE;
      case GradleDependencyScope.TEST_IMPLEMENTATION -> GRADLE_TEST_DEPENDENCY_NEEDLE;
    };
  }

  private String dependencyDeclaration(JavaDependency dependency, boolean forBuildProfile) {
    StringBuilder dependencyDeclaration = new StringBuilder()
      .append(indentation.times(1))
      .append(gradleDependencyScope(dependency).command())
      .append("(");
    var versionCatalogReference = forBuildProfile
      ? versionCatalogReferenceForBuildProfile(dependency)
      : versionCatalogReference(dependency);
    if (dependency.scope() == JavaDependencyScope.IMPORT) {
      dependencyDeclaration.append("platform(%s)".formatted(versionCatalogReference));
    } else {
      dependencyDeclaration.append(versionCatalogReference);
    }
    dependencyDeclaration.append(")");

    if (!dependency.exclusions().isEmpty()) {
      dependencyDeclaration.append(" {");
      for (var exclusion : dependency.exclusions()) {
        dependencyDeclaration.append(LINE_BREAK);
        dependencyDeclaration
          .append(indentation.times(2))
          .append("exclude(group = \"%s\", module = \"%s\")".formatted(exclusion.groupId(), exclusion.artifactId()));
      }
      dependencyDeclaration.append(LINE_BREAK);
      dependencyDeclaration.append(indentation.times(1)).append("}");
    }

    return dependencyDeclaration.toString();
  }

  private static String versionCatalogReferenceForBuildProfile(JavaDependency dependency) {
    return "libs.findLibrary(\"%s\").get()".formatted(applyVersionCatalogReferenceConvention(libraryAlias(dependency)));
  }

  private static String versionCatalogReference(JavaDependency dependency) {
    return "libs.%s".formatted(applyVersionCatalogReferenceConvention(libraryAlias(dependency)));
  }

  private static String applyVersionCatalogReferenceConvention(String rawVersionCatalogReference) {
    return rawVersionCatalogReference.replace("-", ".");
  }

  private static GradleDependencyScope gradleDependencyScope(JavaDependency dependency) {
    return switch (dependency.scope()) {
      case TEST -> GradleDependencyScope.TEST_IMPLEMENTATION;
      case PROVIDED -> GradleDependencyScope.COMPILE_ONLY;
      case RUNTIME -> GradleDependencyScope.RUNTIME_ONLY;
      default -> GradleDependencyScope.IMPLEMENTATION;
    };
  }

  @Override
  public void handle(RemoveDirectJavaDependency command) {
    versionsCatalog
      .retrieveDependencySlugsFrom(command.dependency())
      .stream()
      .filter(dependencyExistsFrom(command.buildProfile()))
      .forEach(dependencySlug -> {
        removeDependencyFromBuildGradle(dependencySlug, command.buildProfile());
        versionsCatalog.removeLibrary(command.dependency());
      });
  }

  private Predicate<DependencySlug> dependencyExistsFrom(Optional<BuildProfileId> buildProfile) {
    return dependencySlug -> {
      String buildGradleFileContent = readContent(buildGradleFile(buildProfile));
      return dependencyLinePattern(dependencySlug, buildProfile).matcher(buildGradleFileContent).find();
    };
  }

  @ExcludeFromGeneratedCodeCoverage(reason = "IOException is hard to test")
  private String readContent(Path path) {
    try {
      return Files.readString(path);
    } catch (IOException e) {
      throw GeneratorException.technicalError("Error reading file " + path + ": " + e.getMessage(), e);
    }
  }

  private void removeDependencyFromBuildGradle(DependencySlug dependencySlug, Optional<BuildProfileId> buildProfile) {
    MandatoryReplacer replacer = new MandatoryReplacer(
      new RegexReplacer(always(), dependencyLinePattern(dependencySlug, buildProfile)),
      ""
    );
    fileReplacer.handle(
      projectFolder,
      ContentReplacers.of(new MandatoryFileReplacer(projectFolderRelativePathFrom(buildGradleFile(buildProfile)), replacer)),
      context
    );
  }

  private Pattern dependencyLinePattern(DependencySlug dependencySlug, Optional<BuildProfileId> buildProfile) {
    String scopePattern = Stream.of(GradleDependencyScope.values())
      .map(GradleDependencyScope::command)
      .collect(Collectors.joining("|", "(?:", ")"));

    String libsPattern = buildProfile.isPresent() ? "libs\\.findLibrary\\(\"%s\"\\)\\.get\\(\\)" : "libs\\.%s";

    return Pattern.compile(
      "^\\s+%s\\((?:platform\\()?%s\\)?\\)(?:\\s+\\{(?:\\s+exclude\\([^)]*\\))+\\s+\\})?$".formatted(
          scopePattern,
          libsPattern.formatted(dependencySlug.slug().replace("-", "\\."))
        ),
      Pattern.MULTILINE
    );
  }

  @Override
  public void handle(RemoveJavaDependencyManagement command) {
    versionsCatalog
      .retrieveDependencySlugsFrom(command.dependency())
      .stream()
      .filter(dependencyExistsFrom(command.buildProfile()))
      .forEach(dependencySlug -> {
        removeDependencyFromBuildGradle(dependencySlug, command.buildProfile());
        versionsCatalog.removeLibrary(command.dependency());
      });
  }

  @Override
  public void handle(AddJavaDependencyManagement command) {
    versionsCatalog.addLibrary(command.dependency());
    addDependencyToBuildGradle(command.dependency(), buildGradleFile(command.buildProfile()), command.buildProfile().isPresent());
  }

  @Override
  public void handle(AddDirectMavenPlugin command) {
    // Maven specific commands are ignored
  }

  @Override
  public void handle(AddMavenPluginManagement command) {
    // Maven commands are ignored
  }

  @Override
  public void handle(SetBuildProperty command) {
    Assert.notNull(COMMAND, command);

    addPropertyTo(command.property(), buildGradleFile(command.buildProfile()));
  }

  private Path buildGradleFile(Optional<BuildProfileId> buildProfile) {
    return buildProfile
      .map(buildProfileId -> {
        File scriptPlugin = scriptPluginForProfile(buildProfileId);
        if (!scriptPlugin.exists()) {
          throw new MissingGradleProfileException(buildProfileId);
        }
        return scriptPlugin.toPath();
      })
      .orElse(projectFolder.filePath(BUILD_GRADLE_FILE));
  }

  private void addPropertyTo(BuildProperty property, Path buildGradleFile) {
    MandatoryReplacer replacer;
    if (propertyExistsFrom(property.key(), buildGradleFile)) {
      replacer = existingPropertyReplacer(property);
    } else {
      replacer = addNewPropertyReplacer(property);
    }

    fileReplacer.handle(
      projectFolder,
      ContentReplacers.of(new MandatoryFileReplacer(projectFolderRelativePathFrom(buildGradleFile), replacer)),
      context
    );
  }

  private JHipsterProjectFilePath projectFolderRelativePathFrom(Path buildGradleFile) {
    return new JHipsterProjectFilePath(Path.of(projectFolder.folder()).relativize(buildGradleFile).toString());
  }

  private MandatoryReplacer existingPropertyReplacer(BuildProperty property) {
    Pattern propertyLinePattern = Pattern.compile(
      "val %s by extra\\(\"(.*?)\"\\)".formatted(toCamelCasedKotlinVariable(property.key())),
      Pattern.MULTILINE
    );
    return new MandatoryReplacer(regex(notMatchingRegex(propertyLinePattern), propertyLinePattern), convertToKotlinFormat(property));
  }

  private static MandatoryReplacer addNewPropertyReplacer(BuildProperty property) {
    return new MandatoryReplacer(new RegexNeedleBeforeReplacer(always(), GRADLE_PROPERTY_NEEDLE), convertToKotlinFormat(property));
  }

  private static String convertToKotlinFormat(BuildProperty property) {
    return "val %s by extra(\"%s\")".formatted(toCamelCasedKotlinVariable(property.key()), property.value().get());
  }

  private static String toCamelCasedKotlinVariable(PropertyKey key) {
    return Arrays.stream(key.get().split("[.-]"))
      .map(s -> s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1).toLowerCase(Locale.ROOT))
      .collect(Collectors.collectingAndThen(Collectors.joining(), str -> str.substring(0, 1).toLowerCase(Locale.ROOT) + str.substring(1)));
  }

  private boolean propertyExistsFrom(PropertyKey key, Path buildGradleFile) {
    String content = readContent(buildGradleFile);

    return content.contains("val %s by extra(".formatted(toCamelCasedKotlinVariable(key)));
  }

  @Override
  public void handle(AddMavenBuildExtension command) {
    // Maven commands are ignored
  }

  @Override
  public void handle(AddJavaBuildProfile command) {
    Assert.notNull(COMMAND, command);

    enablePrecompiledScriptPlugins();

    File scriptPlugin = scriptPluginForProfile(command.buildProfileId());
    if (!scriptPlugin.exists()) {
      addProfileActivation(command);
      addScriptPluginForProfile(command.buildProfileId());
    }
  }

  private void enablePrecompiledScriptPlugins() {
    addFileToProject(from("buildtool/gradle/buildSrc/build.gradle.kts.template"), to(PLUGIN_BUILD_GRADLE_FILE));
    addFileToProject(from("buildtool/gradle/buildSrc/settings.gradle.kts.template"), to("buildSrc/settings.gradle.kts"));
  }

  private File scriptPluginForProfile(BuildProfileId buildProfileId) {
    return projectFolder.filePath(BUILD_GRADLE_PROFILE_PATH_TEMPLATE.formatted(buildProfileId.value())).toFile();
  }

  private void addProfileActivation(AddJavaBuildProfile command) {
    MandatoryReplacer replacer = new MandatoryReplacer(
      new RegexNeedleBeforeReplacer(always(), GRADLE_PROFILE_ACTIVATION_NEEDLE),
      fillProfileActivationTemplate(command)
    );
    fileReplacer.handle(
      projectFolder,
      ContentReplacers.of(new MandatoryFileReplacer(new JHipsterProjectFilePath(BUILD_GRADLE_FILE), replacer)),
      context
    );
  }

  private static String fillProfileActivationTemplate(AddJavaBuildProfile command) {
    Optional<Boolean> isActiveByDefault = command.activation().flatMap(BuildProfileActivation::activeByDefault);

    return (isActiveByDefault.orElse(false) ? PROFILE_DEFAULT_ACTIVATION_CONDITIONAL_TEMPLATE : PROFILE_CONDITIONAL_TEMPLATE).formatted(
        command.buildProfileId(),
        command.buildProfileId()
      );
  }

  private void addScriptPluginForProfile(BuildProfileId buildProfileId) {
    addFileToProject(
      from("buildtool/gradle/buildSrc/src/main/kotlin/profile.gradle.kts.template"),
      to(BUILD_GRADLE_PROFILE_PATH_TEMPLATE.formatted(buildProfileId))
    );
  }

  private void addFileToProject(JHipsterSource source, JHipsterDestination destination) {
    if (projectFolder.fileExists(destination.get())) {
      return;
    }
    files.create(
      projectFolder,
      new JHipsterTemplatedFiles(
        List.of(
          JHipsterTemplatedFile.builder()
            .file(new JHipsterModuleFile(new JHipsterFileContent(source), destination, false))
            .context(context)
            .build()
        )
      )
    );
  }

  @Override
  public void handle(AddGradlePlugin command) {
    Assert.notNull(COMMAND, command);

    switch (command.plugin()) {
      case GradleCorePlugin plugin -> declarePlugin(plugin.id().get(), command.buildProfile());
      case GradleCommunityPlugin plugin -> {
        declarePlugin(
          "alias(libs.plugins.%s)".formatted(applyVersionCatalogReferenceConvention(pluginAlias(plugin))),
          command.buildProfile()
        );
        versionsCatalog.addPlugin(plugin);
      }
      case GradleCommunityProfilePlugin plugin -> {
        declarePlugin(
          """
          id("%s")\
          """.formatted(plugin.id().get()),
          command.buildProfile()
        );
        versionsCatalog.addLibrary(dependencyFrom(plugin));
        addDependencyToBuildGradle(dependencyFrom(plugin), projectFolder.filePath(PLUGIN_BUILD_GRADLE_FILE), false);
      }
    }
    command.plugin().imports().forEach(pluginImport -> addPluginImport(pluginImport, command.buildProfile()));
    command.plugin().configuration().ifPresent(pluginConfiguration -> addPluginConfiguration(pluginConfiguration, command.buildProfile()));
    command.toolVersion().ifPresent(version -> handle(new SetVersion(version)));
    command.pluginVersion().ifPresent(version -> handle(new SetVersion(version)));
  }

  private void declarePlugin(String pluginDeclaration, Optional<BuildProfileId> buildProfile) {
    MandatoryReplacer replacer = new MandatoryReplacer(
      new RegexNeedleBeforeReplacer(
        (contentBeforeReplacement, newText) -> !contentBeforeReplacement.contains(newText),
        GRADLE_PLUGIN_NEEDLE
      ),
      indentation.times(1) + pluginDeclaration
    );
    fileReplacer.handle(
      projectFolder,
      ContentReplacers.of(new MandatoryFileReplacer(projectFolderRelativePathFrom(buildGradleFile(buildProfile)), replacer)),
      context
    );
  }

  private void addPluginImport(BuildGradleImport gradleImport, Optional<BuildProfileId> buildProfile) {
    MandatoryReplacer replacer = new MandatoryReplacer(
      new RegexNeedleBeforeReplacer(
        (contentBeforeReplacement, newText) -> !contentBeforeReplacement.contains(newText),
        GRADLE_IMPORT_NEEDLE
      ),
      "import %s".formatted(gradleImport.get())
    );
    fileReplacer.handle(
      projectFolder,
      ContentReplacers.of(new MandatoryFileReplacer(projectFolderRelativePathFrom(buildGradleFile(buildProfile)), replacer)),
      context
    );
  }

  private void addPluginConfiguration(GradlePluginConfiguration pluginConfiguration, Optional<BuildProfileId> buildProfile) {
    MandatoryReplacer replacer = new MandatoryReplacer(
      new RegexNeedleBeforeReplacer(
        (contentBeforeReplacement, newText) -> !contentBeforeReplacement.contains(newText),
        GRADLE_PLUGIN_PROJECT_EXTENSION_CONFIGURATION_NEEDLE
      ),
      LINE_BREAK + pluginConfiguration.get()
    );
    fileReplacer.handle(
      projectFolder,
      ContentReplacers.of(new MandatoryFileReplacer(projectFolderRelativePathFrom(buildGradleFile(buildProfile)), replacer)),
      context
    );
  }

  private JavaDependency dependencyFrom(GradleCommunityProfilePlugin plugin) {
    var builder = JavaDependency.builder().groupId(plugin.dependency().groupId()).artifactId(plugin.dependency().artifactId());
    plugin.versionSlug().ifPresent(builder::versionSlug);
    return builder.build();
  }

  @Override
  public void handle(AddGradleConfiguration command) {
    MandatoryReplacer replacer = new MandatoryReplacer(
      new RegexNeedleBeforeReplacer(
        (contentBeforeReplacement, newText) -> !contentBeforeReplacement.contains(newText),
        GRADLE_FREE_CONFIGURATION_BLOCKS_NEEDLE
      ),
      LINE_BREAK + command.get()
    );
    fileReplacer.handle(
      projectFolder,
      ContentReplacers.of(new MandatoryFileReplacer(new JHipsterProjectFilePath(BUILD_GRADLE_FILE), replacer)),
      context
    );
  }

  @Override
  public void handle(AddGradleTasksTestInstruction command) {
    MandatoryReplacer replacer = new MandatoryReplacer(
      new RegexNeedleBeforeReplacer(
        (contentBeforeReplacement, newText) -> !contentBeforeReplacement.contains(newText),
        GRADLE_TASKS_TEST_NEEDLE
      ),
      indentation.times(1) + command.get()
    );
    fileReplacer.handle(
      projectFolder,
      ContentReplacers.of(new MandatoryFileReplacer(new JHipsterProjectFilePath(BUILD_GRADLE_FILE), replacer)),
      context
    );
  }
}
