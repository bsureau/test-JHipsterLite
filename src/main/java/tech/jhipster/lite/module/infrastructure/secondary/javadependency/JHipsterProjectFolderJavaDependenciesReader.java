package tech.jhipster.lite.module.infrastructure.secondary.javadependency;

import tech.jhipster.lite.module.domain.javadependency.ProjectJavaDependencies;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

/**
 * <p>
 * Read java dependencies in existing JHipster project folder.
 * </p>
 */
public interface JHipsterProjectFolderJavaDependenciesReader {
  ProjectJavaDependencies get(JHipsterProjectFolder folder);
}
