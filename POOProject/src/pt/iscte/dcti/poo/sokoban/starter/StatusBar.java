package pt.iscte.dcti.poo.sokoban.starter;
import javax.swing.JOptionPane;
import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class StatusBar {
	private int level;
	private int moves;
	private int energy;
	
	public StatusBar(){
		this.level = 0;
		this.moves = 0;
		this.energy = 200;
		ImageMatrixGUI.getInstance().setStatusMessage("Level: " + level + " Moves: " + moves + " Energy: " + energy);
	}
	
	public void incrementaMoves() {
		moves = moves +1;
		ImageMatrixGUI.getInstance().setStatusMessage("Level: " + level + " Moves: " + moves + " Energy: " + energy);
	}
	
	public void decrementaEnergia() {
		energy = energy - 2;
		ImageMatrixGUI.getInstance().setStatusMessage("Level: " + level + " Moves: " + moves + " Energy: " + energy);
		if(energy <=0) {
			JOptionPane.showMessageDialog(null, "Game Over");
			System.exit(0);
		}
	}
	
	public void incrementaEnergia() {
		if(SokobanGame.getInstance().player.temBateria == true) {
			energy = energy +34;
			ImageMatrixGUI.getInstance().setStatusMessage("Level: " + level + " Moves: " + moves + " Energy: " + energy);
			SokobanGame.getInstance().player.temBateria = false;
		}
	}
}
