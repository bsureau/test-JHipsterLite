package tech.jhipster.lite.module.domain.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tech.jhipster.lite.UnitTest;

@UnitTest
class JHipsterServerPortTest {

  @Test
  void shouldGetDefaultServerPortFromNullPort() {
    assertThat(new JHipsterServerPort(null).get()).isEqualTo(8080);
  }

  @Test
  void shouldGetServerPortFromPort() {
    assertThat(new JHipsterServerPort(9000).get()).isEqualTo(9000);
  }

  @ParameterizedTest
  @CsvSource(
    {
      "-90000,tech.jhipster.lite.shared.error.domain.NumberValueTooLowException",
      "90000,tech.jhipster.lite.shared.error.domain.NumberValueTooHighException",
    }
  )
  void shouldValidatePortNumbers(int port, Class<Exception> exceptionClass) {
    assertThatExceptionOfType(exceptionClass).isThrownBy(() -> new JHipsterServerPort(port));
  }

  @Test
  void testToStringShowsPortNumber() {
    assertThat(new JHipsterServerPort(9000)).hasToString("9000");
  }
}
