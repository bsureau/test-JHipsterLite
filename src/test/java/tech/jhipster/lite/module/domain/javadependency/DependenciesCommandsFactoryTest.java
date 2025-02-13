package tech.jhipster.lite.module.domain.javadependency;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.localBuildProfile;
import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.springBootDependencyId;
import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.springBootStarterWebDependency;

import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.javabuild.command.AddDirectJavaDependency;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommand;
import tech.jhipster.lite.module.domain.javabuild.command.RemoveDirectJavaDependency;

@UnitTest
class DependenciesCommandsFactoryTest {

  private final DependenciesCommandsFactory commandsFactory = DependenciesCommandsFactory.DIRECT;

  @Nested
  class AddDependency {

    @Test
    void shouldAddDependencyWithoutProfile() {
      JavaBuildCommand buildCommand = commandsFactory.addDependency(springBootStarterWebDependency(), Optional.empty());

      assertThat(buildCommand).isEqualTo(new AddDirectJavaDependency(springBootStarterWebDependency()));
    }

    @Test
    void shouldAddDependencyWithProfile() {
      JavaBuildCommand buildCommand = commandsFactory.addDependency(springBootStarterWebDependency(), Optional.of(localBuildProfile()));

      assertThat(buildCommand).isEqualTo(new AddDirectJavaDependency(springBootStarterWebDependency(), localBuildProfile()));
    }
  }

  @Nested
  class RemoveDependency {

    @Test
    void shouldRemoveDependencyWithoutProfile() {
      JavaBuildCommand buildCommand = commandsFactory.removeDependency(springBootDependencyId(), Optional.empty());

      assertThat(buildCommand).isEqualTo(new RemoveDirectJavaDependency(springBootDependencyId()));
    }

    @Test
    void shouldRemoveDependencyWithProfile() {
      JavaBuildCommand buildCommand = commandsFactory.removeDependency(springBootDependencyId(), Optional.of(localBuildProfile()));

      assertThat(buildCommand).isEqualTo(new RemoveDirectJavaDependency(springBootDependencyId(), localBuildProfile()));
    }
  }
}
