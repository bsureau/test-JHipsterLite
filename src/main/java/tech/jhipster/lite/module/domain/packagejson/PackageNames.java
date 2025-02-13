package tech.jhipster.lite.module.domain.packagejson;

import java.util.Collection;
import java.util.stream.Stream;
import tech.jhipster.lite.shared.collection.domain.JHipsterCollections;

public record PackageNames(Collection<PackageName> packageNames) {
  public PackageNames(Collection<PackageName> packageNames) {
    this.packageNames = JHipsterCollections.immutable(packageNames);
  }

  public boolean isEmpty() {
    return packageNames().isEmpty();
  }

  public Stream<PackageName> stream() {
    return packageNames().stream();
  }
}
