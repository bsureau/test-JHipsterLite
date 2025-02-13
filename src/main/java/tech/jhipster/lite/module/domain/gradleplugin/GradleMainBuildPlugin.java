package tech.jhipster.lite.module.domain.gradleplugin;

public sealed interface GradleMainBuildPlugin extends GradlePlugin permits GradleCorePlugin, GradleCommunityPlugin {}
