Feature:
  Scenario:
    Given a board like this:
      | X | O |   |
      |   | O |   |
      | X |   | X |
    When player O plays
    Then O should place at row 3, col 2
  Scenario:
    Given a board like this:
      | O | X | X |
      |   | X | X |
      |   | O | O |
    When player O plays
    Then O should place at row 3, col 1
  Scenario:
    Given a board like this:
      | O |   | X |
      | X |   |   |
      |   | X | O |
    When player O plays
    Then O should place at row 2, col 2
  Scenario:
    Given a board like this:
      | O |   | X |
      | O |   | O |
      | X |   | X |
    When player O plays
    Then O should place at row 2, col 2