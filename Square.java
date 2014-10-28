//Erick Lin, William Lynch, Kevin Moore, Ivan Gorbunov
//5-10-13
//Square.java
//Square
//Each location on the board

import java.awt.*;
import javax.swing.*;

//class that makes up Board and holds Disc
public class Square extends JPanel
{
	private int row, col;
	private Disc disc;
	private final int SIDE_LENGTH = 64;
	
	public Square(int r, int c)
	{
		row = r;
		col = c;
		disc = null;
	}
	
	//drawing method
	public void paint(Graphics g)
	{
		if (col == Connect4.game.getCurrentPlayer().getCurrentColumn())
		{
			g.setColor(Color.ORANGE);
			g.fillRect(getCol() * SIDE_LENGTH, getRow() * SIDE_LENGTH, SIDE_LENGTH, SIDE_LENGTH);
		}
		g.setColor(Color.BLACK);
		g.drawRect(getCol() * SIDE_LENGTH, getRow() * SIDE_LENGTH, SIDE_LENGTH, SIDE_LENGTH);
		if (disc != null)
			disc.paint(g);
	}
	
	//accessors
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public Disc getDisc()
	{
		return disc;
	}
	
	public int getSideLength()
	{
		return SIDE_LENGTH;
	}
	
	//adds a disc of the current player's color if none exists
	public Disc addDisc(int playerTurn)
	{
		if (disc == null)
		{
			if (playerTurn == 1)
				disc = new Disc1(getRow(), getCol(), SIDE_LENGTH);
			if (playerTurn == 2)
				disc = new Disc2(getRow(), getCol(), SIDE_LENGTH);
			return disc;
		}
		return null;
	}
}