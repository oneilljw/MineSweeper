package ButtonGrid;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame; //imports JFrame library
import javax.swing.JButton; //imports JButton library
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout; //imports GridLayout library
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/****
 * Replaces the view and controller classes in the minesweeper application
 * @author Andrew O'Neill
 * version 1.0
 */
public class ButtonGrid implements ActionListener
{
    JButton[][] buttonGrid; //names the grid of buttons
    int rows, columns;
    Grid grid;
    int count;
    ImageIcon logoIcon;	//MineSweeper Logo
        
    public ButtonGrid()
    { 
    	//constructor
        JFrame frame = new JFrame("MineSweeper");
        logoIcon = createImageIcon("MinesweeperCG.png", "MineSweeper Logo");
        
        //get the size of the mine field from the user. Dialog returns the number
        //of rows and columns in a Point object. If user exits the dialog without
        //selecting a mine field size, (0,0) is returned
        MinefieldSizeDialog sizeDlg = new MinefieldSizeDialog(frame, logoIcon);
        Point size = sizeDlg.showDialog();
        if(size.x > 0 && size.y > 0)
        {
        	rows = size.x;
        	columns = size.y;
        }
        else
        	System.exit(0);	//user closed size dialog, doesn't want to play
        
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(new GridLayout(rows,columns)); //set layout 
        contentPane.setBorder(BorderFactory.createBevelBorder(
        					BevelBorder.RAISED, Color.BLACK, Color.BLACK));
        
        //set up the mine field for the initial play
        initializeMineField(contentPane);
            
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //sets appropriate size for frame
        frame.setVisible(true); //makes frame visible 
    }
    
    void initializeMineField(JPanel contentPane)
    {
    	 buttonGrid = new JButton[rows][columns]; //allocate the size of grid
         grid = new Grid(rows, columns);
         count = 0;
               
         Dimension buttonSize = new Dimension(9*4,9*3);
         for(int row=0; row < rows; row++)
             for(int col=0; col<columns; col++)
             {
             	buttonGrid[row][col] = new JButton(grid.getSymbol(row, col)); //creates new button
             	buttonGrid[row][col].setPreferredSize(buttonSize);
             	buttonGrid[row][col].addActionListener(this);
                contentPane.add(buttonGrid[row][col]); //adds button to grid      
             }
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
   	ImageIcon createImageIcon(String path, String description)
   	{
   		java.net.URL imgURL = getClass().getResource(path);
   		if (imgURL != null) { return new ImageIcon(imgURL, description); } 
   		else { System.err.println("Couldn't find file: " + path); return null; }
   	}
   
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		boolean done = false;
		for(int row=0; row < rows; row++)
	        for(int col=0; col<columns; col++)
	            if(ae.getSource() == buttonGrid[row][col])
	            {
	            	if (grid.newMove(row, col))
	            		count++;
	            	done = grid.checkMine(row, col);
	            	buttonGrid[row][col].setText(grid.getSymbol(row, col));
	            	buttonGrid[row][col].setBorder(BorderFactory.createBevelBorder(
	            			BevelBorder.LOWERED, Color.RED, Color.RED));
	            	break;
	            } 
		
		if(done)
		{
			//user hit a mine
			String message = "Game Over - Mine Encountered";
			Object[] options = {"Play Again", "Quit"};
			int option = JOptionPane.showOptionDialog(buttonGrid[rows-1][columns-1], message,
						"Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						logoIcon, options, options[0]);
			System.out.println(options[option]);
			
			System.exit(0);
		}
		else if(count >= (grid.cellCount()))
		{
			//user completed mine field without hitting a mine
			JOptionPane.showMessageDialog(buttonGrid[rows-1][columns-1], "Game Over - Winner", 
				"Game Over", JOptionPane.INFORMATION_MESSAGE, logoIcon);
			
			System.exit(0);
		}
	}        
}