Feature: School Service Testing

  Background:
    Given the following schools exist:
      | name          | location_school     | website                | description          |
      | School A      | 40.7128,-74.0060    | http://www.schoolA.com | School A Description |
      | School B      | 34.0522,-118.2437   | http://www.schoolB.com | School B Description |

  Scenario: Get School by Name
    When I request the school with name "School A"
    Then the response should contain the following JSON:
      | name       | location_school     | website                | description          |
      | School A   | 40.7128,-74.0060    | http://www.schoolA.com | School A Description |

  Scenario: Get School by Location
    When I request schools near location "40.7128,-74.0060"
    Then the response should contain the following JSON:
      | name       | location_school     | website                | description          |
      | School A   | 40.7128,-74.0060    | http://www.schoolA.com | School A Description |


  Scenario: Add a New School
    When I add a new school with the following details:
      | name        | location_school     | website                | description          |
      | New School  | 45.0000,-90.0000    | http://www.newschool.com| New School Description |
    Then the response should contain the following JSON:
      | name        | location_school     | website                | description          |
      | New School  | 45.0000,-90.0000    | http://www.newschool.com| New School Description |

  Scenario: Update School Information
    When I update the school with ID 1 with the following details:
      | name        | location_school     | website                | description          |
      | Updated School | 50.0000,-85.0000 | http://www.updatedschool.com| Updated School Description |
    Then the response should contain the following JSON:
      | name           | location_school      | website                     | description               |
      | Updated School | 50.0000,-85.0000    | http://www.updatedschool.com| Updated School Description |

  Scenario: Delete a School
    When I delete the school with ID 2
    Then the response status should be 204
    And the school with ID 2 should not exist
