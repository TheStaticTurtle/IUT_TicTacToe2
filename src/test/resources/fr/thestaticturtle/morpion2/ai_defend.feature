Feature:
  Scenario:
    Given a board like this:
    | X | X |   |
    | O |   |   |
    |   |   |   |
    When player O plays
    Then O should place at row 1, col 3