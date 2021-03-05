package fr.thestaticturtle.iut.morpion2;

import java.awt.*;
import java.util.List;

public class AIPlayer extends Player {
	AIPlayer(Board b) throws NoSuchFieldException {
		super(b, "O");
	}

	int minimax_evaluate(Board b) {
		if(b.getWinner().equals("X")) return -1;
		if(b.getWinner().equals("O")) return +1;
		return 0;
	}

	List<Point> minimax_empty_cells() {
		return null;
	}

	@Override
	void play() {

	}
}
