Feature:
  Scenario:
  Scenario:
    Given a board like this:
      | O |   | X |
      | X |   | X |
      | O |   | O |
    When player O plays
    Then O should place at row 2, col 2