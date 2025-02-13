package tech.jhipster.lite.module.domain.gradleplugin;

public sealed interface GradleProfilePlugin extends GradlePlugin permits GradleCorePlugin, GradleCommunityProfilePlugin {}
