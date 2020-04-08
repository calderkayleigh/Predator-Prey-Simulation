package predator_prey_sim;

import util.Helper;

import java.awt.*;

public class Animal
{
    //direction corresponds to up, left, down, right

    //declare integers given in the problem
    //define given rates
    int reproductionRate;
    int turnRate;
    int visualDistance;
    int deathRate;

    //define moving characteristics such as direction, bounds, and animal location
    int direction;
    int animalX;
    int animalY;
    int boundaryX;
    int boundaryY;

    //define color
    Color color;

    Animal(int width, int height)
    {
        //sets animalX to be a random value within the horizontal world boundary
        //we have to add a 1 to all because the random generator could select 0
        animalX = Helper.nextInt(width-2)+1;
        
        //sets animalY to be a random value within the vertical world boundary
        animalY = Helper.nextInt(height-2)+1;
        
        //sets world X boundary equal to the given width
        boundaryX = width;
        
        //sets world Y boundary equal to the given height
        boundaryY = height;
    }


    //get animal color and return that color
    Color getColor()
    {
        return color;
    }
    
    //get the X position of the animal
    int getAnimalX()
    {
        return animalX;
    }
    //get the Y position of the animal
    int getAnimalY()
    {
        return animalY;
    }
    int getReproductionRate()
    {
        return reproductionRate;
    }
    int getTurnRate()
    {
        return turnRate;
    }
    int getVisualDistance()
    {
        return visualDistance;
    }
    int getDirection()
    {
        return direction;
    }

    //change the direction of the animal
    public void changeDirection()
    {
        //randomly generate a direction
        //direction corresponds to up, left, down, right
        int newDirection = Helper.nextInt(3);

        //change the direction of the animal
        if(newDirection == direction)
        {
            changeDirection();
        }
        else
        {
            direction = newDirection;
        }
    }


    //return if the animal is alive or deceased
    boolean checkAlive()
    {
        //helper.nextInt will output a random number from 0-100. Death rate = 5, so there is a 5% chance of this being false.
        return Helper.nextInt(99)> deathRate;
    }
    //return if the animal will reproduce or not
    boolean reproduce()
    {
        //check to see if the predator reproduces. Use a random generator
        return Helper.nextInt(99)> reproductionRate;
    }
}
