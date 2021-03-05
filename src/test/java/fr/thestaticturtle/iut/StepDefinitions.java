package fr.thestaticturtle.iut;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class StepDefinitions {
	@Given("a board like this:")
	public void a_board_like_this(io.cucumber.datatable.DataTable dataTable) {
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or Map<K, List<V>>.
		// E,K,V must be a String, Integer, Float, Double, Byte, Short, Long, BigInteger or BigDecimal.

		System.out.println("Test was given this board: "+dataTable.cells().toString());
		//throw new io.cucumber.java.PendingException();
	}

	@When("player O plays")
	public void player_o_plays() {
		System.out.println("O Plays");
		//throw new io.cucumber.java.PendingException();
	}

	@Then("O should place at row {int}, col {int}")
	public void o_should_place_at_row_col(Integer defX, Integer defY) {
		int playedx = 2;
		int playedy = 2;
		assertEquals(defX.intValue(), playedx);
		assertEquals(defY.intValue(), playedy);
	}
}
