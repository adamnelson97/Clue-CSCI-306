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
	static final int GRID_HEIGHT = 4;
	static final int GRID_WIDTH = 4;
	
	public IntBoard() {
		//IntBoard constructor
		adjMtx = new HashMap<BoardCell, Set<BoardCell>>();
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		
		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_HEIGHT; j++) {
				grid[i][j] = new BoardCell(i, j); //Creates new cell and stores it in according grid location
			}
		}
	}
	
	//Methods
	public void calcAdjacencies() {
		BoardCell currCell;
		Set<BoardCell> adjSet;
		for(int i = 0; i < GRID_WIDTH; i++) { // Iterate through all cells in grid[][]
			for(int j = 0; j < GRID_HEIGHT; j++) {
				currCell = grid[i][j];
				adjSet = new HashSet<BoardCell>();
				// Check to see whether each neighboring cell exists, i.e. Index not out of bounds, and add it to adjSet.
				if(i > 0) { 
					adjSet.add(grid[i-1][j]);
				}
				if(i < GRID_WIDTH - 1) {
					adjSet.add(grid[i+1][j]);
				}
				if(j > 0) {
					adjSet.add(grid[i][j-1]);
				}
				if(j < GRID_HEIGHT - 1) {
					adjSet.add(grid[i][j+1]);
				}
				adjMtx.put(currCell, adjSet);
			}
		}
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
	
	public BoardCell getCell(int i, int j) {
		return grid[i][j]; //returns the BoardCell stored at the specified value
	}

}
