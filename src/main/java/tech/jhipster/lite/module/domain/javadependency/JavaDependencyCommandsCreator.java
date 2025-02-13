package tech.jhipster.lite.module.domain.javadependency;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommand;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.shared.error.domain.Assert;

abstract sealed class JavaDependencyCommandsCreator permits DirectJavaDependency, JavaDependencyManagement {

  private final JavaDependency dependency;

  JavaDependencyCommandsCreator(JavaDependency dependency) {
    Assert.notNull("dependency", dependency);

    this.dependency = dependency;
  }

  JavaBuildCommands changeCommands(
    JavaDependenciesVersions currentVersions,
    ProjectJavaDependencies projectDependencies,
    Optional<BuildProfileId> buildProfile
  ) {
    Assert.notNull("currentVersion", currentVersions);
    Assert.notNull("projectDependencies", projectDependencies);

    Collection<JavaBuildCommand> dependencyCommands = dependencyCommands(projectDependencies, buildProfile);
    Collection<JavaBuildCommand> versionCommands = versionCommands(currentVersions, projectDependencies, buildProfile);

    return new JavaBuildCommands(Stream.of(dependencyCommands, versionCommands).flatMap(Collection::stream).toList());
  }

  protected JavaDependency dependency() {
    return dependency;
  }

  protected abstract Collection<JavaBuildCommand> dependencyCommands(
    ProjectJavaDependencies projectDependencies,
    Optional<BuildProfileId> buildProfile
  );

  protected abstract Collection<JavaBuildCommand> versionCommands(
    JavaDependenciesVersions currentVersions,
    ProjectJavaDependencies projectDependencies,
    Optional<BuildProfileId> buildProfile
  );
}
