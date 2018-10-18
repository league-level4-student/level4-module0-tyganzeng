package _04_Maze_Maker;
import java.awt.Graphics;

import _03_Conways_Game_of_Life.Cell;

public class Maze {
	//1. Create a 2D array of cells. Don't initialize it.
	Cel[][] cells;
	private int width;
	private int height;

	public Maze(int w, int h) {
		this.width = w;
		this.height = h;

		//2. Initialize the cells using the width and height varibles
		cells = new Cel[width][height];
		//3. Iterated through each cell and initialize it
		//   using i and j as the location
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cel(i,j);
			}
		}
	}

	//4. This method iterates through the cells and draws them
	public void draw(Graphics g) {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].draw(g);
			}
		}
	}
	
	//4b. This method returns the selected cell.
	public Cel getCell(int x, int y){
		return cells[x][y];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
