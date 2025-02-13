package tech.jhipster.lite.module.domain.resource;

import tech.jhipster.lite.module.domain.JHipsterModuleSlug;
import tech.jhipster.lite.shared.error.domain.GeneratorException;

class UnknownSlugException extends GeneratorException {

  public UnknownSlugException(JHipsterModuleSlug slug) {
    super(internalServerError(ResourceErrorKey.UNKNOWN_SLUG).message(buildMessage(slug)).addParameter("slug", slug.get()));
  }

  private static String buildMessage(JHipsterModuleSlug slug) {
    return "Module %s does not exist".formatted(slug.get());
  }
}
