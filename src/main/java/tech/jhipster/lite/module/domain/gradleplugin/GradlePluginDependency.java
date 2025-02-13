package tech.jhipster.lite.module.domain.gradleplugin;

import tech.jhipster.lite.module.domain.javabuild.ArtifactId;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.shared.error.domain.Assert;

public record GradlePluginDependency(GroupId groupId, ArtifactId artifactId) {
  public GradlePluginDependency {
    Assert.notNull("groupId", groupId);
    Assert.notNull("artifactId", artifactId);
  }
}
