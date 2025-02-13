package tech.jhipster.lite.module.domain.npm;

import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

/**
 * Run npm install if a previous npm install has already been done.
 */
public interface NpmLazyInstaller {
  void runInstallIn(JHipsterProjectFolder folder);
}
