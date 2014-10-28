//Erick Lin, William Lynch, Kevin Moore, Ivan Gorbunov
//5-9-13
//Board.java
//Board
//The game board, which is represented by a two-dimensional array

import java.awt.*;
import javax.swing.*;

public class Board extends JPanel
{
	private Square[][] squares;
	
	//initializes new board
	public Board(int numRows, int numCols)
	{
		squares = new Square[numRows][numCols];
		for (int row = 0; row < numRows; row++)
			for (int col = 0; col < numCols; col++)
			{
				squares[row][col] = new Square(row, col);
			}
	}
	
	//drawing method
	public void paint(Graphics g)
	{
		for (int row = 0; row < squares.length; row++)
			for (int col = 0; col < squares[0].length; col++)
				squares[row][col].paint(g);
	}
	
	//accessor methods
	public int getRows()
	{
		return squares.length;
	}
	
	public int getCols()
	{
		return squares[0].length;
	}
	
	public Square getSquare(int row, int col)
	{
		return squares[row][col];
	}
}