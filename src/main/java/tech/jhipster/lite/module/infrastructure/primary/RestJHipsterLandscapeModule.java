package tech.jhipster.lite.module.infrastructure.primary;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.util.Collection;
import java.util.List;
import tech.jhipster.lite.module.domain.landscape.JHipsterLandscapeElementType;
import tech.jhipster.lite.module.domain.landscape.JHipsterLandscapeModule;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleRank;

@JsonPropertyOrder({ "type", "slug", "operation", "properties", "dependencies", "rank" })
@Schema(name = "JHipsterLandscapeModule", description = "Module in a landscape")
final class RestJHipsterLandscapeModule implements RestJHipsterLandscapeElement {

  private final String slug;
  private final String operation;
  private final RestJHipsterModulePropertiesDefinition properties;
  private final Collection<RestJHipsterLandscapeDependency> dependencies;
  private final JHipsterModuleRank rank;

  private RestJHipsterLandscapeModule(RestJHipsterLandscapeModuleBuilder builder) {
    slug = builder.slug;
    operation = builder.operation;
    properties = builder.properties;
    dependencies = builder.dependencies;
    rank = builder.rank;
  }

  static RestJHipsterLandscapeModule fromModule(JHipsterLandscapeModule module) {
    return new RestJHipsterLandscapeModuleBuilder()
      .slug(module.slug().get())
      .operation(module.operation().get())
      .properties(RestJHipsterModulePropertiesDefinition.from(module.propertiesDefinition()))
      .rank(module.rank())
      .dependencies(buildDependencies(module))
      .build();
  }

  private static List<RestJHipsterLandscapeDependency> buildDependencies(JHipsterLandscapeModule module) {
    return module
      .dependencies()
      .map(dependencies -> dependencies.stream().map(RestJHipsterLandscapeDependency::from).toList())
      .orElse(null);
  }

  @Override
  @Schema(description = "Type of this landscape element", requiredMode = RequiredMode.REQUIRED)
  public JHipsterLandscapeElementType getType() {
    return JHipsterLandscapeElementType.MODULE;
  }

  @Schema(description = "Unique slug of this module", requiredMode = RequiredMode.REQUIRED)
  public String getSlug() {
    return slug;
  }

  @Schema(description = "Operation done by this module", requiredMode = RequiredMode.REQUIRED)
  public String getOperation() {
    return operation;
  }

  @Schema(description = "Definition of properties for this module", requiredMode = RequiredMode.REQUIRED)
  public RestJHipsterModulePropertiesDefinition getProperties() {
    return properties;
  }

  @Schema(description = "Dependencies of this module")
  public Collection<RestJHipsterLandscapeDependency> getDependencies() {
    return dependencies;
  }

  @Schema(description = "Rank of this module", requiredMode = RequiredMode.REQUIRED)
  public JHipsterModuleRank getRank() {
    return rank;
  }

  private static final class RestJHipsterLandscapeModuleBuilder {

    private String slug;
    private String operation;
    private RestJHipsterModulePropertiesDefinition properties;
    private List<RestJHipsterLandscapeDependency> dependencies;
    private JHipsterModuleRank rank;

    public RestJHipsterLandscapeModuleBuilder slug(String slug) {
      this.slug = slug;

      return this;
    }

    public RestJHipsterLandscapeModuleBuilder operation(String operation) {
      this.operation = operation;

      return this;
    }

    public RestJHipsterLandscapeModuleBuilder properties(RestJHipsterModulePropertiesDefinition properties) {
      this.properties = properties;

      return this;
    }

    public RestJHipsterLandscapeModuleBuilder dependencies(List<RestJHipsterLandscapeDependency> dependencies) {
      this.dependencies = dependencies;

      return this;
    }

    public RestJHipsterLandscapeModuleBuilder rank(JHipsterModuleRank rank) {
      this.rank = rank;

      return this;
    }

    public RestJHipsterLandscapeModule build() {
      return new RestJHipsterLandscapeModule(this);
    }
  }
}
