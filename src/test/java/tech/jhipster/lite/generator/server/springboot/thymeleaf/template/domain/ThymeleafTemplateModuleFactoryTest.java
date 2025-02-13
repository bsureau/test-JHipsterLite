package tech.jhipster.lite.generator.server.springboot.thymeleaf.template.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class ThymeleafTemplateModuleFactoryTest {

  private static final ThymeleafTemplateModuleFactory factory = new ThymeleafTemplateModuleFactory();

  @Test
  void shouldCreateThymeleafTemplateModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .projectBaseName("jhiTest")
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    //@formatter:off
    assertThatModuleWithFiles(module, packageJsonFile())
      .hasFiles("documentation/thymeleaf.md")
      .hasFile("package.json")
        .containing(nodeDependency("@babel/cli"))
        .containing(nodeDependency("autoprefixer"))
        .containing(nodeDependency("browser-sync"))
        .containing(nodeDependency("cssnano"))
        .containing(nodeDependency("mkdirp"))
        .containing(nodeDependency("npm-run-all2"))
        .containing(nodeDependency("onchange"))
        .containing(nodeDependency("path-exists-cli"))
        .containing(nodeDependency("postcss"))
        .containing(nodeDependency("postcss-cli"))
        .containing(nodeDependency("recursive-copy-cli"))
        .containing(
          """
          "build": "npm-run-all --parallel build:*"\
          """
        )
        .containing(
          """
          "build:html": "recursive-copy 'src/main/resources/templates' target/classes/templates -w"\
          """
        )
        .containing(
          """
          "build:css": "mkdirp target/classes/static/css && postcss src/main/resources/static/css/*.css -d target/classes/static/css"\
          """
        )
        .containing(
          """
          "build:js": "path-exists src/main/resources/static/js && (mkdirp target/classes/static/js && babel src/main/resources/static/js/ --out-dir target/classes/static/js/) || echo 'No src/main/resources/static/js directory found.'"\
          """
        )
        .containing(
          """
          "build:svg": "path-exists src/main/resources/static/svg && recursive-copy 'src/main/resources/static/svg' target/classes/static/svg -w -f '**/*.svg' || echo 'No src/main/resources/static/svg directory found.'"\
          """
        )
        .containing(
          """
          "build-prod": "NODE_ENV='production' npm-run-all --parallel build-prod:*"\
          """
        )
        .containing(
          """
          "build-prod:html": "npm run build:html"\
          """
        )
        .containing(
          """
          "build-prod:css": "npm run build:css"\
          """
        )
        .containing(
          """
          "build-prod:js": "path-exists src/main/resources/static/js && (mkdirp target/classes/static/js && babel src/main/resources/static/js/ --minified --out-dir target/classes/static/js/) || echo 'No src/main/resources/static/js directory found.'"\
          """
        )
        .containing(
          """
          build-prod:svg": "npm run build:svg"\
          """
        )
        .containing(
          """
          "watch": "npm-run-all --parallel watch:*"\
          """
        )
        .containing(
          """
          "watch:html": "onchange 'src/main/resources/templates/**/*.html' -- npm run build:html"\
          """
        )
        .containing(
          """
          "watch:css": "onchange 'src/main/resources/static/css/**/*.css' -- npm run build:css"\
          """
        )
        .containing(
          """
          "watch:js": "onchange 'src/main/resources/static/js/**/*.js' -- npm run build:js"\
          """
        )
        .containing(
          """
          "watch:svg": "onchange 'src/main/resources/static/svg/**/*.svg' -- npm run build:svg"\
          """
        )
        .containing(
          """
          "watch:serve": "browser-sync start --proxy localhost:8080 --files 'target/classes/templates' 'target/classes/static'"\
          """
        )
        .and()
      .hasFiles("src/main/resources/templates/index.html")
      .hasFiles("src/main/resources/templates/layout/main.html")
      .hasFiles("src/main/resources/static/css/application.css")
      .hasFiles("postcss.config.js");
    //@formatter:on
  }

  @Test
  void shouldCreateTailwindcssModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .projectBaseName("jhipster")
      .build();

    JHipsterModule module = factory.buildTailwindcssModule(properties);

    //@formatter:off
    assertThatModuleWithFiles(
        module,
        packageJsonFile(),
        appPostcssFile(),
        appCssFile(),
        appIndexFile()
    )
      .hasFile("package.json")
        .containing(nodeDependency("tailwindcss"))
        .containing(
          """
          "watch:html": "onchange 'src/main/resources/templates/**/*.html' -- npm-run-all --serial build:css build:html"\
          """
        )
        .containing(
          """
          "watch:serve": "browser-sync start --no-inject-changes --proxy localhost:8080 --files 'target/classes/templates' 'target/classes/static'"\
          """
        )
        .and()
      .hasFile("postcss.config.js")
        .containing(",require('tailwindcss')")
        .and()
      .hasFile("src/main/resources/static/css/application.css")
        .containing(
          """
          /*! @import */
          @tailwind base;
          @tailwind components;
          @tailwind utilities;
          """
        )
        .and()
      .hasFile("src/main/resources/templates/index.html")
        .containing(
          """
          <main class="flex flex-col min-h-screen w-full justify-center">
            <section
              class="flex flex-col w-full py-16 md:py-24 border-2 border-dashed border-green-500"
            >
              <div
                class="flex flex-col w-full max-w-7xl mx-auto px-4 md:px-8 xl:px-20 gap-8"
              >
                <div class="flex justify-center items-center gap-2">
                  <img
                    class="w-36 h-36"
                    th:src="@{/images/ThymeleafLogo.png}"
                    alt="Thymeleaf Logo"
                  />
                  <h1 class="text-6xl font-bold">Thymeleaf</h1>
                </div>
                <div class="flex justify-center">
                  <div class="text-lg">
                    Welcome to your Spring Boot with Thymeleaf project!
                  </div>
                </div>
              </div>
            </section>
          </main>
          """
        )
        .and()
      .hasFiles("tailwind.config.js")
      .hasFile("src/main/resources/static/images/ThymeleafLogo.png");
    //@formatter:on
  }

  @Test
  void shouldProxyBeUpdatedWhenServerPortPropertyNotDefault() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .projectBaseName("jhipster")
      .put("serverPort", 8081)
      .build();

    JHipsterModule module = factory.buildModule(properties);

    //@formatter:off
    assertThatModuleWithFiles(module, packageJsonFile())
      .hasFile("package.json")
      .containing("browser-sync start --proxy localhost:8081 --files 'target/classes/templates' 'target/classes/static'")
      .notContaining("browser-sync start --proxy localhost:8080 --files 'target/classes/templates' 'target/classes/static'");
    //@formatter:on
  }

  private static ModuleFile appPostcssFile() {
    return new ModuleFile("src/test/resources/projects/thymeleaf/postcss.config.js.template", "postcss.config.js");
  }

  private static ModuleFile appCssFile() {
    return new ModuleFile(
      "src/test/resources/projects/thymeleaf/application.css.template",
      "src/main/resources/static/css/application.css"
    );
  }

  private static ModuleFile appIndexFile() {
    return new ModuleFile("src/test/resources/projects/thymeleaf/index.html.template", "src/main/resources/templates/index.html");
  }
}
