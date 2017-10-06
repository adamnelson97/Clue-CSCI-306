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
	
	//Methods
	public void calcAdjacencies() {
		//Stores adjacency matrix in adjMtx
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		//Stores possible target cells in targets
	}
	
	public Set<BoardCell> getTargets() {
		return targets; //Getter for targets so the set doesn't have to be created for every get
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell) {
		Set<BoardCell> list = adjMtx.get(cell); //Stores the adjacency list for a cell in a new list
		return list;
	}

}
