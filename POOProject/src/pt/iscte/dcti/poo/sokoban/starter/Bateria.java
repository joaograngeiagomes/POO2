package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Position;

public class Bateria extends ObjetoDoJogo {

	public Bateria(String imageName, Position position) {
		super("Bateria", position);
	}
	
	public int getLevel() {
		return 2;
	}
}
