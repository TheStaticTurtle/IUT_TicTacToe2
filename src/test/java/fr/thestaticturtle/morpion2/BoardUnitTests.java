package fr.thestaticturtle.morpion2;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardUnitTests {

	@Test
	public void testInitEmpty() {
		Board b = new Board();
		Assert.assertEquals(9, b.empty_cells().size());
	}

	@Test
	public void testInitFromList() {
		ArrayList<List<String>> o = new ArrayList<List<String>>();
		for (int i = 0; i < 3; i++) {
			ArrayList<String> x = new ArrayList<String>();
			x.add(null); x.add(null); x.add(null);
			o.add(x);
		}
		o.get(2).set(2,"X");

		Board b = new Board(o,Who.HumanA,Who.HumanB);
		Assert.assertEquals(Who.HumanA, b.getAt(new Point(2,2)));
	}

	@Test
	public void testEmptyCells() {
		ArrayList<List<String>> o = new ArrayList<List<String>>();
		for (int i = 0; i < 3; i++) {
			ArrayList<String> x = new ArrayList<String>();
			x.add(null); x.add(null); x.add(null);
			o.add(x);
		}
		Board b = new Board(o,Who.HumanA,Who.HumanB);
		Assert.assertEquals(9, b.empty_cells().size());

		o.get(0).set(0,"X");
		o.get(1).set(0,"X");
		o.get(2).set(0,"X");
		o.get(0).set(1,"X");
		o.get(1).set(1,"X");
		o.get(2).set(1,"X");
		o.get(0).set(2,"X");
		o.get(1).set(2,"X");
		o.get(2).set(2,"X");

		b = new Board(o,Who.HumanA,Who.HumanB);
		Assert.assertEquals(Who.HumanA, b.getAt(new Point(2,2)));

		Assert.assertEquals(0, b.empty_cells().size());
	}

	@Test
	public void testPlacementValid() {
		ArrayList<List<String>> o = new ArrayList<List<String>>();
		for (int i = 0; i < 3; i++) {
			ArrayList<String> x = new ArrayList<String>();
			x.add(null); x.add(null); x.add(null);
			o.add(x);
		}
		Board b = new Board(o,Who.HumanA,Who.HumanB);
		Assert.assertTrue(b.is_placement_valid(new Point(0,0)));

		o.get(0).set(0,"X");

		b = new Board(o,Who.HumanA,Who.HumanB);
		Assert.assertFalse(b.is_placement_valid(new Point(0,0)));
	}

	@Test
	public void testPlacementValidBounds() {
		Board b = new Board();
		Assert.assertFalse(b.is_placement_valid(new Point(3,3)));
	}

	@Test
	public void testPlaceAt() {
		Board b = new Board();
		Assert.assertTrue(b.is_placement_valid(new Point(0,0)));

		b.placeAt(new Point(0,0),Who.HumanA);

		Assert.assertFalse(b.is_placement_valid(new Point(0,0)));
	}

	@Test
	public void testEraseAt() {
		Board b = new Board();
		Assert.assertTrue(b.is_placement_valid(new Point(0,0)));
		b.placeAt(new Point(0,0),Who.HumanA);
		Assert.assertFalse(b.is_placement_valid(new Point(0,0)));
		b.eraseAt(new Point(0,0));
		Assert.assertTrue(b.is_placement_valid(new Point(0,0)));
	}

	@Test
	public void testGetWinner() {
		Board b = new Board();
		Assert.assertNull(b.getWinner());

		b.placeAt(new Point(0,0),Who.HumanA);
		b.placeAt(new Point(1,0),Who.HumanA);
		b.placeAt(new Point(2,0),Who.HumanA);

		b.placeAt(new Point(0,0),Who.HumanB);
		b.placeAt(new Point(1,0),Who.HumanB);

		Assert.assertEquals(b.getWinner(),Who.HumanA);
	}

	@Test
	public void testisFinished() {
		Board b = new Board();
		Assert.assertFalse(b.isFinished());
		b.placeAt(new Point(0,0),Who.HumanA);
		Assert.assertFalse(b.isFinished());
		b.placeAt(new Point(1,0),Who.HumanA);
		Assert.assertFalse(b.isFinished());
		b.placeAt(new Point(2,0),Who.HumanA);
		Assert.assertTrue(b.isFinished());
	}
}
