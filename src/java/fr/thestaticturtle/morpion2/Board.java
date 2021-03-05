package fr.thestaticturtle.morpion2;

import java.util.ArrayList;
import java.util.List;

public class Board {
	List<List<String>> _board;

	public Board() {
		_board = new ArrayList<List<String>>();
	}

	public Board(List<List<String>> initializer) {
		_board = new ArrayList<List<String>>();
		for(List<String> sublist : initializer) _board.add(new ArrayList<String>(sublist));
	}
}
