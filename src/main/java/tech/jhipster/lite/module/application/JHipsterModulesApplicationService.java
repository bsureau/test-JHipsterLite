package tech.jhipster.lite.module.application;

import java.util.Collection;
import org.springframework.stereotype.Service;
import tech.jhipster.lite.module.domain.*;
import tech.jhipster.lite.module.domain.git.GitRepository;
import tech.jhipster.lite.module.domain.javabuild.ProjectJavaBuildToolRepository;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesVersionsRepository;
import tech.jhipster.lite.module.domain.javadependency.ProjectJavaDependenciesRepository;
import tech.jhipster.lite.module.domain.landscape.JHipsterLandscape;
import tech.jhipster.lite.module.domain.preset.Preset;
import tech.jhipster.lite.module.domain.resource.JHipsterModulesResources;

@Service
public class JHipsterModulesApplicationService {

  private final JHipsterModuleEvents events;
  private final JHipsterModulesRepository modules;
  private final JHipsterModulesApplyer applyer;
  private final JHipsterPresetRepository preset;

  public JHipsterModulesApplicationService(
    JHipsterModuleEvents events,
    JHipsterModulesRepository modules,
    JavaDependenciesVersionsRepository currentVersions,
    ProjectJavaDependenciesRepository projectDependencies,
    ProjectJavaBuildToolRepository javaBuildTools,
    GitRepository git,
    GeneratedProjectRepository generatedProject,
    JHipsterPresetRepository preset
  ) {
    this.events = events;
    this.modules = modules;
    this.preset = preset;

    applyer = new JHipsterModulesApplyer(modules, currentVersions, projectDependencies, javaBuildTools, git, generatedProject);
  }

  public void apply(JHipsterModulesToApply modulesToApply) {
    Collection<JHipsterModuleApplied> modulesApplied = applyer.apply(modulesToApply);

    modulesApplied.forEach(events::dispatch);
  }

  public void apply(JHipsterModuleToApply moduleToApply) {
    JHipsterModuleApplied moduleApplied = applyer.apply(moduleToApply);

    events.dispatch(moduleApplied);
  }

  public JHipsterModulesResources resources() {
    return modules.resources();
  }

  public JHipsterLandscape landscape() {
    return modules.landscape();
  }

  public Collection<Preset> getPresets() {
    return preset.getPresets();
  }
}
