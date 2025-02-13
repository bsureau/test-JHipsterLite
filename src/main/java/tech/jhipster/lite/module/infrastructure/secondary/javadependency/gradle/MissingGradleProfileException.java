package tech.jhipster.lite.module.infrastructure.secondary.javadependency.gradle;

import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.shared.error.domain.GeneratorException;

class MissingGradleProfileException extends GeneratorException {

  public MissingGradleProfileException(BuildProfileId profileId) {
    super(
      badRequest(GradleDependencyErrorKey.MISSING_PROFILE).message(
        "Your gradle project does not contain any profile-%s.gradle.kts precompiled script plugin".formatted(profileId)
      )
    );
  }
}
