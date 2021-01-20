package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Position;

public interface ActiveObject {
	
	void move(Direction movimento);
	public boolean eParede(Position p);
	public boolean eBuraco(Position p);
}
