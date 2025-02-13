package tech.jhipster.lite.generator.server.documentation.jmolecules.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.artifactId;
import static tech.jhipster.lite.module.domain.JHipsterModule.groupId;
import static tech.jhipster.lite.module.domain.JHipsterModule.javaDependency;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.javadependency.JavaDependencyScope.IMPORT;
import static tech.jhipster.lite.module.domain.javadependency.JavaDependencyType.POM;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class JMoleculesModuleFactory {

  private static final GroupId JMOLECULES_GROUP_ID = groupId("org.jmolecules");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
        .addDependencyManagement(jMoleculesBomDependency())
        .addDependency(JMOLECULES_GROUP_ID, artifactId("jmolecules-ddd"))
        .addDependency(JMOLECULES_GROUP_ID, artifactId("jmolecules-hexagonal-architecture"))
        .addDependency(JMOLECULES_GROUP_ID, artifactId("jmolecules-events"))
        .and()
      .build();
    //@formatter:on
  }

  private JavaDependency jMoleculesBomDependency() {
    return javaDependency()
      .groupId(JMOLECULES_GROUP_ID)
      .artifactId("jmolecules-bom")
      .versionSlug("jmolecules-bom")
      .type(POM)
      .scope(IMPORT)
      .build();
  }
}
