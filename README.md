# IUT_TicTacToe2

Simple tictactoe game that can be played in a terminal. Done as an assignment for my class. The goal is to use the gherkin modeling language to test the AI (minimax) and ensure that it perform as intended.
Tests are given in a format like this one:
```gherkin
Feature:
  Scenario:
    Given a board like this:
    | X | X |   |
    | O |   |   |
    |   |   |   |
    When player O plays
    Then O should place at row 1, col 3
```
And the Step definition interprets it and fails the test if an error is produced

# Instructions

## Settings
In the class `fr.thestaticturtle.morpion2.Morpion2` you can select the game mode, User vs User, User vs AI, AI vs AI by commenting the proper statements (variable of type Player)

## Build and launch the app
```mvn exec:java``` Can be used to start the project directly or `mvn test` can be used to run the cucumber tests.

Ansi color can be enabled by setting the ENABLE_ANSI environement variable to 1 (`ENABLE_ANSI=1`). Otherwise it will display a plain ascii version (see screenshots).

# Screenshots
![Ansi version](https://data.thestaticturtle.fr/ShareX/2021/03/05/2021-03-05_18-10-19.gif)
![Ascii version](https://data.thestaticturtle.fr/ShareX/2021/03/05/idea64_2021-03-05_18-12-50.png)