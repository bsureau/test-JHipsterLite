package tech.jhipster.lite.module.infrastructure.secondary.javadependency;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;
import tech.jhipster.lite.module.domain.ProjectFiles;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesVersions;

@Order
@Repository
class JHLiteMavenDependenciesReader implements JavaDependenciesReader {

  private static final String CURRENT_VERSIONS_FILE = "/generator/dependencies/pom.xml";

  private final FileSystemMavenDependenciesReader reader;

  public JHLiteMavenDependenciesReader(ProjectFiles files) {
    this.reader = new FileSystemMavenDependenciesReader(files, CURRENT_VERSIONS_FILE);
  }

  @Override
  public JavaDependenciesVersions get() {
    return reader.get();
  }
}
