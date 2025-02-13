package tech.jhipster.lite.module.domain.gradleconfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;
import tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradleConfiguration;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradleTasksTestInstruction;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModuleGradleConfigurations {

  private final Collection<GradleConfiguration> configurations;
  private final Collection<GradleTasksTestInstruction> tasksTestInstructions;

  public JHipsterModuleGradleConfigurations(JHipsterModuleGradleConfigurationBuilder builder) {
    Assert.notNull("configurations", builder.configurations);
    Assert.notNull("tasksTestInstructions", builder.tasksTestInstructions);

    this.configurations = builder.configurations;
    this.tasksTestInstructions = builder.tasksTestInstructions;
  }

  public static JHipsterModuleGradleConfigurationBuilder builder(JHipsterModuleBuilder module) {
    return new JHipsterModuleGradleConfigurationBuilder(module);
  }

  public JavaBuildCommands buildChanges() {
    return Stream.of(configurationToAddGradleConfiguration(), tasksTestInstructionsToAddTasksTestInstruction())
      .flatMap(Function.identity())
      .reduce(JavaBuildCommands.EMPTY, JavaBuildCommands::merge);
  }

  private Stream<JavaBuildCommands> configurationToAddGradleConfiguration() {
    return Stream.of(
      new JavaBuildCommands(configurations.stream().map(configuration -> new AddGradleConfiguration(configuration.get())).toList())
    );
  }

  private Stream<JavaBuildCommands> tasksTestInstructionsToAddTasksTestInstruction() {
    return Stream.of(
      new JavaBuildCommands(
        tasksTestInstructions.stream().map(tasksTestInstruction -> new AddGradleTasksTestInstruction(tasksTestInstruction.get())).toList()
      )
    );
  }

  public static final class JHipsterModuleGradleConfigurationBuilder {

    private final JHipsterModuleBuilder module;
    private final Collection<GradleConfiguration> configurations = new ArrayList<>();
    private final Collection<GradleTasksTestInstruction> tasksTestInstructions = new ArrayList<>();

    private JHipsterModuleGradleConfigurationBuilder(JHipsterModuleBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
    }

    public JHipsterModuleGradleConfigurationBuilder addConfiguration(String configuration) {
      configurations.add(new GradleConfiguration(configuration));

      return this;
    }

    public JHipsterModuleGradleConfigurationBuilder addTasksTestInstruction(String instruction) {
      tasksTestInstructions.add(new GradleTasksTestInstruction(instruction));

      return this;
    }

    public JHipsterModuleBuilder and() {
      return module;
    }

    public JHipsterModuleGradleConfigurations build() {
      return new JHipsterModuleGradleConfigurations(this);
    }
  }
}
