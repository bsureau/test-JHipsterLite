package tech.jhipster.lite.generator.client.tools.cypress.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.module.domain.npm.JHLiteNpmVersionSource.COMMON;

import java.util.function.Consumer;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class CypressModuleFactory {

  private static final JHipsterSource SOURCE = from("client/common/cypress");

  private static final JHipsterDestination CYPRESS_COMPONENT_TESTS = to("src/test/webapp/component");
  private static final JHipsterDestination CYPRESS_E2E_TESTS = to("src/test/webapp/e2e");

  private static final String HOME = "home";
  private static final String UTILS = "utils";

  public JHipsterModule buildComponentTestsModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return commonCypressModuleBuilder(properties, CYPRESS_COMPONENT_TESTS)
      .packageJson()
        .addDevDependency(packageName("start-server-and-test"), COMMON)
        .addScript(scriptKey("test:component"), scriptCommand("start-server-and-test dev http://localhost:9000 'cypress open --e2e --config-file src/test/webapp/component/cypress-config.ts'"))
        .addScript(
          scriptKey("test:component:headless"),
          scriptCommand("start-server-and-test dev http://localhost:9000 'cypress run --headless --config-file src/test/webapp/component/cypress-config.ts'")
        )
        .and()
      .context()
        .put("cypressTestDirectory", "component")
        .and()
      .apply(patchEslintPluginCypressComponent(properties))
      .build();
    //@formatter:on
  }

  public JHipsterModule buildE2ETestsModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return commonCypressModuleBuilder(properties, CYPRESS_E2E_TESTS)
      .packageJson()
        .addScript(scriptKey("e2e"), scriptCommand("cypress open --e2e --config-file src/test/webapp/e2e/cypress-config.ts"))
        .addScript(scriptKey("e2e:headless"), scriptCommand("cypress run --headless --config-file src/test/webapp/e2e/cypress-config.ts"))
        .and()
      .context()
        .put("cypressTestDirectory", "e2e")
        .and()
      .apply(patchEslintPluginCypressE2E(properties))
      .build();
    //@formatter:on
  }

  private static JHipsterModuleBuilder commonCypressModuleBuilder(
    JHipsterModuleProperties properties,
    JHipsterDestination destinationFolder
  ) {
    //@formatter:off
    return moduleBuilder(properties)
      .packageJson()
        .addDevDependency(packageName("cypress"), COMMON)
        .addDevDependency(packageName("eslint-plugin-cypress"), COMMON)
        .and()
      .files()
        .batch(SOURCE, destinationFolder)
          .addTemplate("cypress-config.ts")
          .addFile("tsconfig.json")
          .and()
        .batch(SOURCE.append(HOME), destinationFolder.append(HOME))
          .addTemplate("Home.spec.ts")
          .and()
        .batch(SOURCE.append(UTILS), destinationFolder.append(UTILS))
          .addFile("Interceptor.ts")
          .addFile("DataSelector.ts")
          .and()
        .and()
      ;
    //@formatter:on
  }

  private Consumer<JHipsterModuleBuilder> patchEslintPluginCypressE2E(JHipsterModuleProperties properties) {
    return patchEslintPluginCypress(properties, "e2e");
  }

  private Consumer<JHipsterModuleBuilder> patchEslintPluginCypressComponent(JHipsterModuleProperties properties) {
    return patchEslintPluginCypress(properties, "component");
  }

  private Consumer<JHipsterModuleBuilder> patchEslintPluginCypress(JHipsterModuleProperties properties, String path) {
    String eslintPluginCypress =
      """
      \t{
        \t\tfiles: ['src/test/webapp/%s/**/*.ts'],
        \t\textends: [...typescript.configs.recommendedTypeChecked, cypress.configs.recommended],
        \t\tlanguageOptions: {
          \t\t\tparserOptions: {
            \t\t\t\tproject: ['src/test/webapp/%s/tsconfig.json'],
          \t\t\t},
        \t\t},
        \t\trules: {
          \t\t\t'@typescript-eslint/no-unsafe-assignment': 'off',
        \t\t},
      \t},\
      """.formatted(path, path).replace("\t", properties.indentation().spaces());
    //@formatter:off
    return moduleBuilder -> moduleBuilder
      .optionalReplacements()
      .in(path("eslint.config.mjs"))
      .add(lineAfterRegex("from 'typescript-eslint'"), "import cypress from 'eslint-plugin-cypress/flat';")
      .add(lineAfterRegex(".configs.recommended,"), eslintPluginCypress)
      .and()
      .and()
      .optionalReplacements()
      .in(path("eslint.config.js"))
      .add(lineAfterRegex("from 'typescript-eslint'"), "import cypress from 'eslint-plugin-cypress/flat';")
      .add(lineAfterRegex(".configs.recommended,"), eslintPluginCypress);
    //@formatter:on
  }
}
