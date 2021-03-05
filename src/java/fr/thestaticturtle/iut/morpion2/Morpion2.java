package fr.thestaticturtle.iut.morpion2;

public class Morpion2 {
	public static void main(String[] args) throws Exception {
		Board board = new Board();
		Player playerX = new Player(board,Who.HumanA);
		Player playerO = new Player(board,Who.HumanB);

		//playerX = new AIPlayer(board, Who.AI_BIS);
		playerO = new AIPlayer(board);

		board.display();
		while(!board.isFinished()) {
			playerX.play();
			board.display();
			if(board.isFinished()) break;
			playerO.play();
			board.display();
		}

		System.out.println();
		System.out.println();
		System.out.println(board.getWinBanner());
	}
}
