package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Position;

public class Parede extends ObjetoDoJogo{

	public Parede(String imageName, Position position) {
		super("Parede", position);
	}
	
	public int getLevel() {
		return 0;
	}

}
