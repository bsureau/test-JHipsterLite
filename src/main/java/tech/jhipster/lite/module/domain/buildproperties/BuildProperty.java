package tech.jhipster.lite.module.domain.buildproperties;

import tech.jhipster.lite.shared.error.domain.Assert;

public record BuildProperty(PropertyKey key, PropertyValue value) {
  public BuildProperty {
    Assert.notNull("key", key);
    Assert.notNull("value", value);
  }
}
