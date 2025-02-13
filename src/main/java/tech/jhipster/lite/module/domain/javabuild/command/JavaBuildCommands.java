package tech.jhipster.lite.module.domain.javabuild.command;

import static tech.jhipster.lite.shared.collection.domain.JHipsterCollections.*;

import java.util.Collection;
import java.util.List;
import tech.jhipster.lite.shared.error.domain.Assert;

public record JavaBuildCommands(Collection<? extends JavaBuildCommand> commands) {
  public static final JavaBuildCommands EMPTY = new JavaBuildCommands(List.of());

  public JavaBuildCommands(Collection<? extends JavaBuildCommand> commands) {
    this.commands = immutable(commands);
  }

  public JavaBuildCommands merge(JavaBuildCommands other) {
    Assert.notNull("other", other);

    return new JavaBuildCommands(concat(commands(), other.commands()));
  }

  public boolean isEmpty() {
    return get().isEmpty();
  }

  @SuppressWarnings("unchecked")
  public Collection<JavaBuildCommand> get() {
    return (Collection<JavaBuildCommand>) commands();
  }
}
