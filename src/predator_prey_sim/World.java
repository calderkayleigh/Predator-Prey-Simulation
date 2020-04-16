package predator_prey_sim;

import util.Helper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;


public class World
{

	public int width, height;
	public static Color canvasColor;

	//create array lists of predators and prey
	static ArrayList<Predator> predators = new ArrayList();
	static ArrayList<Prey> prey = new ArrayList();

	/**
	 * @param w width of city
	 * @param h height of city
	 * @param numPrey number of prey
	 * @param numPredator number of predators
	 */
	public World(int w, int h, int numPrey, int numPredator)
	{
		width = w;
		height = h;
		canvasColor = Helper.newRandColor();
		
		// Add prey and predators
		populate(numPrey, numPredator);
	}

	Color getCanvasColor()
	{
		return canvasColor;
	}


	/**
	 * Generates numPrey random prey and numPredator random predators 
	 * distributed throughout the world.
	 * Prey must not be placed outside canvas!
	 *
	 * @param numPrey the number of prey to generate
	 * @param numPredator the number of predators to generate
	 */
	private void populate(int numPrey, int numPredator)
	{
		// Generates numPrey prey and numPredator predators 
		// randomly placed around the world.

		//add predators
		for(int i=0; i<numPredator; i++)
		{
			//add predator with given width and height
			predators.add(new Predator(width, height));
		}
		//add prey
		for(int i = 0; i<numPrey; i++)
		{
			//add prey with width, height, world (this), and a random color
			prey.add(new Prey(width, height, this, Helper.newRandColor()));
		}

	}
	
	/**
	 * Updates the state of the world for a time step.
	 */
	//update the world. Add and remove animals
	public void update()
	{
		// Move predators, prey, etc

		//create array lists for removing and adding predators and prey
		ArrayList<Animal> removeList = new ArrayList();
		ArrayList<Animal> addList = new ArrayList();

		//predators can reproduce, die, and move
		for(Predator p: predators)
		{
			//check if the predator died naturally
			if(p.dieNaturally())
			{
				removeList.add(p);
			}
			if(p.reproduce())
			{
				//predators create up to 3 offspring
				int numReproduced = Helper.nextInt(2)+1;
				//add the predators given the random generated number of offspring
				for(int i = 0; i<=numReproduced; i++)
				{
					//add predator with width and height
					addList.add(new Predator(width, height));
				}
			}
			//otherwise, move the predator
			else
			{
				p.move();
			}
		}

		for(Prey p: prey)
		{
			//if the prey is eaten, add it to the remove array list
			if(p.getEaten())
			{
				removeList.add(p);
			}
			if (p.dieNaturally())
			{
				removeList.add(p);
			}
			if(p.reproduce())
			{
				//determine if the prey mutates
				if(Helper.nextInt(99) <= p.getMutationChance())
				{
					//if the prey mutates, add a new prey with a different color
					addList.add(new Prey(width, height, this, Helper.newRandColor()));
				}
				//if the prey does not mutate, give the new prey the same color
				else
				{
					addList.add(new Prey(width, height, p.getAnimalX(), p.getAnimalY(), this));
				}
			}
			//otherwise, move
			else
			{
				p.move();
			}
		}

		//remove a specific type of animal
		for(Animal a: removeList)
		{
			//if the animal is a predator, remove the predator
			if(a instanceof Predator)
			{
				predators.remove(a);
			}
			//if the animal is a prey, remove the prey
			else if(a instanceof Prey)
			{
				prey.remove(a);
			}
		}

		//add a specific type of animal
		for(Animal a: addList)
		{
			//add a predator if it asks for a predator
			if(a instanceof Predator)
			{
				predators.add((Predator) a);
			}
			//otherwise, add prey
			else if(a instanceof Prey)
			{
				prey.add((Prey) a);
			}
		}


	}

	/**
	 * Draw all the predators and prey.
	 */
	void draw()
	{
		/* Clear the screen */
		PPSim.dp.clear(canvasColor);

		// Draw predators
		for(Predator p: predators)
		{
			PPSim.dp.drawSquare(p.getAnimalX(), p.getAnimalY(),p.getColor());
		}
		//draw prey
		for(Prey p: prey)
		{
			PPSim.dp.drawCircle(p.getAnimalX(), p.getAnimalY(), p.getColor());
		}
	}

	//change the background
	public void changeBackground()
	{
		//assign a random color
		canvasColor = Helper.newRandColor();
		//recheck if the prey's color matches the new background
		for(Prey p: prey)
		{
			p.checkIfColorIsSame(canvasColor);
		}

	}

	//add prey to the world for the mouse command and button
	void addPrey(int animalX, int animalY)
	{
		prey.add(new Prey(width, height, animalX, animalY, this));
	}
	//add predators for the button command
	void addPredators(int width, int height)
	{
		predators.add(new Predator(width, height));
	}

	//reset simulation
	void resetSimulation()
	{
		//create new array lists
		predators = new ArrayList();
		prey = new ArrayList();

		//populate the initial simulation with 10 prey and 5 predators
		populate(10,5);
	}
	//alternative simulation reset with less predators
	void resetSimulationAlternate()
	{
		predators = new ArrayList();
		prey = new ArrayList();

		Scanner scan = new Scanner(System.in);
		System.out.println("How many prey would you like in the simulation? Please enter a positive integer");
		int numPrey = scan.nextInt();
		System.out.println("How many predators would you like in the simulation? Please enter a positive integer");
		int numPredators = scan.nextInt();
		populate(numPrey, numPredators);
	}

}

