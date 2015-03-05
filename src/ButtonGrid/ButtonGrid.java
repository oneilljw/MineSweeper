package ButtonGrid;

import javax.swing.BorderFactory;
import javax.swing.JFrame; //imports JFrame library
import javax.swing.JButton; //imports JButton library
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout; //imports GridLayout library
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

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
        
    public ButtonGrid()
    { 
    	//constructor
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Rows: ");
        rows = scan.nextInt();
        System.out.print("Enter Columns: ");
        columns = scan.nextInt();
        	
        JFrame frame = new JFrame("MineSweeper");
//      frame.setLayout(new GridLayout(rows,columns)); //set layout
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(new GridLayout(rows,columns)); //set layout 
        contentPane.setBorder(BorderFactory.createBevelBorder(
        					BevelBorder.RAISED, Color.BLACK, Color.BLACK));
        
        buttonGrid = new JButton[rows][columns]; //allocate the size of grid
        grid = new Grid(rows, columns);
        count = 0;
              
        Dimension buttonSize = new Dimension(9*4,9*3);
        for(int row=0; row < rows; row++)
            for(int col=0; col<columns; col++)
            {
            	buttonGrid[row][col] = new JButton(grid.getSymbol(row, col)); //creates new button
//            	buttonGrid[row][col].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            	buttonGrid[row][col].setPreferredSize(buttonSize);
            	buttonGrid[row][col].addActionListener(this);
                contentPane.add(buttonGrid[row][col]); //adds button to grid      
            }
            
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //sets appropriate size for frame
        frame.setVisible(true); //makes frame visible 
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
	            			BevelBorder.LOWERED, Color.BLUE, Color.RED));
	            	break;
	            } 
		if(done)
		{
			JOptionPane.showMessageDialog(buttonGrid[rows-1][columns-1], "Game Over - Mine Encountered", 
					"Game Over", JOptionPane.ERROR_MESSAGE);
			
			System.exit(0);
		}
		else if(count >= (grid.cellCount()))
		{
			JOptionPane.showMessageDialog(buttonGrid[rows-1][columns-1], "Game Over - Winner", 
				"Game Over", JOptionPane.INFORMATION_MESSAGE);
			
			System.exit(0);
		}
	}        
}