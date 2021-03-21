Feature:
  Scenario:
    Given a board like this:
      | X | X |   |
      | O |   |   |
      |   |   |   |
    When player O plays
    Then O should place at row 1, col 3
  Scenario:
    Given a board like this:
      | X | X |   |
      | O |   |   |
      |   |   | X |
    When player O plays
    Then O should place at row 1, col 3
  Scenario:
    Given a board like this:
      |   |   |   |
      |   | O |   |
      |   | X | X |
    When player O plays
    Then O should place at row 3, col 1
  Scenario:
    Given a board like this:
      | X | O | X |
      |   | O |   |
      | O | X | X |
    When player O plays
    Then O should place at row 2, col 3
  Scenario:
    Given a board like this:
      | O | X | X |
      |   | X |   |
      |   |   | O |
    When player O plays
    Then O should place at row 3, col 2