package predator_prey_sim;

import util.DotPanel;
import util.Helper;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


/*
 * You must add a way to represent predators.  When there is no prey, predators
 * should follow these simple rules:
 * 		if (1 in 5 chance):
 * 			turn to face a random direction (up/down/left/right)
 * 		Move in the current direction one space unless stepping out of the world.
 *
 * We will add additional rules for dealing with sighting or running into prey later.
 */

public class PPSim extends JFrame implements KeyListener, MouseListener {

	/* Create our city */
	World ppWorld = new World(MAX_X, MAX_Y, NUM_PREY, NUM_PREDATORS);

	private static final long serialVersionUID = -5176170979783243427L;

	/** The Dot Panel object you will draw to */
	protected static DotPanel dp;

	/* Define constants using static final variables */
	public static final int MAX_X = 100;
	public static final int MAX_Y = 100;
	public static final int DOT_SIZE = 6;
	private static final int NUM_PREY = 10;
	private static final int NUM_PREDATORS = 5;



	/*
	 * This fills the frame with a "DotPanel", a type of drawing canvas that
	 * allows you to easily draw squares for predators and circles for prey
	 * to the screen.
	 */
	public PPSim() {
		this.setSize(MAX_X * DOT_SIZE, MAX_Y * DOT_SIZE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Predator Prey World");

		/* Create and set the size of the panel */
		dp = new DotPanel(MAX_X, MAX_Y, DOT_SIZE);

		/* Add the panel to the frame */
		Container cPane = this.getContentPane();
		cPane.add(dp);

		/* Initialize the DotPanel canvas:
		 * You CANNOT draw to the panel BEFORE this code is called.
		 * You CANNOT add new widgets to the frame AFTER this is called.
		 */
		this.pack();
		dp.init();
		dp.clear();
		dp.setPenColor(Color.red);
		//set the window to be visible
		this.setVisible(true);

		//add key and mouse listeners for the keyboard and mouse commands
		this.addKeyListener(this);
		dp.addMouseListener(this);


		/* This is the Run Loop (aka "simulation loop" or "game loop")
		 * It will loop forever, first updating the state of the world
		 * (e.g., having predators take a single step) and then it will
		 * draw the newly updated simulation. Since we don't want
		 * the simulation to run too fast for us to see, it will sleep
		 * after repainting the screen. Currently it sleeps for
		 * 33 milliseconds, so the program will update at about 30 frames
		 * per second.
		 */
		while(true)
		{
			// Run update rules for world and everything in it
			ppWorld.update();
			// Draw to screen and then refresh
			ppWorld.draw();
			dp.repaintAndSleep(500);

		}
	}


	/**Key Commands*/

	//declare keyCommands. Invoked when a key has been pressed
	public void keyPressed(KeyEvent e)
	{
		//if space is pressed, change the canvas
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			ppWorld.changeBackground();
		}

		//if enter is pressed, reset the simulation
		if(e.getKeyCode() == KeyEvent.VK_ENTER);
		{
			ppWorld.resetSimulation();

		}
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
			ppWorld.resetSimulationAlternate();
		}
	}

	//invoked when the key has been released
	public void keyReleased(KeyEvent e) {}
	//invoked when the key has been typed
	public void keyTyped(KeyEvent e) {}

	/**Mouse Commands*/

	//invoked when a mouse has been pressed and released. Add prey to that location.
	public void mouseClicked(MouseEvent e)
	{
		ppWorld.addPrey(e.getX() / DOT_SIZE, e.getY() / DOT_SIZE );
	}

	//invoked when a mouse has been pressed on a component
	public void mousePressed(MouseEvent e) {}
	//invoked when a mouse has been released on a component
	public void mouseReleased(MouseEvent e) {}
	//invoked when a mouse has enters a component
	public void mouseEntered(MouseEvent e) {}
	//invoked when a mouse exits a component
	public void mouseExited(MouseEvent e) {}


	public static void main(String[] args)
	{
		//Set the seed
		//Helper.setSeed(50);
		/* Create a new GUI window  */
		new PPSim();
	}

}
