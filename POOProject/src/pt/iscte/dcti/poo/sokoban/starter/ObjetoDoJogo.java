package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Position;

public abstract class ObjetoDoJogo implements ImageTile {
	private String imageName;
	private Position position;
	
	public ObjetoDoJogo(String imageName, Position position) {
		this.imageName = imageName;
		this.position = position;
	}
	@Override
	public String getName() {
		return imageName;
	}

	@Override
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public int getLevel() {
		return 1;
	}
	

}
