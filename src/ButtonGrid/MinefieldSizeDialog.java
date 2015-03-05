package ButtonGrid;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MinefieldSizeDialog extends JDialog implements ActionListener
{
	/**
	 * This class gets the size of the mine sweeper grid from the user.
	 * @author Andrew O'Neill
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_GRID_SIZE = "10"; //10 x 10 Minesweeper grid
	
	private JComboBox rowsCB, columnsCB;
	JButton btnPlay;
	private Point size;	//holds size of MineSweeper grid 

	MinefieldSizeDialog(JFrame owner)
	{
		super(owner, true);	//modal dialog
		this.setTitle("MineSweeper");
		this.getContentPane().setLayout(new GridLayout(3, 1)); 
		
		//set up dialog user interface
		
		//create a top panel holding instruction to user
		JPanel instPanel = new JPanel();
		JLabel lblInstruction = new JLabel("<html><i>Please select the number of rows<br>" +
											" and columns for the gameboard:</i></html>");
		instPanel.add(lblInstruction);
		
		//set up a panel for the row and column size selections and set default choices
		JPanel cbPanel = new JPanel();
		String[] sizes = {"4", "5", "6", "7", "8", "9", "10",
						  "11", "12", "13", "14", "15", "16" ,"17", "18", "19", "20",
						  "21", "22", "23", "24", "25", "26" ,"27", "28", "29", "30"};
		rowsCB = new JComboBox(sizes);
		rowsCB.setSelectedItem(DEFAULT_GRID_SIZE);
		columnsCB = new JComboBox(sizes);
		columnsCB.setSelectedItem(DEFAULT_GRID_SIZE);
		
		cbPanel.add(new JLabel("Rows:"));
		cbPanel.add(rowsCB);
		cbPanel.add(new JLabel("Columns:"));
		cbPanel.add(columnsCB);
		
		//set up a panel for the Play button
		JPanel cntlPanel = new JPanel();
		btnPlay = new JButton("Play");
		btnPlay.addActionListener(this);
		cntlPanel.add(btnPlay);
		
		//add the panels to the dialog
		this.add(instPanel);
		this.add(cbPanel);
		this.add(cntlPanel);
		
		pack();
	}
	
	/***
	 * Shows the dialog. setVisible blocks until user clicks Play button or 
	 * exits the dialog. By exiting, a Point(0,0) is returned
	 * @return A Point object, x value is # of rows, y value is # of columns
	 */
	Point showDialog()
	{
		size = new Point(0, 0);	//returned if user exits the dialog vice clicking "Play"
		this.setVisible(true);
		return size;
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource() == btnPlay)
		{
			//set the size object x value to the # of rows and the y object
			//to the number of columns selected by user prior to clicking "Play"
			//then close the dialog
			size.x = Integer.parseInt((String) rowsCB.getSelectedItem());
			size.y = Integer.parseInt((String) columnsCB.getSelectedItem());
			this.dispose();
		}
	}
}
