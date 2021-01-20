package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Position;

public class Stone extends ObjetoDoJogo implements ActiveObject{

	public Stone(String imageName, Position position) {
		super(imageName, position);
	}
	
	public boolean podeMover(Direction movimento) {
		Position nextPos = getPosition().plus(movimento.asVector());

		if ((nextPos.getY() < 10 || nextPos.getX() < 10 || nextPos.getY() >= 0 || nextPos.getX() >= 0)
				&& !SokobanGame.getInstance().player.eParede(nextPos)
				&& !SokobanGame.getInstance().player.eCaixote(nextPos)
				&& !SokobanGame.getInstance().player.eStone(nextPos)) {
			return true;
		}
		return false;
	}
	
	public void move(Direction movimento) {
		Position nextPos = getPosition().plus(movimento.asVector());

		if ((nextPos.getY() < 10 || nextPos.getX() < 10 || nextPos.getY() >= 0 || nextPos.getX() >= 0)
				&& !SokobanGame.getInstance().player.eParede(nextPos)) {

			if (eBuraco(nextPos)) {
				Stone SAux = null;
				for (ImageTile i : SokobanGame.getInstance().getTiles()) {
					if (i instanceof Buraco && this instanceof SmallStone) {//&& i.getPosition().equals(nextPos)) { // i.getPosition().equals(nextPos)
						 SAux = SokobanGame.getInstance().player.retornaStone(getPosition());
					}
				}
				SokobanGame.getInstance().getTiles().remove(SAux);
				ImageMatrixGUI.getInstance().clearImages();
				ImageMatrixGUI.getInstance().addImages(SokobanGame.getInstance().getTiles());
				ImageMatrixGUI.getInstance().update();
			}

			setPosition(nextPos);

		}
		ImageMatrixGUI.getInstance().update();
	}

	@Override
	public boolean eBuraco(Position p) {
		for(ImageTile i: SokobanGame.getInstance().getTiles()) {
			if(i.getPosition().equals(p) && i.getName().equals("Buraco")){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean eParede(Position p) {
		for(ImageTile i : SokobanGame.getInstance().getTiles()) {
			if(i.getPosition().equals(p) && i.getName().equals("Parede"))
				return true;
		}
		return false;
	}

	public int getLevel() {
		return 2;
	}

}
