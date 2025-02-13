package tech.jhipster.lite.module.infrastructure.secondary.javadependency;

import java.util.Collection;
import java.util.function.Function;
import org.springframework.stereotype.Repository;
import tech.jhipster.lite.module.domain.javadependency.ProjectJavaDependencies;
import tech.jhipster.lite.module.domain.javadependency.ProjectJavaDependenciesRepository;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

@Repository
class JHipsterJavaDependenciesRepository implements ProjectJavaDependenciesRepository {

  private final Function<JHipsterProjectFolder, ProjectJavaDependencies> javaDependencies;

  public JHipsterJavaDependenciesRepository(Collection<JHipsterProjectFolderJavaDependenciesReader> readers) {
    javaDependencies = readJavaDependencies(readers);
  }

  private Function<JHipsterProjectFolder, ProjectJavaDependencies> readJavaDependencies(
    Collection<JHipsterProjectFolderJavaDependenciesReader> readers
  ) {
    return folder ->
      readers.stream().map(reader -> reader.get(folder)).reduce(ProjectJavaDependencies.EMPTY, ProjectJavaDependencies::merge);
  }

  @Override
  public ProjectJavaDependencies get(JHipsterProjectFolder folder) {
    return javaDependencies.apply(folder);
  }
}
