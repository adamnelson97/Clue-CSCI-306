package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	
	private Map<BoardCell, Set<BoardCell>> adjMtx; //Stands for adjacentMatrix
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;

	//Static Variables for Board size
	static final int gridHeight = 4;
	static final int gridWidth = 4;
	
	public IntBoard() {
		//IntBoard constructor
		adjMtx = new HashMap<BoardCell, Set<BoardCell>>();
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		
		for (int i = 0; i < gridHeight; i++) {
			for (int j = 0; j < gridWidth; j++) {
				grid[i][j] = new BoardCell(i, j); //Creates new cell and stores it in according grid location
			}
		}
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
		return adjMtx.get(cell); //Stores the adjacency list for a cell in a new list
	}

}
