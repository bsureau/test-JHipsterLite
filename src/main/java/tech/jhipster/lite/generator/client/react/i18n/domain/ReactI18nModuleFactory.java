package tech.jhipster.lite.generator.client.react.i18n.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.LINE_BREAK;
import static tech.jhipster.lite.module.domain.JHipsterModule.append;
import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.lineAfterText;
import static tech.jhipster.lite.module.domain.JHipsterModule.lineBeforeText;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.packageName;
import static tech.jhipster.lite.module.domain.JHipsterModule.path;
import static tech.jhipster.lite.module.domain.JHipsterModule.text;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;
import static tech.jhipster.lite.module.domain.npm.JHLiteNpmVersionSource.COMMON;
import static tech.jhipster.lite.module.domain.npm.JHLiteNpmVersionSource.REACT;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class ReactI18nModuleFactory {

  private static final JHipsterSource APP_SOURCE = from("client/common/i18n");
  private static final JHipsterSource HOME_CONTEXT_SOURCE = from("client/common/i18n/app");
  private static final JHipsterSource ASSETS_SOURCE = from("client/common/i18n/app/locales");

  private static final String INDEX = "src/main/webapp/app/";
  private static final String INDEX_TEST = "src/test/";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .packageJson()
      .addDependency(packageName("i18next"), COMMON)
      .addDependency(packageName("i18next-browser-languagedetector"), COMMON)
      .addDependency(packageName("i18next-http-backend"), COMMON)
      .addDependency(packageName("react-i18next"), REACT)
      .and()
      .files()
      .batch(APP_SOURCE, to(INDEX))
        .addFile("i18n.ts")
        .addFile("Translations.ts")
        .and()
      .batch(HOME_CONTEXT_SOURCE, to(INDEX + "home/"))
        .addFile("HomeTranslations.ts")
        .and()
      .batch(ASSETS_SOURCE, to(INDEX + "home/locales/"))
        .addFile("en.ts")
        .addFile("fr.ts")
        .and()
      .batch(APP_SOURCE, to(INDEX_TEST + "webapp/unit"))
        .addFile("i18n.spec.ts")
        .and()
      .and()
      .mandatoryReplacements()
        .in(path(INDEX + "i18n.ts"))
          .add(lineAfterText("import LanguageDetector from 'i18next-browser-languagedetector';"), "import { initReactI18next } from 'react-i18next';")
          .add(text(".use(LanguageDetector)"), ".use(initReactI18next).use(LanguageDetector)")
        .and()
        .in(path(INDEX + "home/infrastructure/primary/HomePage.tsx"))
          .add(lineBeforeText("function HomePage"), "import { useTranslation } from 'react-i18next';" + LINE_BREAK)
          .add(lineBeforeText("return ("), properties.indentation().times(1) + "const { t } = useTranslation();" + LINE_BREAK)
          .add(lineAfterText("</h1>"), LINE_BREAK +
            properties.indentation().times(4) + "<p>{t('home.translationEnabled')}</p>")
          .and()
        .in(path(INDEX + "index.tsx"))
          .add(lineBeforeText("const container = document.getElementById('root');"), "import './i18n';" + LINE_BREAK)
          .and()
        .in(path(INDEX_TEST + "webapp/unit/home/infrastructure/primary/HomePage.spec.tsx"))
          .add(append(), LINE_BREAK + """
           describe('Home I18next', () => {
             it('renders with translation', () => {
               vi.mock('react-i18next', () => ({
                 useTranslation: () => {
                   return {
                     t: vi.fn().mockImplementation(() => 'Internationalization enabled'),
                   };
                 },
               }));
               const { getAllByText } = render(<HomePage />);
               const title = getAllByText('Internationalization enabled');
               expect(title).toBeTruthy();
             });
           });""" )
        .and()
      .and()
      .build();
    //@formatter:off
  }
}
