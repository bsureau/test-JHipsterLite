package tech.jhipster.lite.module.infrastructure.secondary.npm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.jhipster.lite.module.domain.npm.NpmLazyInstaller;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;
import tech.jhipster.lite.shared.npmdetector.infrastructure.secondary.NpmInstallationReader;
import tech.jhipster.lite.shared.npmdetector.infrastructure.secondary.NpmInstallationType;

/**
 * Launches npm install if the npm command is detected and if an existing package-lock.json is present.
 */
@Service
@ExcludeFromGeneratedCodeCoverage(reason = "Hard to test, requires npm installed")
class FileSystemNpmLazyInstaller implements NpmLazyInstaller {

  private static final Logger log = LoggerFactory.getLogger(FileSystemNpmLazyInstaller.class);
  private final NpmInstallationReader npmInstallationReader = new NpmInstallationReader();

  @Override
  public void runInstallIn(JHipsterProjectFolder folder) {
    Assert.notNull("folder", folder);

    if (!folder.fileExists("package-lock.json")) {
      log.info("No package-lock.json found, npm install skipped");
      return;
    }

    NpmInstallationType npmInstallationType = npmInstallationReader.get();
    switch (npmInstallationType) {
      case UNIX:
        execute(folder, "npm", "install");
        break;
      case WINDOWS:
        execute(folder, "npm.cmd", "install");
        break;
      case NONE:
        log.info("No npm installed, can't install npm dependencies");
        break;
    }
  }

  private void execute(JHipsterProjectFolder path, String... commands) {
    try {
      Process process = new ProcessBuilder(commands).directory(folderFile(path)).start();

      if (failedExecution(process)) {
        throw new NpmInstallException("Error during installation of npm dependencies, process failed");
      }

      traceProcess(String.join(" ", commands), process);
    } catch (IOException e) {
      throw new NpmInstallException("Error during installation of npm dependencies: " + e.getMessage(), e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();

      throw new NpmInstallException("Error during installation of npm dependencies: " + e.getMessage(), e);
    }
  }

  private File folderFile(JHipsterProjectFolder path) {
    return new File(path.get());
  }

  private boolean failedExecution(Process process) throws InterruptedException {
    return !process.waitFor(1, TimeUnit.MINUTES);
  }

  private void traceProcess(String command, Process process) throws IOException {
    if (log.isTraceEnabled()) {
      log.trace("{}: {}", command, read(process.getInputStream()));
    }

    String errors = read(process.getErrorStream());

    if (StringUtils.isNotBlank(errors)) {
      log.error("Error during {}: {}", command, errors);
    }
  }

  private String read(InputStream stream) throws IOException {
    return new String(stream.readAllBytes(), StandardCharsets.UTF_8).intern();
  }
}
