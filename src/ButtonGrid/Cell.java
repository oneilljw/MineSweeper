package ButtonGrid;

/**
 * Cell class represents a single cell in the game minesweeper 
 *
 * @author Chase
 * @version 1.0
 */
public class Cell
{
	//private attributes to represent the neighboring mine count, the current
	//representation of the character, and whether or not it is a mine
	private int mineCount;
	private char visibleChar;
	private boolean mine;
		
	/**
	 * Constructor for the Cell class.  
	 */
	public Cell()
	{
		this.visibleChar = '^';
		this.mine = false;
		this.mineCount = 0;
	}
	
	/**
	 * returns whether or not the cell is a mine
	 * @return a boolean representation of the cell
	 */
	public boolean isMine()
	{
	    return mine;
	}
	
	/**
	 * returns whether or not the cell has been revealed
	 * @return a boolean whether or not the cell has been revealed
	 */
	public boolean beenRevealed()
	{
	    return (!(visibleChar == '^'));
	}
	
	/**
	 * sets this Cell to be a mine
	 */
	public void setMine()
	{
		mine = true;
	}
	
	/**
	 * reveals this cell by changing the visibleChar
	 */
	public void reveal()
	{
		if (this.isMine())
			visibleChar = '*';
		else 
			visibleChar = (mineCount + "").charAt(0);
	}
	
	/**
	 * returns the mine count
	 * @return an int representing the mine count
	 */
	public int getMineCount()
	{
	    return mineCount;
	}
	
	/**
	 * sets the mine count for this Cell
	 *
	 * @param value the new value for this cell
	 */
	public void setMineCount(int value)
	{
	    this.mineCount = value;
	}

	/**
	 * Returns a string representation of this Cell
	 * @return a string representation of the Cell
	 */
	public String toString()
	{
        return("" + visibleChar);
	}
		
}