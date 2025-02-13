package tech.jhipster.lite.generator.server.javatool.jacoco.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.artifactId;
import static tech.jhipster.lite.module.domain.JHipsterModule.gradleCorePlugin;
import static tech.jhipster.lite.module.domain.JHipsterModule.groupId;
import static tech.jhipster.lite.module.domain.JHipsterModule.mavenPlugin;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.pluginExecution;
import static tech.jhipster.lite.module.domain.JHipsterModule.versionSlug;
import static tech.jhipster.lite.module.domain.mavenplugin.MavenBuildPhase.POST_INTEGRATION_TEST;
import static tech.jhipster.lite.module.domain.mavenplugin.MavenBuildPhase.TEST;
import static tech.jhipster.lite.module.domain.mavenplugin.MavenBuildPhase.VERIFY;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.gradleplugin.GradleMainBuildPlugin;
import tech.jhipster.lite.module.domain.javabuild.ArtifactId;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin.MavenPluginOptionalBuilder;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class JacocoModuleFactory {

  private static final String JACOCO = "jacoco";
  private static final GroupId JACOCO_GROUP = groupId("org.jacoco");
  private static final ArtifactId JACOCO_ARTIFACT_ID = artifactId("jacoco-maven-plugin");
  private static final VersionSlug JACOCO_VERSION = versionSlug(JACOCO);

  public JHipsterModule buildJacocoModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .mavenPlugins()
        .plugin(mavenJacocoPlugin())
        .pluginManagement(mavenJacocoPluginManagement())
        .and()
      .gradlePlugins()
        .plugin(gradleJacocoPlugin())
        .and()
      .gradleConfigurations()
        .addTasksTestInstruction(
          """
          finalizedBy("jacocoTestReport")\
          """
        )
        .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildJacocoWithMinCoverageCheckModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .mavenPlugins()
        .plugin(mavenJacocoPlugin())
        .pluginManagement(mavenJacocoWithMinCoverageCheckPluginManagement())
        .and()
      .gradlePlugins()
        .plugin(gradleJacocoWithMinCoverageCheckPlugin())
        .and()
      .gradleConfigurations()
        .addTasksTestInstruction(
          """
          finalizedBy("jacocoTestCoverageVerification")\
          """
        )
        .and()
      .build();
    //@formatter:on
  }

  private static MavenPlugin mavenJacocoPlugin() {
    return mavenPlugin().groupId(JACOCO_GROUP).artifactId(JACOCO_ARTIFACT_ID).build();
  }

  private static MavenPlugin mavenJacocoPluginManagement() {
    return commonMavenJacocoPluginManagement().build();
  }

  private static MavenPlugin mavenJacocoWithMinCoverageCheckPluginManagement() {
    return commonMavenJacocoPluginManagement()
      .addExecution(
        pluginExecution()
          .goals("check")
          .id("check")
          .configuration(
            """
              <dataFile>target/jacoco/allTest.exec</dataFile>
              <rules>
                <rule>
                  <element>CLASS</element>
                  <limits>
                    <limit>
                      <counter>BRANCH</counter>
                      <value>MISSEDCOUNT</value>
                      <maximum>0</maximum>
                    </limit>
                    <limit>
                      <counter>LINE</counter>
                      <value>MISSEDCOUNT</value>
                      <maximum>0</maximum>
                    </limit>
                  </limits>
                </rule>
              </rules>
            """
          )
      )
      .build();
  }

  private static MavenPluginOptionalBuilder commonMavenJacocoPluginManagement() {
    return mavenPlugin()
      .groupId(JACOCO_GROUP)
      .artifactId(JACOCO_ARTIFACT_ID)
      .versionSlug(JACOCO_VERSION)
      .addExecution(pluginExecution().goals("prepare-agent").id("pre-unit-tests"))
      .addExecution(pluginExecution().goals("report").id("post-unit-test").phase(TEST))
      .addExecution(pluginExecution().goals("prepare-agent-integration").id("pre-integration-tests"))
      .addExecution(pluginExecution().goals("report-integration").id("post-integration-tests").phase(POST_INTEGRATION_TEST))
      .addExecution(
        pluginExecution()
          .goals("merge")
          .id("merge")
          .phase(VERIFY)
          .configuration(
            """
              <fileSets>
              <fileSet implementation="org.apache.maven.shared.model.fileset.FileSet">
                <directory>${project.basedir}</directory>
                <includes>
                  <include>**/*.exec</include>
                </includes>
              </fileSet>
            </fileSets>
            <destFile>target/jacoco/allTest.exec</destFile>
            """
          )
      )
      .addExecution(
        pluginExecution()
          .goals("report")
          .id("post-merge-report")
          .phase(VERIFY)
          .configuration(
            """
              <dataFile>target/jacoco/allTest.exec</dataFile>
              <outputDirectory>target/jacoco/</outputDirectory>
            """
          )
      );
  }

  private static GradleMainBuildPlugin gradleJacocoPlugin() {
    return gradleCorePlugin()
      .id(JACOCO)
      .toolVersionSlug(JACOCO)
      .configuration(
        """
        jacoco {
          toolVersion = libs.versions.jacoco.get()
        }

        tasks.jacocoTestReport {
          dependsOn("test", "integrationTest")
          reports {
            xml.required.set(true)
            html.required.set(true)
          }
          executionData.setFrom(fileTree(layout.buildDirectory).include("**/jacoco/test.exec", "**/jacoco/integrationTest.exec"))
        }
        """
      )
      .build();
  }

  private static GradleMainBuildPlugin gradleJacocoWithMinCoverageCheckPlugin() {
    return gradleCorePlugin()
      .id(JACOCO)
      .toolVersionSlug(JACOCO)
      .configuration(
        """
        jacoco {
          toolVersion = libs.versions.jacoco.get()
        }

        tasks.jacocoTestReport {
          dependsOn("test", "integrationTest")
          reports {
            xml.required.set(true)
            html.required.set(true)
          }
          executionData.setFrom(fileTree(layout.buildDirectory).include("**/jacoco/test.exec", "**/jacoco/integrationTest.exec"))
        }

        tasks.jacocoTestCoverageVerification {
          dependsOn("jacocoTestReport")
          violationRules {

              rule {
                  element = "CLASS"

                  limit {
                      counter = "LINE"
                      value = "MISSEDCOUNT"
                      maximum = "0.00".toBigDecimal()
                  }

                  limit {
                      counter = "BRANCH"
                      value = "MISSEDCOUNT"
                      maximum = "0.00".toBigDecimal()
                  }
              }
          }
          executionData.setFrom(fileTree(layout.buildDirectory).include("**/jacoco/test.exec", "**/jacoco/integrationTest.exec"))
        }
        """
      )
      .build();
  }
}
