package tech.jhipster.lite.module.domain;

import java.util.regex.Pattern;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public abstract sealed class JHipsterSlug implements Comparable<JHipsterSlug> permits JHipsterModuleSlug, JHipsterFeatureSlug {

  private static final Pattern SLUG_FORMAT = Pattern.compile("^[a-z0-9-]+$");

  private final String slug;

  protected JHipsterSlug(String slug) {
    Assert.field("slug", slug).notBlank().matchesPatternOrThrow(SLUG_FORMAT, () -> new InvalidJHipsterSlugException(slug));

    this.slug = slug;
  }

  public String get() {
    return slug;
  }

  @Override
  public int compareTo(JHipsterSlug other) {
    if (isInit()) {
      return -1;
    }

    if (other.isInit()) {
      return 1;
    }

    return get().compareTo(other.get());
  }

  private boolean isInit() {
    return get().equals("init");
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public int hashCode() {
    return new HashCodeBuilder().append(slug).hashCode();
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof JHipsterSlug other)) {
      return false;
    }

    return new EqualsBuilder().append(slug, other.slug).isEquals();
  }
}
