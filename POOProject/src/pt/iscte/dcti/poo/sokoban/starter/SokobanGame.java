package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Position;

public class SokobanGame implements Observer {
 	
	protected Player player; 
	protected ArrayList<ImageTile> tiles;
	//protected ArrayList<ObjetoDoJogo> objetos;
	private static SokobanGame instance = null;
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	private StatusBar s;
	private int nrNivel = 0;
	//protected ArrayList<Position> pAlvos = new ArrayList<Position>();
	
	public SokobanGame(){
	}
	
	public static SokobanGame getInstance(){
		if(instance == null)
			instance = new SokobanGame();
		return instance ;
	}
	
	public void arrancaSokobanGame(){
		if(nrNivel < 3) {
			s = new StatusBar();
			tiles = buildSampleLevel();
			lerNivel("levels/level" + nrNivel + ".txt");
		}else {
			JOptionPane.showMessageDialog(null, "Terminou o jogo");
			ImageMatrixGUI.getInstance().dispose();
		}
			
		ImageMatrixGUI.getInstance().addImages(tiles);
		gui.addObserver(this);
		gui.go();
		
	}
	
	private void lerNivel(String file) {
		try {
			Scanner scanner = new Scanner(new File(file));
			int j = 0;
			while(scanner.hasNext()) {
				String s = scanner.nextLine();
				for(int i = 0; i<s.length(); i++) {
					char c = s.charAt(i);
					if(c == '#') {
						tiles.add(new Parede("Parede",new Position(i,j)));
					}
					else if(c == 'b') {
						tiles.add(new Bateria("Bateria",new Position(i,j)));
					}
					else if(c == 'X') {
						tiles.add(new Alvo("Alvo",new Position(i,j)));
						//pAlvos.add(new Position(i,j));
					}
					else if(c == 'C') {
						tiles.add(new Caixote("Caixote", new Position(i,j)));
					}
					else if(c == 'O') {
						tiles.add(new Buraco("Buraco",new Position(i,j)));
					}
					else if(c == 's') {
						tiles.add(new SmallStone("SmallStone", new Position(i,j)));
					}
					else if(c == 'S') {
						tiles.add(new BigStone("BigStone", new Position(i,j)));
					}
					else if(c == 'E') {
						player = new Player("Empilhadora_D",new Position(i,j));
					}
				}
				j++;
			}
			tiles.add(player);
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	private ArrayList<ImageTile> buildSampleLevel(){
		ArrayList<ImageTile> sampleLevelTiles = new ArrayList<ImageTile>();
		for (int y=0; y!=10; y++)
			for (int x=0; x!=10 ; x++)
				sampleLevelTiles.add(new Chao("Chao", new Position(x,y)));
				
		return sampleLevelTiles;	
	}
	
	
	
	public boolean noAlvo() {
		boolean tna = true;
		for(ImageTile i: tiles) {
			if(i instanceof Caixote) {
				System.out.println(i.getPosition().toString());
				tna = checkBox(i.getPosition()) && tna;
			}
		}
		return tna;
	}
	public boolean checkBox(Position position) { 
		for(ImageTile i : tiles) {
			if(i instanceof Alvo && i.getPosition().equals(position)) {
						//JOptionPane.showMessageDialog(null, "PASSOU DE NIVEL ");
						//System.exit(0);
						return true;
					}
				}
		
		
		return false;
	}

	void carregaNivel() {
		ImageMatrixGUI.getInstance().clearImages();
		nrNivel++;
		arrancaSokobanGame();
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		int lastKeyPressed = (Integer) arg1;
		System.out.println("Key pressed " + lastKeyPressed);
		if(lastKeyPressed == KeyEvent.VK_DOWN) {
			player.move(Direction.DOWN);
			s.incrementaMoves();
			s.decrementaEnergia();
			s.incrementaEnergia();
		}
		if(lastKeyPressed == KeyEvent.VK_UP) {
			player.move(Direction.UP);
			s.incrementaMoves();
			s.decrementaEnergia();
			s.incrementaEnergia();
		}
		if(lastKeyPressed == KeyEvent.VK_RIGHT) {
			player.move(Direction.RIGHT);
			s.incrementaMoves();
			s.decrementaEnergia();
			s.incrementaEnergia();
		}
		if(lastKeyPressed == KeyEvent.VK_LEFT ) {
			player.move(Direction.LEFT);
			s.incrementaMoves();
			s.decrementaEnergia();
			s.incrementaEnergia();
		}			
	}

	public ArrayList<ImageTile> getTiles() {
		return tiles;
	}
	

}
