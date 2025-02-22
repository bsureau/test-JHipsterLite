Feature: NestJs Module

  Scenario: Should apply my module
    When I apply "nestjs" module to default project
      | packageName | tech.jhipster.chips |
    Then I should have files in "src/main/java/tech/jhipster/chips/nestjs"
      | dummy.java |
