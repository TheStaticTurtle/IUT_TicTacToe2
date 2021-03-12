package fr.thestaticturtle.morpion2;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.*;

public class PlayerUnitTest {

	@Test
	public void testPlayerPlayNormal() {
		Board b = new Board();

		//Replace stdin for testing the input
		InputStream sysInBackup = System.in; // backup System.in to restore it later
		ByteArrayInputStream in = new ByteArrayInputStream("1,1\n".getBytes());
		System.setIn(in);

		Player p = new Player(b, Who.HumanA);
		p.play();

		Assert.assertTrue( b.getAt(new Point(0,0)).equals(Who.HumanA) );
		System.setIn(sysInBackup);
	}

	@Test(expected = java.util.NoSuchElementException.class) //Scanner will fail because it expect a new line when the first is invalid
	public void testPlayerPlaceOutsideBoard() {
		Board b = new Board();

		InputStream sysInBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream("0,0\n".getBytes());
		System.setIn(in);

		Player p = new Player(b, Who.HumanA);
		p.play();

		Assert.assertFalse(b.getAt(new Point(0,0)).equals(Who.HumanA) );
		System.setIn(sysInBackup);
	}

	@Test(expected = java.util.NoSuchElementException.class) //Scanner will fail because it expect a new line when the first is invalid
	public void testPlayerPlaceOutsideBoard2() {
		Board b = new Board();

		InputStream sysInBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream("4,4\n".getBytes());
		System.setIn(in);

		Player p = new Player(b, Who.HumanA);
		p.play();

		Assert.assertFalse(b.getAt(new Point(0,0)).equals(Who.HumanA) );
		System.setIn(sysInBackup);
	}

}
