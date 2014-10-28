//Erick Lin, William Lynch, Kevin Moore, Ivan Gorbunov
//5-9-13
//Player.java
//Player
//Classes

import   java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player extends JPanel
{
	//fields
	private int playerNum;
	private int currentColumn;
	
	//constructor
	public Player(int number)
	{
		playerNum = number;
		currentColumn = 0;
	}
	
	//accessor methods
	public int getPlayerNum()
	{
		return playerNum;
	}
	
	public int getCurrentColumn()
	{
		return currentColumn;
	}
	
	
	//moves the selection to the column adjacent on the left
	public void moveLeft()
	{
		if (currentColumn > 0)
		{
			System.out.println("Move Left");
			currentColumn--;
		}
	}
	
	//moves the selection to the column adjacent on the right
	public void moveRight()
	{
		if (currentColumn < Connect4.game.getBoard().getCols()-1)
		{
			System.out.println("Move Right");
			currentColumn++;
		}
	}
	
	//creates a disc of the current player's color in the lowest empty space on the currently selected column
	public void dropDisc()
	{
		for (int row = Connect4.game.getBoard().getRows() - 1; row >= 0; row--)
		{
			//initializes a variable to the bottom empty square in the column
			Square currentSquare = Connect4.game.getBoard().getSquare(row, currentColumn);
			if (currentSquare.getDisc() == null)
			{
				Disc addedDisc = currentSquare.addDisc(playerNum);
				if (addedDisc != null && Connect4.game.checkWin(addedDisc))
				{
					System.out.println("Player " + Connect4.game.getPlayerTurn() + " Wins!");
					System.out.println("Press Enter to Restart");
					Connect4.game.setMessage("Player " + Connect4.game.getPlayerTurn() + " Wins!");
					return;
				}
				if (Connect4.game.checkTie())
				{
					System.out.println("It's a Draw");
					System.out.println("Press Enter to Restart");
					Connect4.game.setMessage("It's a Draw");
					return;
				}
				Connect4.game.switchPlayer();
				if (playerNum == 1 && Connect4.game.getNumPlayers() == 1)
				{
					Player cpu = Connect4.game.getCurrentPlayer();
					cpu.makeMove();
				}
				System.out.println("Disc Dropped");
				return;
			}
		}
		System.out.println("Can't Drop Here");
	}
	
	//cpu's move only
	public void makeMove()
	{
		int[] choices = new int[Connect4.game.getBoard().getCols()];
		int numChoices = 0;
		for (int col = 0; col < Connect4.game.getBoard().getCols(); col++)
			if (Connect4.game.getBoard().getSquare(0, col).getDisc() == null)
			{
				choices[numChoices] = col;
				numChoices++;
			}
		currentColumn = choices[(int)(Math.random() * numChoices)];
		dropDisc();
	}
}