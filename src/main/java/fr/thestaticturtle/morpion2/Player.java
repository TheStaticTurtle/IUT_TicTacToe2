package fr.thestaticturtle.morpion2;

import java.awt.*;
import java.util.Scanner;

public class Player {
	protected Board party;
	Who piece;

	Player(Board p, Who player_piece) {
		piece = player_piece;
		party = p;
	}

	public void play() {
		Scanner in = new Scanner(System.in);

		System.out.print("[Player "+piece+"] Where do you want to play (row,col): ");
		String s = in.nextLine();
		if(s.equals("exit")||s.equals("quit")) System.exit(0);
		if(!s.matches("\\d,\\d")) {
			System.out.println("Invalid input");
			play();
			return;
		}
		String[] split = s.split(",");
		if(split.length != 2) {
			System.out.println("Invalid input");
			play();
			return;
		}
		int y = Integer.parseInt(split[0]);
		int x = Integer.parseInt(split[1]);
		if(! party.placeAt(new Point(x-1,y-1),piece)) {
			System.out.println("Requested position is invalid");
			play();
		}
	}
}
