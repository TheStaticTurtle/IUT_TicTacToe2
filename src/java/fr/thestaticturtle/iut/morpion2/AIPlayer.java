package fr.thestaticturtle.iut.morpion2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AIPlayer extends Player {
	AIPlayer(Board b) throws NoSuchFieldException {
		super(b, "O");
	}

	float minimax_evaluate(Board b) {
		if(b.getWinner() == null) return 0f;
		if(b.getWinner().equals("X")) return -1f;
		if(b.getWinner().equals("O")) return +1f;
		return 0f;
	}

	Float[] minimax_move(Board state, int depth, String player) throws NoSuchFieldException {
		Float[] best;
		if(player.equals("O")) best = new Float[]{-1f, -1f, Float.NEGATIVE_INFINITY};
		else best = new Float[]{-1f, -1f, Float.POSITIVE_INFINITY};

		if (depth==0 || state.isFinished()){
			return new Float[]{-1f, -1f, minimax_evaluate(state)};
		}

		for (Point cell : state.empty_cells()) {
			state.placeAt(cell, player);
			Float[] guess = minimax_move(state, depth-1, player.equals("X") ? "O" : "X");
			guess[0] = (float) cell.x;
			guess[1] = (float) cell.y;

			if(player.equals("O")) {
				if(guess[2]>best[2]) best = guess;
			} else {
				if(guess[2]<best[2]) best = guess;
			}
		}

		return best;
	}

	@Override
	void play() throws NoSuchFieldException {
		int depth = this.party.empty_cells().size();

		if(depth==0 || this.party.isFinished()) {
			return;
		} else if(depth==9) {
			Random r = new Random();
			Point move = new Point(r.nextInt(3+1)+1,r.nextInt(3+1)+1);
			this.party.placeAt(move,piece);
			System.out.println("[Player "+piece+"] played at (row,col): "+(move.y+1)+","+(move.x+1)+"");
		} else {
			Float[] guess = minimax_move(new Board(this.party), depth,"O");
			//System.out.println(this.party._board);
			//System.out.println(Arrays.toString(guess));
			this.party.placeAt(new Point( Math.round(guess[0]) +1, Math.round(guess[1])+1 ),piece);
			System.out.println("[Player "+piece+"] played at (row,col): "+(Math.round(guess[0]) +1)+","+(Math.round(guess[1])+1)+"");
		}
	}
}
