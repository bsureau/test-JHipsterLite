package tech.jhipster.lite.generator.ci.gitlab.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.GITLAB_CI;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.GITLAB_CI_GRADLE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.GITLAB_CI_MAVEN;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.GRADLE_JAVA;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.MAVEN_JAVA;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.ci.gitlab.application.GitLabCiApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class GitLabCiModuleConfiguration {

  private static final String CI_TAG = "ci";
  private static final String GITLAB_TAG = "gitlab";
  private static final String CONTINUOUS_INTEGRATION_GROUP_DOC = "Continuous Integration";

  @Bean
  JHipsterModuleResource gitLabCiMavenModule(GitLabCiApplicationService gitlabCi) {
    return JHipsterModuleResource.builder()
      .slug(GITLAB_CI_MAVEN)
      .withoutProperties()
      .apiDoc(CONTINUOUS_INTEGRATION_GROUP_DOC, "Add GitLab CI for Maven Build")
      .organization(JHipsterModuleOrganization.builder().feature(GITLAB_CI).addDependency(MAVEN_JAVA).build())
      .tags(CI_TAG, GITLAB_TAG)
      .factory(gitlabCi::buildGitLabCiMavenModule);
  }

  @Bean
  JHipsterModuleResource gitLabCiGradleModule(GitLabCiApplicationService gitlabCi) {
    return JHipsterModuleResource.builder()
      .slug(GITLAB_CI_GRADLE)
      .withoutProperties()
      .apiDoc(CONTINUOUS_INTEGRATION_GROUP_DOC, "Add GitLab CI for Gradle Build")
      .organization(JHipsterModuleOrganization.builder().feature(GITLAB_CI).addDependency(GRADLE_JAVA).build())
      .tags(CI_TAG, GITLAB_TAG)
      .factory(gitlabCi::buildGitLabCiGradleModule);
  }
}
