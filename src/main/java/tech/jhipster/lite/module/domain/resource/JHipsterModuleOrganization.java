package tech.jhipster.lite.module.domain.resource;

import static org.apache.commons.lang3.builder.ToStringStyle.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.apache.commons.lang3.builder.ToStringBuilder;
import tech.jhipster.lite.module.domain.JHipsterFeatureSlug;
import tech.jhipster.lite.module.domain.landscape.JHipsterFeatureDependency;
import tech.jhipster.lite.module.domain.landscape.JHipsterLandscapeDependency;
import tech.jhipster.lite.module.domain.landscape.JHipsterModuleDependency;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug;

public final class JHipsterModuleOrganization {

  public static final JHipsterModuleOrganization STANDALONE = builder().build();
  public static final JHipsterModuleOrganization SPRINGBOOT_DEPENDENCY = builder().addDependency(JHLiteModuleSlug.SPRING_BOOT).build();

  private final Optional<JHipsterFeatureSlug> feature;
  private final Collection<JHipsterLandscapeDependency> dependencies;

  private JHipsterModuleOrganization(JHipsterModuleOrganizationBuilder builder) {
    feature = builder.feature;
    dependencies = builder.dependencies;
  }

  public static JHipsterModuleOrganizationBuilder builder() {
    return new JHipsterModuleOrganizationBuilder();
  }

  public Optional<JHipsterFeatureSlug> feature() {
    return feature;
  }

  public Collection<JHipsterLandscapeDependency> dependencies() {
    return dependencies;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
      .append("feature", feature.map(JHipsterFeatureSlug::get).orElse(""))
      .append("dependencies", dependencies)
      .build();
  }

  public static class JHipsterModuleOrganizationBuilder {

    private final Collection<JHipsterLandscapeDependency> dependencies = new ArrayList<>();
    private Optional<JHipsterFeatureSlug> feature = Optional.empty();

    public JHipsterModuleOrganizationBuilder feature(JHipsterFeatureSlugFactory featureFactory) {
      Assert.notNull("featureFactory", featureFactory);

      this.feature = featureFactory.build();

      return this;
    }

    public JHipsterModuleOrganizationBuilder addDependency(JHipsterModuleSlugFactory module) {
      Assert.notNull("module", module);

      dependencies.add(new JHipsterModuleDependency(module.build()));

      return this;
    }

    public JHipsterModuleOrganizationBuilder addDependency(JHipsterFeatureSlugFactory featureFactory) {
      Assert.notNull("featureFactory", featureFactory);

      featureFactory.build().map(JHipsterFeatureDependency::new).ifPresent(dependencies::add);

      return this;
    }

    public JHipsterModuleOrganization build() {
      return new JHipsterModuleOrganization(this);
    }
  }
}
