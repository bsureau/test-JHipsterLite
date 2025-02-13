package tech.jhipster.lite.generator.prettier.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.packageName;
import static tech.jhipster.lite.module.domain.JHipsterModule.preCommitCommands;
import static tech.jhipster.lite.module.domain.JHipsterModule.scriptCommand;
import static tech.jhipster.lite.module.domain.JHipsterModule.scriptKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.stagedFilesFilter;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;
import static tech.jhipster.lite.module.domain.npm.JHLiteNpmVersionSource.COMMON;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.npm.NpmLazyInstaller;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class PrettierModuleFactory {

  private static final JHipsterSource SOURCE = from("prettier");
  private static final JHipsterDestination DESTINATION = to(".");

  private final NpmLazyInstaller npmLazyInstaller;

  public PrettierModuleFactory(NpmLazyInstaller npmLazyInstaller) {
    this.npmLazyInstaller = npmLazyInstaller;
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    //@formatter:off
    return moduleBuilder(properties)
      .context()
        .put("dasherizedBaseName", properties.projectBaseName().kebabCase())
        .put("endOfLine", endOfLine(properties))
        .and()
      .files()
        .batch(SOURCE, DESTINATION)
          .addFile(".prettierignore")
          .addTemplate(".prettierrc")
          .and()
        .and()
      .packageJson()
        .addDevDependency(packageName("@prettier/plugin-xml"), COMMON)
        .addDevDependency(packageName("prettier"), COMMON)
        .addDevDependency(packageName("prettier-plugin-gherkin"), COMMON)
        .addDevDependency(packageName("prettier-plugin-java"), COMMON)
        .addDevDependency(packageName("prettier-plugin-organize-imports"), COMMON)
        .addDevDependency(packageName("prettier-plugin-packagejson"), COMMON)
        .addScript(scriptKey("prettier:check"), scriptCommand("prettier --check ."))
        .addScript(scriptKey("prettier:format"), scriptCommand("prettier --write ."))
        .and()
      .postActions()
        .add(context -> npmLazyInstaller.runInstallIn(context.projectFolder()))
        .and()
      .preCommitActions(stagedFilesFilter("*.{md,json*,yml,html,css,scss,java,xml,feature}"), preCommitCommands("['prettier --write']"))
      .build();
    //@formatter:on
  }

  private String endOfLine(JHipsterModuleProperties properties) {
    return properties.getOrDefaultString("endOfLine", "lf");
  }
}
