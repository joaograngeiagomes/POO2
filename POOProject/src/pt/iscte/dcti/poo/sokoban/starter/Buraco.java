package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Position;

public class Buraco extends ObjetoDoJogo {

	public Buraco(String imageName, Position position) {
		super("Buraco", position);
	}
	public int getLevel() {
		return 1;
	}
}
