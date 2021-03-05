package fr.thestaticturtle.morpion2;

import java.awt.*;
import java.util.Scanner;

public class Player {
	Board party;
	String piece;

	Player(Board p, String player_piece) throws NoSuchFieldException {
		if(!player_piece.equals("O") && !player_piece.equals("X")) throw new NoSuchFieldException("Invalid player piece");
		piece = player_piece;
		party = p;
	}

	void play() {
		Scanner in = new Scanner(System.in);

		System.out.print("[Player "+piece+"] Where do you want to play (row,col): ");
		String s = in.nextLine();
		if(!s.matches("\\d,\\d")) {
			System.out.println("Invalid input");
			play();
		}
		String[] split = s.split(",");
		int y = Integer.parseInt(split[0]);
		int x = Integer.parseInt(split[1]);
		if(x>0 && x<4 && y>0 && y<4) {
			try {
				party.placeAt(new Point(x,y),piece);
			} catch (Exception e) {
				System.out.println("Invalid input ("+e.getMessage()+")");
				play();
			}
		} else {
			System.out.println("Invalid input");
			play();
		}
	}
}
