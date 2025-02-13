package tech.jhipster.lite.module.domain.javabuild.command;

import tech.jhipster.lite.module.domain.javadependency.JavaDependency;

public sealed interface AddJavaDependency permits AddDirectJavaDependency, AddJavaDependencyManagement {
  JavaDependency dependency();
}
