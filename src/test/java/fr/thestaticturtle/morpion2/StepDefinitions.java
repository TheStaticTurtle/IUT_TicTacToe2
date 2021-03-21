package fr.thestaticturtle.morpion2;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.*;

import static org.junit.Assert.*;

public class StepDefinitions {
	Board tictactoe;
	Player ai;

	@Given("a board like this:")
	public void a_board_like_this(io.cucumber.datatable.DataTable dataTable) {
		System.out.println("Test was given this board: "+dataTable.cells().toString());
		tictactoe = new Board(dataTable.cells(), Who.HumanA, Who.AI);
		ai = new AIPlayer(tictactoe);
	}

	@When("player O plays")
	public void player_o_plays() {
		//tictactoe.display();
		ai.play();
	}

	@Then("O should place at row {int}, col {int}")
	public void o_should_place_at_row_col(Integer defX, Integer defY) {
		//tictactoe.display();
		assertNotNull(tictactoe.getAt(new Point(defY-1,defX-1)));
		assertTrue(tictactoe.getAt(new Point(defY-1,defX-1)).equals(Who.AI));
	}
}
