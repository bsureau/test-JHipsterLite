Feature: Liquibase module

  Scenario: Should apply liquibase module
    When I apply "liquibase" module to default project with maven file
      | packageName | tech.jhipster.chips |
    Then I should have files in "src/main/resources/config/liquibase"
      | master.xml |

  Scenario: Should apply liquibase-async module
    When I apply "liquibase-async" module to default project with maven file
      | packageName | tech.jhipster.chips |
    Then I should have files in "src/main/java/tech/jhipster/chips/wire/liquibase/infrastructure/secondary"
      | AsyncSpringLiquibase.java |

  Scenario: Should apply liquibase-linter module
    When I apply "liquibase-linter" module to default project with maven file
      | packageName | tech.jhipster.chips |
    Then I should have "<artifactId>liquibase-linter-maven-plugin</artifactId>" in "pom.xml"
    Then I should have files in "src/test/resources/"
      | liquibase-linter.jsonc |
