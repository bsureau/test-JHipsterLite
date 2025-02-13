package tech.jhipster.lite.module.domain.replacement;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.module.domain.replacement.ReplacementCondition.always;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class EndOfFileReplacerTest {

  @Test
  void shouldMatchMatchingRegex() {
    EndOfFileReplacer replacer = new EndOfFileReplacer(always());

    assertThat(replacer.notMatchIn("content")).isFalse();
  }

  @Test
  void shouldReplaceLineEnd() {
    EndOfFileReplacer replacer = new EndOfFileReplacer(always());

    String updatedContent = replacer
      .replacement()
      .apply(
        """
        <root>
        </root>
        """,
        "<element />"
      );

    assertThat(updatedContent).isEqualTo(
      """
      <root>
      </root>
      <element />
      """
    );
  }

  @Test
  void shouldGetPatternAsSearchMatcher() {
    EndOfFileReplacer replacer = new EndOfFileReplacer(always());

    assertThat(replacer.searchMatcher()).isEqualTo("\\z");
  }
}
