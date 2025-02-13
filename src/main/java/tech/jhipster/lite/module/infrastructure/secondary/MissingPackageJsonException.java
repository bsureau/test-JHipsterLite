package tech.jhipster.lite.module.infrastructure.secondary;

import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.shared.error.domain.GeneratorException;

class MissingPackageJsonException extends GeneratorException {

  public MissingPackageJsonException(JHipsterProjectFolder folder) {
    super(badRequest(ModuleSecondaryErrorKey.MISSING_PACKAGE_JSON).message(buildMessage(folder)).addParameter("folder", folder.get()));
  }

  private static String buildMessage(JHipsterProjectFolder folder) {
    return "package.json is missing in %s, can't apply module".formatted(folder.get());
  }
}
