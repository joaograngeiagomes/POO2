package pt.iscte.dcti.poo.sokoban.starter;
import javax.swing.JOptionPane;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Position;

public class Player extends ObjetoDoJogo implements ActiveObject {
	protected boolean temBateria;

	public Player(String ImageName, Position position) {
		super("Empilhadora_D", position);
	}

	@Override
	public void move(Direction movimento) {
		Position nextPos = getPosition().plus(movimento.asVector());

		if ((nextPos.getY() < 10 || nextPos.getX() < 10 || nextPos.getY() >= 0 || nextPos.getX() >= 0)
				&& !eParede(nextPos)) {
			if (eCaixote(nextPos)) {
				Caixote c = retornaCaixote(nextPos);
				//(c.getPosition().toString());
				if (c.podeMover(movimento))
					c.move(movimento);
				else if(!c.podeMover(movimento))
					return;
			}
			
			else if(eStone(nextPos)) {
				Stone s = retornaStone(nextPos);
				if (s.podeMover(movimento))
					s.move(movimento);
				else if(!s.podeMover(movimento))
					return;
				
			}
			else if (eBuraco(nextPos)) {
				Player pAux = null;
				for (ImageTile i : SokobanGame.getInstance().getTiles()) {
					if (i instanceof Player) 
						 pAux = (Player)i;		
				}
				SokobanGame.getInstance().getTiles().remove(pAux);
				ImageMatrixGUI.getInstance().clearImages();
				ImageMatrixGUI.getInstance().addImages(SokobanGame.getInstance().getTiles());
				ImageMatrixGUI.getInstance().update();
				JOptionPane.showMessageDialog(null, "Game Over");
				System.exit(0);
				// ImageMatrixGUI.getInstance().dispose();
			}
			else if (eBateria(nextPos)) {
				Bateria BAux = null;
				for (ImageTile i : SokobanGame.getInstance().getTiles()) {
					if (i instanceof Bateria) 
						BAux = (Bateria)i;
				}
				SokobanGame.getInstance().getTiles().remove(BAux);
				ImageMatrixGUI.getInstance().clearImages();
				ImageMatrixGUI.getInstance().addImages(SokobanGame.getInstance().getTiles());
				ImageMatrixGUI.getInstance().update();
				temBateria = true;
			}
			

			if (movimento.equals(Direction.DOWN)) {
				setImageName("Empilhadora_D");
			}
			if (movimento.equals(Direction.UP)) {
				setImageName("Empilhadora_U");
			}
			if (movimento.equals(Direction.LEFT)) {
				setImageName("Empilhadora_L");
			}
			if (movimento.equals(Direction.RIGHT)) {
				setImageName("Empilhadora_R");
			}

			setPosition(nextPos);
			

		}

		ImageMatrixGUI.getInstance().update();
	}

	public boolean eParede(Position p) {
		for (ImageTile i : SokobanGame.getInstance().getTiles()) {
			if (i.getPosition().equals(p) && i.getName().equals("Parede"))
				return true;
		}
		return false;
	}

	public Caixote retornaCaixote(Position p) {
		for (ImageTile i : SokobanGame.getInstance().getTiles()) {
			if (i.getPosition().equals(p) && i.getName().equals("Caixote"))
				return (Caixote) i;
		}
		return null;
	}

	public boolean eCaixote(Position p) {
		for (ImageTile i : SokobanGame.getInstance().getTiles()) {
			if (i.getPosition().equals(p) && i.getName().equals("Caixote"))
				return true;
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

	public boolean eBateria(Position p) {
		for (ImageTile i : SokobanGame.getInstance().getTiles()) {
			if (i.getPosition().equals(p) && i.getName().equals("Bateria")) {
				return true;
			}
		}
		return false;
	}

	
	public int getLevel() {
		return 3;
	}

	public boolean eStone(Position p) {
		for (ImageTile i : SokobanGame.getInstance().getTiles()) {
			if (i.getPosition().equals(p) && (i.getName().equals("BigStone") || i.getName().equals("SmallStone"))) {
				return true;
			}
		}
		return false;
	}

	public Stone retornaStone(Position p) {
		for (ImageTile i : SokobanGame.getInstance().getTiles()) {
			if (i.getPosition().equals(p) && (i.getName().equals("BigStone") || i.getName().equals("SmallStone"))) 
				return (Stone) i;
		}
		return null;
	}
	

}
