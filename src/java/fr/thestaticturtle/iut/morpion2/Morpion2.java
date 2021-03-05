package fr.thestaticturtle.iut.morpion2;

public class Morpion2 {
	public static void main(String[] args) throws Exception {
		Board board = new Board();
		Player playerX = new Player(board,"X");
		Player playerO = new Player(board,"O"); //AIPlayer uses O
		//Player playerO = new AIPlayer(board); //AIPlayer uses O

		board.display();
		while(!board.isFinished()) {
			playerX.play();
			if(!(playerO instanceof AIPlayer)) board.display();
			if(board.isFinished()) break;
			playerO.play();
			board.display();
		}

		System.out.println(board.getWinBanner());
	}
}
