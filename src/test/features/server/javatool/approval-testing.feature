Feature: Approval testing module

  Scenario: Should apply approval-tests module in maven project
    When I apply "approval-tests" module to default project with maven file
      | packageName | tech.jhipster.chips |
    Then I should have "<artifactId>approvaltests</artifactId>" in "pom.xml"
