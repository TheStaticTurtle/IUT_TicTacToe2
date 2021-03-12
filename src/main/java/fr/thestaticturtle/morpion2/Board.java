package fr.thestaticturtle.morpion2;

import fr.thestaticturtle.utils.Colors;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
	List<List<Who>> _board;

	public Board() {
		_board = new ArrayList<List<Who>>();
		for (int i = 0; i < 3; i++) {
			ArrayList<Who> x = new ArrayList<Who>();
			x.add(null);
			x.add(null);
			x.add(null);
			_board.add(x);
		}
	}

	public Board(List<List<String>> initializer, Who who_is_x, Who who_is_o) {
		_board = new ArrayList<List<Who>>();
		for(List<String> sublist : initializer) {
			List<Who> x = new ArrayList<Who>();
			for(String cell : sublist) {
				if(cell == null) x.add(null);
				else x.add( cell.equals("X") ? who_is_x : who_is_o );
			}
			_board.add(x);
		}
	}

	public Board(Board b) {
		_board = new ArrayList<List<Who>>();
		for(List<Who> sublist : b._board) _board.add(new ArrayList<Who>(sublist));
	}

	private void display_ansi() {
		System.out.print("\033[H\033[2J");
		System.out.print(Colors.WHITE_BACKGROUND + "    T I C   T A C   T O E    " + Colors.RESET + "\n");
		for (int y = 0; y < 15; y++) {
			for (int x = 0; x < 29; x++) {
				if (y == 0 || y == 14 || x == 0 || x == 28) System.out.print(Colors.BLACK_BACKGROUND + " ");
				else if (x == 1 || x == 27) System.out.print(Colors.BLACK_BACKGROUND + " ");
				else if ((x == 2 || x == 26)) System.out.print(Colors.WHITE_BACKGROUND + " ");
				else if ((y == 1 || y == 13 || x == 3 || x == 25)) System.out.print(Colors.WHITE_BACKGROUND + " ");
				else if (!((x - 1) % 8 > 2 && (y - 1) % 4 > 0)) System.out.print(Colors.WHITE_BACKGROUND + " ");
				else if ((x - 1) % 8 > 2 && (y - 1) % 4 > 0) {
					System.out.print(Colors.BLUE_BACKGROUND);
					Who c = _board.get(((y - 1) / 4)).get( ((x - 1) / 8));
					if ((x - 1) % 8 == 5 && (y - 1) % 4 == 2)
						System.out.print(Colors.BLACK + "" + Colors.BLUE_BACKGROUND + ((c == null) ? " " : c.piece));
					else System.out.print(" ");
				} else System.out.print(Colors.RESET + " ");
			}
			System.out.print(Colors.RESET + "\n");
		}
	}
	private void display_ascii() {
		System.out.print("    T I C   T A C   T O E    \n");
		for (int y = 0; y < 15; y++) {
			for (int x = 0; x < 29; x++) {
				if (y == 0 || y == 14 || x == 0 || x == 28) System.out.print("█");
				else if (x == 1 || x == 27) System.out.print("█");
				else if ((x == 2 || x == 26)) System.out.print("█");
				else if ((y == 1 || y == 13 || x == 3 || x == 25)) System.out.print("#");
				else if (!((x - 1) % 8 > 2 && (y - 1) % 4 > 0)) System.out.print("#");
				else if ((x - 1) % 8 > 2 && (y - 1) % 4 > 0) {
					Who c = _board.get(((y - 1) / 4)).get( ((x - 1) / 8));
					if ((x - 1) % 8 == 5 && (y - 1) % 4 == 2)
						System.out.print((c == null) ? " " : c.piece);
					else System.out.print(" ");
				} else System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
	public void display() {
		if(System.getenv("ENABLE_ANSI")!= null && System.getenv("ENABLE_ANSI").equals("1")) display_ansi();
		else display_ascii();
	}

	List<Point> empty_cells() {
		List<Point> out = new ArrayList<Point>();
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if(_board.get(y).get(x) == null) out.add(new Point(x,y));
			}
		}
		return out;
	}

	boolean is_placement_valid(Point p) {
		if(p.x>=0 && p.x<3 && p.y>=0 && p.y<3) {
			for (Point ep : empty_cells()) {
				if(ep.x==p.x && ep.y==p.y) return true;
			}
		}
		return false;
	}

	boolean placeAt(Point p, Who who)  {
		if(is_placement_valid(p)) {
			_board.get(p.y).set(p.x, who);
			return true;
		} else {
			return false;
		}
	}

	Who eraseAt(Point p) {
		if( _board.get(p.y).get(p.x) != null ) {
			Who was = _board.get(p.y).get(p.x);
			_board.get(p.y).set(p.x, null);
			return was;
		}
		return null;
	}

	public Who getAt(Point p) {
		return _board.get(p.y).get(p.x);
	}

	Who getWinner() {
		for (int i = 0; i < 3; i++) {
			if(_board.get(0).get(i) != null && _board.get(0).get(i).equals(_board.get(1).get(i)) && _board.get(0).get(i).equals(_board.get(2).get(i))) return _board.get(0).get(i);
			if(_board.get(i).get(0) != null && _board.get(i).get(0).equals(_board.get(i).get(1)) && _board.get(i).get(0).equals(_board.get(i).get(2))) return _board.get(i).get(0);
		}
		if(_board.get(0).get(0) != null && _board.get(0).get(0).equals(_board.get(1).get(1)) && _board.get(0).get(0).equals(_board.get(2).get(2))) return _board.get(0).get(0);
		if(_board.get(0).get(2) != null && _board.get(0).get(2).equals(_board.get(1).get(1)) && _board.get(0).get(2).equals(_board.get(2).get(2))) return _board.get(0).get(2);
		if(empty_cells().size()==0) return Who.INTERNAL_DRAW;
		return null;
	}

	public String getWinBanner() {
		if(getWinner().piece.equals("X")) {
			return  Colors.BLUE + " ██████╗  ██████╗         ██╗  ██╗" +Colors.RESET + "\n" +
					Colors.BLUE + "██╔════╝ ██╔════╝ ██╗     ╚██╗██╔╝" +Colors.RESET + "\n" +
					Colors.BLUE + "██║  ███╗██║  ███╗╚═╝      ╚███╔╝ " +Colors.RESET + "\n" +
					Colors.BLUE + "██║   ██║██║   ██║██╗      ██╔██╗ " +Colors.RESET + "\n" +
					Colors.BLUE + "╚██████╔╝╚██████╔╝╚═╝     ██╔╝ ██╗" +Colors.RESET + "\n" +
					Colors.BLUE + " ╚═════╝  ╚═════╝         ╚═╝  ╚═╝" + Colors.RESET;
		} else if (getWinner().piece.equals("O")) {
			return  Colors.BLUE + " ██████╗  ██████╗          ██████╗ " + Colors.RESET + "\n" +
					Colors.BLUE + "██╔════╝ ██╔════╝ ██╗     ██╔═══██╗" + Colors.RESET + "\n" +
					Colors.BLUE + "██║  ███╗██║  ███╗╚═╝     ██║   ██║" + Colors.RESET + "\n" +
					Colors.BLUE + "██║   ██║██║   ██║██╗     ██║   ██║" + Colors.RESET + "\n" +
					Colors.BLUE + "╚██████╔╝╚██████╔╝╚═╝     ╚██████╔╝" + Colors.RESET + "\n" +
					Colors.BLUE + " ╚═════╝  ╚═════╝          ╚═════╝ " + Colors.RESET;
		} else {
			return  Colors.BLUE + "██████╗ ██████╗  █████╗ ██╗    ██╗" + Colors.RESET + "\n" +
					Colors.BLUE + "██╔══██╗██╔══██╗██╔══██╗██║    ██║" + Colors.RESET + "\n" +
					Colors.BLUE + "██║  ██║██████╔╝███████║██║ █╗ ██║" + Colors.RESET + "\n" +
					Colors.BLUE + "██║  ██║██╔══██╗██╔══██║██║███╗██║" + Colors.RESET + "\n" +
					Colors.BLUE + "██████╔╝██║  ██║██║  ██║╚███╔███╔╝" + Colors.RESET + "\n" +
					Colors.BLUE + "╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝ ╚══╝╚══╝ " + Colors.RESET;
		}
	}

	public boolean isFinished() {
		return getWinner() != null;
	}
}