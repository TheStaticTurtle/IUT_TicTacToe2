package fr.thestaticturtle.iut.morpion2;
import java.awt.*;
import java.util.List;
import java.util.Random;

class MiniMaxGuess {
	Point point;
	int score;
	public MiniMaxGuess(Point point, int score) {
		this.point = point;
		this.score = score;
	}
}

public class AIPlayer extends Player {
	AIPlayer(Board b)  {
		super(b, Who.AI);
	}

	AIPlayer(Board b, Who who) {
		super(b, who);
	}

	int minimax_evaluate(Board b) {
		if( b.getWinner().equals(this.piece.getAdversary()) ) return -1;
		if( b.getWinner().equals(this.piece) ) return  1;
		return 0;
	}

	MiniMaxGuess minimax_move(Board state, int depth, Who player) {
		MiniMaxGuess best_guess;
		if(player.equals(Who.AI)) best_guess = new MiniMaxGuess(null, Integer.MIN_VALUE);
		else best_guess = new MiniMaxGuess(null, Integer.MAX_VALUE);

		if (depth==0 || state.isFinished()){
			return new MiniMaxGuess(null, minimax_evaluate(state));
		}

		for (Point cell : state.empty_cells()) {
			state.placeAt(cell, player);
			MiniMaxGuess guess = minimax_move(new Board(state), depth-1, player.getAdversary());
			state.eraseAt(cell);

			guess.point = cell;

			if(player.equals(Who.AI)) {
				if(guess.score>best_guess.score) best_guess = guess;
			} else {
				if(guess.score<best_guess.score) best_guess = guess;
			}
		}

		return best_guess;
	}

	@Override
	void play() {
		int depth = this.party.empty_cells().size();

		if(depth==0 || this.party.isFinished()) {
			return;
		} else if(depth==9) {
			Random r = new Random();
			Point move = new Point(r.nextInt(3),r.nextInt(3));
			this.party.placeAt(move,piece);
			System.out.println("[Player "+piece+"] played at (row,col): "+(move.y+1)+","+(move.x+1)+"");
		} else {
			MiniMaxGuess guess = minimax_move(new Board(this.party), depth, Who.AI);
			this.party.placeAt(guess.point,piece);
			System.out.println("[Player "+piece+"] played at (row,col): "+(guess.point.y+1)+","+(guess.point.x+1)+"");
		}
	}
}
