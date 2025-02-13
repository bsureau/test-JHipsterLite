package tech.jhipster.lite.module.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class PreCommitCommandsTest {

  @Test
  void shouldHandleSingleCommand() {
    assertThat(PreCommitCommands.of("prettier --write").get()).isEqualTo("'prettier --write'");
  }

  @Test
  void shouldHandleSingleCommandAlreadyQuoted() {
    assertThat(PreCommitCommands.of("'prettier --write'").get()).isEqualTo("'prettier --write'");
  }

  @Test
  void shouldHandleSingleCommandAlreadyArray() {
    assertThat(PreCommitCommands.of("['prettier --write']").get()).isEqualTo("['prettier --write']");
  }

  @Test
  void shouldHandleMultipleCommands() {
    assertThat(PreCommitCommands.of("prettier --write", "eslint --fix").get()).isEqualTo("['prettier --write', 'eslint --fix']");
  }
}
