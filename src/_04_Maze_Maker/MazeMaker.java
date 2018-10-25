package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cel> uncheckedCells = new Stack<Cel>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		int randW = randGen.nextInt(w);
		int randH = randGen.nextInt(h);
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cells[randW][randH]);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cel currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		
		ArrayList<Cel> cells = getUnvisitedNeighbors(currentCell);
		//B. check for unvisited neighbors using the cell
		/*int sum = 0;
		
		if(currentCell.getY() > 0 && !maze.cells[currentCell.getX()][currentCell.getY()-1].hasBeenVisited()) {
			sum++;
		}
		if(currentCell.getX() > 0 && !maze.cells[currentCell.getX()-1][currentCell.getY()].hasBeenVisited()) {
			sum++;
		}
		if(currentCell.getX() < maze.cells.length - 1 && !maze.cells[currentCell.getX()+1][currentCell.getY()].hasBeenVisited()) {
			sum++;
		}
		if(currentCell.getY() < maze.cells.length - 1 && !maze.cells[currentCell.getX()][currentCell.getY()+1].hasBeenVisited()) {
			sum++;
		}
		
		Cel[] neighbors = new Cel[sum];
		int pos = 0;
		if(currentCell.getY() > 0 && !maze.cells[currentCell.getX()][currentCell.getY()-1].hasBeenVisited()) {
			neighbors[pos] = maze.cells[currentCell.getX()][currentCell.getY()-1];
			pos++;
		}
		if(currentCell.getX() > 0 && !maze.cells[currentCell.getX()-1][currentCell.getY()].hasBeenVisited()) {
			neighbors[pos] = maze.cells[currentCell.getX()-1][currentCell.getY()];
			pos++;
		}
		if(currentCell.getX() < maze.cells.length - 1 && !maze.cells[currentCell.getX()+1][currentCell.getY()].hasBeenVisited()) {
			neighbors[pos] = maze.cells[currentCell.getX()+1][currentCell.getY()];
			pos++;
		}
		if(currentCell.getY() < maze.cells.length - 1 && !maze.cells[currentCell.getX()][currentCell.getY()+1].hasBeenVisited()) {
			neighbors[pos] = maze.cells[currentCell.getX()][currentCell.getY()+1];
			pos++;
		}*/
		//C. if has unvisited neighbors,
		if(cells.size() > 0) {
			//C1. select one at random.
			int rand = randGen.nextInt(cells.size());
			
			//C2. push it to the stack
			uncheckedCells.push(cells.get(rand));
			//C3. remove the wall between the two cells
			removeWalls(currentCell, cells.get(rand));
			//C4. make the new cell the current cell and mark it as visited
			currentCell = cells.get(rand);
			currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
			
		}
		//D. if all neighbors are visited
		else if (cells.size() == 0) {
			//D1. if the stack is not empty
			if(!uncheckedCells.isEmpty()) {
				// D1a. pop a cell from the stack

				// D1b. make that the current cell
				currentCell = uncheckedCells.pop();
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cel c1, Cel c2) {

		if(Math.abs(c1.getX() - c2.getX()) == 1 ^ Math.abs(c1.getY() - c2.getY()) == 1 ) {
			if(c1.getX() - c2.getX() == 1) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			} else if (c2.getX() - c1.getX() == 1) {
				c2.setWestWall(false);
				c1.setEastWall(false);
			}else if (c1.getY() - c2.getY() == 1) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}else if (c2.getY() - c1.getY() == 1) {
				c2.setNorthWall(false);
				c1.setSouthWall(false);
			}
			
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cel> getUnvisitedNeighbors(Cel c) {
		ArrayList<Cel> cels = new ArrayList<Cel>();
		
		if(c.getY() > 0 && !maze.cells[c.getX()][c.getY()-1].hasBeenVisited()) {
			cels.add(maze.cells[c.getX()][c.getY()-1]);
		}
		if(c.getX() > 0 && !maze.cells[c.getX()-1][c.getY()].hasBeenVisited()) {
			cels.add(maze.cells[c.getX()-1][c.getY()]);
		}
		if(c.getX() < maze.cells.length - 1 && !maze.cells[c.getX()+1][c.getY()].hasBeenVisited()) {
			cels.add(maze.cells[c.getX()+1][c.getY()]);
		}
		if(c.getY() < maze.cells.length - 1 && !maze.cells[c.getX()][c.getY()+1].hasBeenVisited()) {
			cels.add(maze.cells[c.getX()][c.getY()+1]);
		}
		return cels;
	}
}
