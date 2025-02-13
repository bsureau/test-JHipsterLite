Feature: Property-based testing module

  Scenario: Should apply jqwik module in maven project
    When I apply "jqwik" module to default project with maven file
      | packageName | tech.jhipster.chips |
    Then I should have "<artifactId>jqwik</artifactId>" in "pom.xml"
