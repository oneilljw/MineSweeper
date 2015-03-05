package ButtonGrid;

import java.util.Random;

/**
 * Grid class represents the grid as a 2-D array of Cells
 *
 * @author Chase/Andrew O'Neill
 * @version 1.1 - update from version 1.0 to add getSymbol method
 * 
 */
public class Grid
{
	//Constant to represent percentage of cells that are mines
	public static final double MINEPERCENTAGE = 0.156;
	
	//attributes to represent the number of columns, the number of
	//rows, and the number of mines
	private int columns;
	private int rows;
	private int numMines;

	//attribute to represent the 2-D array of cells
	//note that it is private
	private Cell[][] grid; 
	
	/**
	 * Constructor for the Grid class.  Establishes the 
	 * 2-d array of cells, places the mines, and fills in the 
	 * mine counts
	 *
	 */
	public Grid(int i, int j)
	{
		this.rows = i;
		this.columns = j;
		grid = new Cell[rows][columns];
		for (int x = 0; x<rows; x++) 
		{
			for (int y = 0; y<columns; y++) 
			{
					grid[x][y] = new Cell();				
			}
		}
		setMines();
		updateMineCounts();
	}

	/**
	 * Set the mines in the grid (note the private helper method)
	 *
	 */
	private void setMines()
	{
		Random rand = new Random();
		numMines = (int)(rows * columns * MINEPERCENTAGE);
		int count = 0;
		int x, y;
				
		while (count < numMines)
		{
			x = rand.nextInt(rows);
			y = rand.nextInt(columns);
			
			if (!(grid[x][y].isMine()))
			{
				grid[x][y].setMine();
				count++;
			}
		}
	}	
		
	/**
	 * update the mine counts in the grid based upon the placement of mines
	 * (note the private helper method)
	 */
	private void updateMineCounts()
	{
		int mc;
		
		for (int i=0; i<rows; i++) 
		{
			for (int j=0; j<columns; j++) 
			{
				if (grid[i][j].isMine())
					grid[i][j].setMineCount(0);
				else
				{
					mc = 0;
					//count the number of neighbors that are mines
					for (int x=-1; x<2; x++) 
					{
						for (int y=-1; y<2; y++) 
						{
							if (valid(i,j,x,y))
							{
								if (grid[i+x][j+y].isMine())
									mc++;
							}
						}
					}
					grid[i][j].setMineCount(mc);
				}
			}
		}
	}

	
	/**
	 * private helper method to determine if a given position is valid
	 * @param i is the base column coordinate
	 * @param j is the base row coordinate
	 * @param x is the column offset
	 * @param y is the row offset
	 */
	private boolean valid(int i, int j, int x, int y)
	{
		boolean result = true;
		if ((x==0) && (y==0))
			result = false;
		else if (((i+x)<0) || ((i+x)>=rows))
			result = false;
		else if (((j+y)<0) || ((j+y)>=columns))
			result = false;
		
		return result;
	}

	/**
	 * Returns a boolean indicating whether a mine has been hit
	 *
	 * @param x the x coordinate of the given cell
	 * @param y the y coordinate of the given cell
	 * @return boolean indicating whether a mine has been hit
	 */
	public boolean checkMine(int row, int col)
	{
		grid[row][col].reveal();
		return grid[row][col].isMine();
	}
	
	/**
	 * Returns the number of cells that are not mines
	 *
	 * @return int number of cells that are not mines
	 */
	public int cellCount()
	{
		return ((rows * columns) - numMines);
	}
	
	/**
	 * Returns a boolean whether or not the given cell has been revealed
	 *
	 * @param x the x coordinate of the given cell
	 * @param y the y coordinate of the given cell
	 * @return boolean whether or not the given cell has been revealed
	 */
	public boolean newMove(int x, int y)
	{
		return (!(grid[x][y].beenRevealed()));
	}
	
	public String getSymbol(int x, int y)
	{
		return grid[x][y].toString();
	}
}