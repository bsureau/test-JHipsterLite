package tech.jhipster.lite.module.infrastructure.secondary.npm;

import tech.jhipster.lite.shared.error.domain.ErrorKey;

enum NpmErrorKey implements ErrorKey {
  INSTALL_ERROR("install-error");

  private final String key;

  NpmErrorKey(String key) {
    this.key = key;
  }

  @Override
  public String get() {
    return key;
  }
}
