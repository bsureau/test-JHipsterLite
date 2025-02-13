package tech.jhipster.lite.module.domain.javabuild;

import java.util.Optional;
import tech.jhipster.lite.module.domain.file.JHipsterModuleFiles;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

public interface ProjectJavaBuildToolRepository {
  Optional<JavaBuildTool> detect(JHipsterProjectFolder projectFolder);

  Optional<JavaBuildTool> detect(JHipsterModuleFiles moduleFiles);
}
