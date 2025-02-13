package tech.jhipster.lite.module.infrastructure.secondary.javadependency;

import java.util.List;
import java.util.regex.Pattern;
import tech.jhipster.lite.module.domain.ProjectFiles;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesVersions;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyVersion;

public class FileSystemMavenDependenciesReader implements JavaDependenciesReader {

  private static final Pattern VERSIONS_PATTERN = Pattern.compile("<([^>]+)\\.version>([^>]+)</");

  private final ProjectFiles files;
  private final String currentVersionsFile;

  public FileSystemMavenDependenciesReader(ProjectFiles files, String currentVersionsFile) {
    this.files = files;
    this.currentVersionsFile = currentVersionsFile;
  }

  @Override
  public JavaDependenciesVersions get() {
    List<JavaDependencyVersion> versions = readVersions(files.readString(currentVersionsFile));

    return new JavaDependenciesVersions(versions);
  }

  private List<JavaDependencyVersion> readVersions(String content) {
    return VERSIONS_PATTERN.matcher(content).results().map(result -> new JavaDependencyVersion(result.group(1), result.group(2))).toList();
  }
}
