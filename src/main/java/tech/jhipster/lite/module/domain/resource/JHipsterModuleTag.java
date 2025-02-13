package tech.jhipster.lite.module.domain.resource;

import java.util.regex.Pattern;
import tech.jhipster.lite.shared.error.domain.Assert;

public record JHipsterModuleTag(String tag) {
  private static final Pattern TAG_FORMAT = Pattern.compile("^[a-z0-9-]+$");

  public JHipsterModuleTag {
    Assert.field("tag", tag).notNull().maxLength(15).matchesPatternOrThrow(TAG_FORMAT, () -> new InvalidJHipsterModuleTagException(tag));
  }

  public String get() {
    return tag();
  }

  @Override
  public String toString() {
    return tag();
  }
}
