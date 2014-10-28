//Erick Lin, William Lynch, Kevin Moore, Ivan Gorbunov
//5-9-13
//Game.java
//Game
//Oversees all actions

import java.awt.*;
import javax.swing.*;

public class Game
{
	//global fields
	private int numPlayers;
	private Player[] p = new Player[3];
	private Board board;
	private int playerTurn;
	private boolean gameOver;
	private String message;
	private int[] score = new int[3];
	
	//constructor
	public Game(int players)
	{
		numPlayers = players;
		playerTurn = 1;					//which player's turn it is
		p[1] = new Player(1);
		p[2] = new Player(2);
		board = new Board(6, 7);	//initializes board with 6 rows and 7 columns
		gameOver = false;
		message = "Player Turn: " + playerTurn;
		score[0] = 0;
		score[1] = 0;
		score[2] = 0;
	}
	
	//drawing method
	public void paint(Graphics g)
	{
		if (playerTurn == 1)
			g.setColor(Color.RED);
		if (playerTurn == 2)
			g.setColor(Color.BLUE);
		//draws whose turn it is or who won
		g.drawString(message, 120, 460);
		g.setColor(Color.BLACK);
		if (gameOver)
			g.drawString("Press <Enter> to Restart", 55, 500);
		board.paint(g);
	}
	
	//accessor methods
	public int getNumPlayers()
	{
		return numPlayers;
	}
	
	public int getPlayerTurn()
	{
		return playerTurn;
	}
	
	public Player getCurrentPlayer()
	{
		return p[playerTurn];
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public int getScore(int scoreNum)
	{
		return score[scoreNum];
	}
	
	public boolean isGameOver()
	{
		return gameOver;
	}
	
	//modifier methods
	public void setMessage(String msg)
	{
		message = msg;
	}
	
	public void switchPlayer()			//switches the player whose turn it is
	{
		if (playerTurn == 1)
			playerTurn = 2;
		else
			playerTurn = 1;
		System.out.println("Player Turn: " + playerTurn);
		setMessage("Player Turn: " + playerTurn);
	}
	
	//checks whether the board has completely filled up
	public boolean checkTie()
	{
		for (int col = 0; col < getBoard().getCols(); col++)
			if (getBoard().getSquare(0, col).getDisc() == null)
				return false;
		//it's a draw
		score[0]++;
		gameOver = true;
		return true;
	}
	
	//checks whether one player has won
	public boolean checkWin(Disc disc)
	{
		//initializes the row and column of the Disc parameter
		int row = disc.getRow();
		int col = disc.getCol();
		//checks whether there are four adjacent Discs in any possible orientation
		if (checkVertical(disc) || checkHorizontal(disc) || checkDiagonal1(disc) || checkDiagonal2(disc))
		{
			score[getPlayerTurn()]++;
			System.out.println("Player " + getPlayerTurn() + " Score: " + score[getPlayerTurn()]);
			//one player wins
			gameOver = true;
			return true;
		}
		return false;
	}
	
	//helper methods for checkWin
	private boolean checkVertical(Disc disc)
	{
		//initialize variables
		int start = disc.getRow() + 3, end = disc.getRow() - 3, col = disc.getCol(), count = 0;
		Color color = disc.getColor();
		//prevent out-of-bounds error
		if (start > getBoard().getRows() - 1)
			start = getBoard().getRows() - 1;
		if (end < 0)
			end = 0;
		//loops through to find any possible four-Disc vertical combinations containing the Disc passed as a parameter
		for (int row = start; row >= end; row--)
		{
			Disc currentDisc = getBoard().getSquare(row, col).getDisc();
			if (currentDisc != null && color.equals(currentDisc.getColor()))
			{
				count++;
				if (count >= 4)
					return true;
			}
			else
				count = 0;
		}
		return false;
	}
	
	private boolean checkHorizontal(Disc disc)
	{
		int start = disc.getCol() - 3, end = disc.getCol() + 3, row = disc.getRow(), count = 0;
		Color color = disc.getColor();
		if (start < 0)
			start = 0;
		if (end > getBoard().getCols() - 1)
			end = getBoard().getCols() - 1;
		for (int col = start; col <= end; col++)
		{
			Disc currentDisc = getBoard().getSquare(row, col).getDisc();
			if (currentDisc != null && color.equals(currentDisc.getColor()))
			{
				count++;
				if (count >= 4)
					return true;
			}
			else
				count = 0;
		}
		return false;
	}
	
	private boolean checkDiagonal1(Disc disc)
	{
		int count = 0;
		Color color = disc.getColor();
		for (int sq = 0; sq < 4; sq++)
		{
			if (disc.getRow() - sq < 0 || disc.getCol() - sq < 0)
				break;
			Disc currentDisc = getBoard().getSquare(disc.getRow() - sq, disc.getCol() - sq).getDisc();
			if (currentDisc != null && color.equals(currentDisc.getColor()))
			{
				count++;
				if (count >= 4)
					return true;
			}
			else
				break;
		}
		for (int sq = 1; sq < 4; sq++)
		{
			if (disc.getRow() + sq > getBoard().getRows() - 1 || disc.getCol() + sq > getBoard().getCols() - 1)
				return false;
			Disc currentDisc = getBoard().getSquare(disc.getRow() + sq, disc.getCol() + sq).getDisc();
			if (currentDisc != null && color.equals(currentDisc.getColor()))
			{
				count++;
				if (count >= 4)
					return true;
			}
			else
				return false;
		}
		return false;
	}
	
	private boolean checkDiagonal2(Disc disc)
	{
		int count = 0;
		Color color = disc.getColor();
		for (int sq = 0; sq < 4; sq++)
		{
			if (disc.getRow() + sq > getBoard().getRows() - 1 || disc.getCol() - sq < 0)
				break;
			Disc currentDisc = getBoard().getSquare(disc.getRow() + sq, disc.getCol() - sq).getDisc();
			if (currentDisc != null && color.equals(currentDisc.getColor()))
			{
				count++;
				if (count >= 4)
					return true;
			}
			else
				break;
		}
		for (int sq = 1; sq < 4; sq++)
		{
			if (disc.getRow() - sq < 0 || disc.getCol() + sq > getBoard().getCols() - 1)
				return false;
			Disc currentDisc = getBoard().getSquare(disc.getRow() - sq, disc.getCol() + sq).getDisc();
			if (currentDisc != null && color.equals(currentDisc.getColor()))
			{
				count++;
				if (count >= 4)
					return true;
			}
			else
				return false;
		}
		return false;
	}
	
	//restarts the game for another round
	public void clearGame()
	{
		playerTurn = 1;
		setMessage("Player Turn: " + playerTurn);
		board = new Board(6, 7);
		gameOver = false;
	}
}