package tech.jhipster.lite.generator.server.javatool.approvaltesting.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.artifactId;
import static tech.jhipster.lite.module.domain.JHipsterModule.documentationTitle;
import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.groupId;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcTestJava;
import static tech.jhipster.lite.module.domain.JHipsterModule.versionSlug;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class ApprovalTestingModuleFactory {

  private static final JHipsterSource SOURCE = from("server/javatool/approvaltesting");

  public JHipsterModule build(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String packagePath = properties.packagePath();
    JHipsterDestination testDestination = toSrcTestJava().append(packagePath);

    //@formatter:off
    return moduleBuilder(properties)
      .documentation(documentationTitle("Approval Testing"), SOURCE.append("approval-testing.md"))
      .javaDependencies()
        .addTestDependency(groupId("com.approvaltests"), artifactId("approvaltests"), versionSlug("approvaltests"))
        .and()
      .files()
        .add(SOURCE.append("test").template("PackageSettings.java"), testDestination.append("PackageSettings.java"))
        .and()
      .gitIgnore()
        .comment("Approval Testing")
        .pattern("src/test/resources/**/*.received.txt")
        .and()
      .build();
    //@formatter:on
  }
}
