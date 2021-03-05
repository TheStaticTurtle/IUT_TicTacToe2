package fr.thestaticturtle.morpion2;

public class Morpion2 {
	public static void main(String[] args) throws Exception {
		Board board = new Board();
		Player playerX = new Player(board,"X");
		Player playerO = new Player(board,"O");

		board.display();
		while(!board.isFinished()) {
			playerX.play();
			board.display();
			if(board.isFinished()) break;
			playerO.play();
			board.display();
		}

		System.out.println(board.getWinBanner());
	}
}
