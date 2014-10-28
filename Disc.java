//Erick Lin, William Lynch, Kevin Moore, Ivan Gorbunov
//5-9-13
//Disc.java
//Disc
//The pieces that each player drops on the board

import java.awt.*;
import javax.swing.*;

public abstract class Disc extends JPanel 
{
	protected int row, col;
	protected Color clr;
	private final int DIAMETER = 56;
	private int squareLength;
	private int margin;
	
	//constructs a disk at a specific position
	public Disc(int r, int c, int sL)
	{
		row = r;
		col = c;
		squareLength = sL;
		margin = (sL - DIAMETER) / 2;
	}
	
	public void paint(Graphics g)
	{
		//draws the Disc
		g.setColor(clr);
		g.fillOval(getCol() * squareLength + margin, getRow() * squareLength + margin, DIAMETER, DIAMETER);
		//draws the Disc's color
		g.setColor(Color.BLACK);
		g.drawOval(getCol() * squareLength + margin, getRow() * squareLength + margin, DIAMETER, DIAMETER);
	}
	
	//accessor methods
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public Color getColor()
	{
		return clr;
	}
}