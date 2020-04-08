package predator_prey_sim;

import util.Helper;

import java.awt.*;
import java.util.ArrayList;


public class World {

	private int width, height;
	private Color canavasColor;
	
	//create array lists of predators and prey
	ArrayList<Predator> predators = new ArrayList();
	ArrayList<Prey> prey = new ArrayList();
	

	/**
	 * @param w width of city
	 * @param h height of city
	 * @param numPrey number of prey
	 * @param numPredator number of predators
	 */
	public World(int w, int h, int numPrey, int numPredator) {
		width = w;
		height = h;
		canavasColor = Helper.newRandColor();
		
		// Add Prey and Predators to the world.
		populate(numPrey, numPredator);
	}


	/**
	 * Generates numPrey random prey and numPredator random predators 
	 * distributed throughout the world.
	 * Prey must not be placed outside canavas!
	 *
	 * @param numPrey the number of prey to generate
	 * @param numPredator the number of predators to generate
	 */
	private void populate(int numPrey, int numPredators)
	{
		// Generates numPrey prey and numPredator predators 
		// randomly placed around the world.
		
		//add predators
		for(int i=0; i<numPredator; i++)
		{
			predators.add(new Predator(width, height));
		}
		//add prey
		/*
		for(int i = 0; i<numPrey; i++)
		{
			prey.add(new Prey(width, height, this));
		}
		*/
		
	}
	
	/**
	 * Updates the state of the world for a time step.
	 */
	public void update() 
	{
		// Move predators, prey, etc
		
		//create array lists for removing and adding predators and prey
		ArrayList<Animal> removeList = new ArrayList();
		ArrayList<Animal> addList = new ArrayList();
		
		//predators can reproduce, die, and move
		for(Predator p: predators)
		{
			if(p.reproduce())
			{
				//create up to 3 offspring
				int numReproduced = Helper.nextInt(2)+1;
				//add the predators given the random generated number of offspring
				for(int i = 0; i<=numReproduced; i++)
				{
					//add predator with width and height
					addList.add(new Predator(width, height));
				}
			}
			//check if the predator is alive
			if(p.checkAlive())
			{
				removeList.add(p);
			}
			//move the predator
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
			/*else if(a instanceof Prey)
			{
				prey.remove(a);
			}
			
			//if the animal is not one of these options, there is an error
			else
			{
				System.out.println("Error in the code");
			}
			*/
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
			/*else if (a instanceof Prey)
			{
				prey.add((Prey) a);
			}
			//since we have not declared any other animal types, something other than a prey or predator will be an error
			else
			{
				System.out.println("Error with code");
			}
			*/
		}

	}

	/**
	 * Draw all the predators and prey.
	 */
	public void draw()
	{
		/* Clear the screen */
		PPSim.dp.clear(canavasColor);
		
		// Draw predators and pray
		
		// Draw predators
		for(Predator p: predators)
		{
			PPSim.dp.drawSquare(p.getAnimalX(), p.getAnimalY(),p.getColor());
		}
	}



}
