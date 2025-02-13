package tech.jhipster.lite.module.domain.javabuild;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.module.domain.javabuild.command.AddMavenBuildExtension;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommand;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;
import tech.jhipster.lite.module.domain.javabuild.command.SetVersion;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesVersions;
import tech.jhipster.lite.shared.error.domain.Assert;

public class JHipsterModuleMavenBuildExtensions {

  private final Collection<MavenBuildExtension> buildExtensions;

  public JHipsterModuleMavenBuildExtensions(JHipsterModuleMavenBuildExtensionsBuilder builder) {
    this.buildExtensions = builder.buildExtensions;
  }

  public JavaBuildCommands buildChanges(JavaDependenciesVersions versions) {
    Assert.notNull("versions", versions);

    List<JavaBuildCommand> addBuildExtensions =
      this.buildExtensions.stream().flatMap(toCommands(versions, AddMavenBuildExtension::new)).toList();

    return new JavaBuildCommands(addBuildExtensions);
  }

  private static Function<MavenBuildExtension, Stream<JavaBuildCommand>> toCommands(
    JavaDependenciesVersions versions,
    Function<MavenBuildExtension, JavaBuildCommand> addCommandFactory
  ) {
    return extension -> {
      JavaBuildCommand addBuildExtensionCommand = addCommandFactory.apply(extension);

      return extension
        .versionSlug()
        .map(version -> Stream.of(new SetVersion(versions.get(version)), addBuildExtensionCommand))
        .orElseGet(() -> Stream.of(addBuildExtensionCommand));
    };
  }

  public static JHipsterModuleMavenBuildExtensionsBuilder builder(JHipsterModuleBuilder module) {
    return new JHipsterModuleMavenBuildExtensionsBuilder(module);
  }

  public static final class JHipsterModuleMavenBuildExtensionsBuilder {

    private final JHipsterModuleBuilder module;
    private final Collection<MavenBuildExtension> buildExtensions = new ArrayList<>();

    private JHipsterModuleMavenBuildExtensionsBuilder(JHipsterModuleBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
    }

    public JHipsterModuleMavenBuildExtensionsBuilder addExtension(MavenBuildExtension buildExtension) {
      Assert.notNull("buildExtension", buildExtension);

      buildExtensions.add(buildExtension);

      return this;
    }

    public JHipsterModuleMavenBuildExtensionsBuilder addExtension(GroupId groupId, ArtifactId artifactId, VersionSlug versionSlug) {
      MavenBuildExtension buildExtension = MavenBuildExtension.builder()
        .groupId(groupId)
        .artifactId(artifactId)
        .versionSlug(versionSlug)
        .build();

      return addExtension(buildExtension);
    }

    public JHipsterModuleBuilder and() {
      return module;
    }

    public JHipsterModuleMavenBuildExtensions build() {
      return new JHipsterModuleMavenBuildExtensions(this);
    }
  }
}
