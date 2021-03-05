package fr.thestaticturtle.iut.morpion2;

public enum Who {
	HumanA         ("X"),
	HumanB         ("O"),
	AI             ("O"),
	AI_BIS         ("X"),

	INTERNAL_DRAW  ("draw");

	final String piece;
	Who(String piece) {
		this.piece = piece;
	}

	public String toString() {
		return this.piece;
	}

	boolean equals(Who w) {
		if(w==null) return false;
		return this.piece.equals(w.piece);
	}

	Who getAdversary() {
		if(this.piece.equals("O")) return Who.HumanA;
		else return Who.AI;
	}
}
