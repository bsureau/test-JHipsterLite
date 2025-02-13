package tech.jhipster.lite.module.domain.mavenplugin;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.javabuild.command.AddDirectMavenPlugin;
import tech.jhipster.lite.module.domain.javabuild.command.AddMavenPluginManagement;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;
import tech.jhipster.lite.module.domain.javadependency.ProjectJavaDependencies;

@UnitTest
class JHipsterModuleMavenPluginsTest {

  @Test
  void shouldAddNewDependencyManagement() {
    JavaBuildCommands changes = JHipsterModuleMavenPlugins.builder(emptyModuleBuilder())
      .pluginManagement(mavenEnforcerPluginManagement())
      .build()
      .buildChanges(currentJavaDependenciesVersion(), ProjectJavaDependencies.EMPTY);

    assertThat(changes.get())
      .usingRecursiveFieldByFieldElementComparator()
      .containsExactly(
        AddMavenPluginManagement.builder()
          .plugin(mavenEnforcerPluginManagement())
          .pluginVersion(mavenEnforcerVersion())
          .addDependencyVersion(jsonWebTokenVersion())
          .build()
      );
  }

  @Test
  void shouldAddNewDependency() {
    JavaBuildCommands changes = JHipsterModuleMavenPlugins.builder(emptyModuleBuilder())
      .plugin(mavenEnforcerPluginManagement())
      .build()
      .buildChanges(currentJavaDependenciesVersion(), ProjectJavaDependencies.EMPTY);

    assertThat(changes.get())
      .usingRecursiveFieldByFieldElementComparator()
      .containsExactly(
        AddDirectMavenPlugin.builder()
          .plugin(mavenEnforcerPluginManagement())
          .pluginVersion(mavenEnforcerVersion())
          .addDependencyVersion(jsonWebTokenVersion())
          .build()
      );
  }
}
