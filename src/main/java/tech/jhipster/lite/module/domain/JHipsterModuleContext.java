package tech.jhipster.lite.module.domain;

import java.util.HashMap;
import java.util.Map;
import tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.module.domain.javabuild.JavaBuildTool;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.collection.domain.JHipsterCollections;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModuleContext {

  private final Map<String, Object> context;

  private JHipsterModuleContext(Map<String, Object> context) {
    this.context = JHipsterCollections.immutable(context);
  }

  public static JHipsterModuleContext empty() {
    return new JHipsterModuleContext(new HashMap<>());
  }

  public JHipsterModuleContext withJavaBuildTool(JavaBuildTool javaBuildTool) {
    Map<String, Object> additionalValues = Map.of(JHipsterModuleProperties.PROJECT_BUILD_DIRECTORY, javaBuildTool.buildDirectory().get());
    return new JHipsterModuleContext(JHipsterCollections.concat(context, additionalValues));
  }

  public static JHipsterModuleContextBuilder builder(JHipsterModuleBuilder module) {
    return new JHipsterModuleContextBuilder(module);
  }

  public Map<String, Object> get() {
    return context;
  }

  public static final class JHipsterModuleContextBuilder {

    private final JHipsterModuleBuilder module;
    private final Map<String, Object> context;

    private JHipsterModuleContextBuilder(JHipsterModuleBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
      context = initialContext(module.properties());
    }

    private Map<String, Object> initialContext(JHipsterModuleProperties properties) {
      Map<String, Object> init = new HashMap<>();

      init.put(JHipsterModuleProperties.PROJECT_BASE_NAME_PARAMETER, properties.projectBaseName().get());
      init.put(JHipsterModuleProperties.PROJECT_NAME_PARAMETER, properties.projectName().get());
      init.put(JHipsterModuleProperties.BASE_PACKAGE_PARAMETER, properties.basePackage().get());
      init.put(JHipsterModuleProperties.SERVER_PORT_PARAMETER, properties.serverPort().get());
      init.put(JHipsterModuleProperties.INDENTATION_PARAMETER, properties.indentation().spacesCount());
      init.put(JHipsterModuleProperties.JAVA_VERSION, properties.javaVersion().get());
      init.put(JHipsterModuleProperties.PROJECT_BUILD_DIRECTORY, JavaBuildTool.MAVEN.buildDirectory().get());
      init.put(JHipsterModuleProperties.SPRING_CONFIGURATION_FORMAT, properties.springConfigurationFormat().get());

      return init;
    }

    public JHipsterModuleContextBuilder put(String key, Object value) {
      Assert.notBlank("key", key);
      Assert.notNull("value", value);

      context.put(key, value);

      return this;
    }

    public JHipsterModuleContext build() {
      return new JHipsterModuleContext(this.context);
    }

    public JHipsterModuleBuilder and() {
      return module;
    }
  }
}
