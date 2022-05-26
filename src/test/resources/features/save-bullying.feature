Feature: Save bullying information
  Scenario: Saving bullying with correct information
    Given a bullyingInformation with description
    And image
    And formURL
    When user send a post to school->bullying
    Then a status 201 must be returned
    And a bullyingInformation must be returned

  Scenario: Saving bullying without formURL
    Given  a bullyingInformation without description
    When user send a post to school->bullying
    Then a status 400 must be returned
    And error message must be returned