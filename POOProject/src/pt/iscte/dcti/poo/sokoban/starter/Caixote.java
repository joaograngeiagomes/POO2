package pt.iscte.dcti.poo.sokoban.starter;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Position;

public class Caixote extends ObjetoDoJogo implements ActiveObject {

	public Caixote(String imageName, Position position) {
		super("Caixote", position);
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

	@Override
	public void move(Direction movimento) {
		Position nextPos = getPosition().plus(movimento.asVector());

		if ((nextPos.getY() < 10 || nextPos.getX() < 10 || nextPos.getY() >= 0 || nextPos.getX() >= 0)
				&& !SokobanGame.getInstance().player.eParede(nextPos)) {

			if (eBuraco(nextPos)) {
				Caixote CAux = null;
				for (ImageTile i : SokobanGame.getInstance().getTiles()) {
					if (i instanceof Buraco) {
						 CAux = SokobanGame.getInstance().player.retornaCaixote(getPosition());
					}
				}
				SokobanGame.getInstance().getTiles().remove(CAux);
				ImageMatrixGUI.getInstance().clearImages();
				ImageMatrixGUI.getInstance().addImages(SokobanGame.getInstance().getTiles());
				ImageMatrixGUI.getInstance().update();
				JOptionPane.showMessageDialog(null, "Game Over");			}

			setPosition(nextPos);

		}
		ImageMatrixGUI.getInstance().update();

		if (eAlvo(nextPos) && SokobanGame.getInstance().noAlvo() == true) {
			try {
				Thread.currentThread();
				Thread.sleep(300);
			} catch (InterruptedException e) {
			}
			SokobanGame.getInstance().carregaNivel();
			System.out.println("TODOS OS CAIXOTES NOS ALVOS");
		}
	}

	public boolean eAlvo(Position p) {
		for (ImageTile i : SokobanGame.getInstance().getTiles()) {
			if (i.getPosition().equals(p) && i.getName().equals("Alvo")) {
				return true;
			}
		}
		return false;
	}

	public boolean eBuraco(Position p) {
		for (ImageTile i : SokobanGame.getInstance().getTiles()) {
			if (i.getPosition().equals(p) && i.getName().equals("Buraco")) {
				return true;
			}
		}
		return false;
	}

	public boolean eParede(Position p) {
		for (ImageTile i : SokobanGame.getInstance().getTiles()) {
			if (i.getPosition().equals(p) && i.getName().equals("Parede"))
				return true;
		}
		return false;
	}

	public int getLevel() {
		return 2;
	}

}
