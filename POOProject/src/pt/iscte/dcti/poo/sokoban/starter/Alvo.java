package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Position;

public class Alvo extends ObjetoDoJogo {

	public Alvo(String imageName, Position position) {
		super("Alvo", position);
	}
	
	public int getLevel() {
		return 1;
	}
}
