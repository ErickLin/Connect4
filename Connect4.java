//Erick Lin, William Lynch, Kevin Moore, Ivan Gorbunov
//5-9-13
//Connect4.java
//Connect4
//Starts up applet

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Connect4 extends JApplet implements KeyListener
{
	//fields
	public static Game game;
	private static int startKey, leftKey, rightKey, actionKey, restartKey, exitKey;
	private boolean gameStarted;
	//the selected number of players
	private int playersSelect;
	public Font titleFont, font, scoreFont;
	
	//initialize starting variables
	public void init()
	{
            setFocusable(true);
            requestFocusInWindow();
            getContentPane().setBackground(Color.WHITE);
            getContentPane().setFocusable(false);
            startKey = KeyEvent.VK_ENTER;
            leftKey = KeyEvent.VK_LEFT;
            rightKey = KeyEvent.VK_RIGHT;
            actionKey = KeyEvent.VK_DOWN;
            restartKey = KeyEvent.VK_ENTER;
            exitKey = KeyEvent.VK_ESCAPE;
            addKeyListener(this);
            titleFont = new Font("Verdana", Font.BOLD, 32);
            font = new Font("Verdana", Font.BOLD, 24);
            scoreFont = new Font("Verdana", Font.BOLD, 18);
            playersSelect = 2;
	}
	
	public void paint(Graphics g)
	{
		//clear background
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		//if game has not started
		if (game == null)
		{
			for(int x = 0; x <= 448; x += 56)
      		{	
      			for(int y = 0; y <= 672; y += 56)
      			{
      				g.setColor(Color.cyan);
      				g.fillRect(x,y,56,56);
      				g.setColor(Color.green);
      				g.drawRect(x,y,56,56);
      				g.setColor(Color.orange);
      				g.fillOval(x+1,y+1,54,54);
      				g.setColor(Color.pink);
      				g.fillOval(x+10,y+10,36,36);
      				g.setColor(Color.red);
      				g.drawOval(x+8,y+8,40,40);
      				g.setColor(Color.yellow);
      				g.fillOval(x+16,y+16,24,24);
      			}
			}
			g.setColor(Color.BLACK);
			g.setFont(titleFont);
			g.drawString("Connect Four", 85, 50);
			g.setFont(font);
			g.drawString("Press the left and right arrow", 30, 150);
			g.drawString("keys to move", 125, 180);
			g.drawString("Press the down arrow key to", 30, 230);
			g.drawString("drop a disc", 130, 260);
			if (playersSelect == 1)
				g.drawString("1 Player >", 155, 310);
			if (playersSelect == 2)
				g.drawString("< 2 Players", 140, 310);
			g.drawString("Press <Enter> to start!", 75, 360);
			g.drawString("by: Erick Lin", 120, 525);
			g.drawString("Ivan Gorbunov", 120, 550);
			g.drawString("Kevin Moore", 120, 575);
			g.drawString("William Lynch", 120, 600);
			
		}
		else	//if game has started
		{
			g.setFont(font);
			game.paint(g);
			g.setFont(scoreFont);
			g.setColor(Color.RED);
			g.drawString("Player 1 score: " + game.getScore(1), 125, 560);
			g.setColor(Color.BLUE);
			g.drawString("Player 2 score: " + game.getScore(2), 125, 580);
			g.setColor(Color.BLACK);
			g.drawString("Draws: " + game.getScore(0), 160, 600);
		}
	}
	
	//accessor methods
	public int getLeftKey()
	{
		return leftKey;
	}
	
	public int getRightKey()
	{
		return rightKey;
	}
	
	public int getActionKey()
	{
		return actionKey;
	}
	
	public int getRestartKey()
	{
		return restartKey;
	}
	
	//KeyListener methods
	public void keyPressed(KeyEvent e)
	{
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		//on main menu
		if (!gameStarted)
		{
			if (key == leftKey)
			{
				if (playersSelect != 1)
					playersSelect = 1;
			}
			if (key == rightKey)
			{
				if (playersSelect != 2)
					playersSelect = 2;
			}
			if (key == startKey)
			{
				game = new Game(playersSelect);
				gameStarted = true;
			}
		}
		//during game
		else
		{
			if (!game.isGameOver())
			{
				Player player = game.getCurrentPlayer();
				if (key == leftKey)
				{
					player.moveLeft();
				}
				if (key == rightKey)
				{
					player.moveRight();
				}
				if (key == actionKey)
				{
					player.dropDisc();
				}
			}
			else if (key == restartKey)
				game.clearGame();
			//restarts the game
			if (key == exitKey)
			{
				game = null;
				gameStarted = false;
			}
		}
		repaint();
	}
	
	public void keyTyped(KeyEvent e)
	{
	}
}