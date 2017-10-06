/*
 * Class: IntBoard
 * 
 * Authors: Nathaniel Fuller, Adam Nelson
 * 
 * Purpose: To create a grid of BoardCells, and be able to calculate adjacent cells, as well as potential cells that can be reached by moving
 * 			N cells away without revisiting cells.
 * 
 */

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
		grid = new BoardCell[GRID_WIDTH][GRID_HEIGHT];
		
		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_HEIGHT; j++) {
				grid[i][j] = new BoardCell(i, j); //Creates new cell and stores it in according grid location
			}
		}
	}
	
	//Methods
	public void calcAdjacencies() {
		// Generates map of all cells and their adjacent cells and stores it in adjMtx
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
		visited.clear();
		targets.clear();
		Set<BoardCell> adj = getAdjList(startCell);
		// Iterate through all adjacent cells
		for(BoardCell c : adj) {
			// If the cell hasn't been visited
			if(!visited.contains(c)) {
				// If the path is length 1, add the cell to targets.
				if(pathLength == 1) {
					targets.add(c);
				}
				// Otherwise, add the adjacent cell to visited, call the function from that cell with a shorter pathLength.
				// Then, after that has completed, remove the cell from visited.
				else {
					visited.add(c);
					calcTargets(c, (pathLength-1));
					visited.remove(c);
				}
			}
		}
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
