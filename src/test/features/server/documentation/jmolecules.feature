Feature: jMolecules

  Scenario: Should apply jMolecules module
    When I apply "jmolecules" module to default project with maven file
      | packageName | tech.jhipster.chips |
    Then I should have "<artifactId>jmolecules-bom</artifactId>" in "pom.xml"
