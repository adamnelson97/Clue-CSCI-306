package experiment;

import java.util.Map;
import java.util.Set;

public class IntBoard {
	
	private Map<BoardCell, Set<BoardCell>> adjMtx; //Stands for adjacentMatrix
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;

	public IntBoard() {
		// TODO IntBoard constructor
	}

}
