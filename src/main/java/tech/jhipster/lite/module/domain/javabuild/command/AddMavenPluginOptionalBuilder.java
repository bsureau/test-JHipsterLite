package tech.jhipster.lite.module.domain.javabuild.command;

import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyVersion;

public interface AddMavenPluginOptionalBuilder<T extends AddMavenPlugin> {
  AddMavenPluginOptionalBuilder<T> pluginVersion(JavaDependencyVersion version);

  AddMavenPluginOptionalBuilder<T> buildProfile(BuildProfileId buildProfile);

  AddMavenPluginOptionalBuilder<T> addDependencyVersion(JavaDependencyVersion javaDependencyVersion);

  T build();
}
