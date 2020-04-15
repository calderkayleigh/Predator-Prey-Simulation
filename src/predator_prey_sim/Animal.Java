package predator_prey_sim;

import util.Helper;

import java.awt.*;

public class Animal
{

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

    //define if an animal has been eaten. This is either true or false
    public boolean eaten;

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

        direction = Helper.nextInt(3);
    }

    Animal(int width, int height, int xBound, int yBound)
    {
        boundaryX = width;
        boundaryY = height;
        animalX = xBound;
        animalY = yBound;
        direction = Helper.nextInt(3);
    }


    //get animal color and return that color
    Color getColor()
    {
        return color;
    }
    //if animal is consumed, declare that it has been consumed
    void eaten()
    {
        eaten = true;
    }
    //return a boolean value when a prey is eaten
    boolean getEaten()
    {
        return eaten;
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

    //allow the animals to move
    //high boundary = west, low boundary = east
    //high boundary = south, low boundary = north
    public void move()
    {
        //check to see if the animal will change direction. If so, change direction.
        if(Helper.nextInt(99)< turnRate)
        {
            changeDirection();
        }
        //if direction is north
        if(direction == 0)
        {
            //check to see if the animal is close to the boundary
            if(animalY - 1 == 1)
            {
                //if the animal is close to the boundary, make the animal move in the opposite direction
                direction = 2;
            }
            else
            {
                //Otherwise move north 1 square
                animalY -=1;
            }
        }
        //if direction is west
        else if(direction ==1)
        {
            //check to see if the animal is close to the boundary
            if (animalX + 1 == boundaryX - 1)
            {
                //move in the opposite direction
                direction = 3;
            }
            else
            {
                //otherwise move west 1 square
                animalX += 1;
            }
        }
        //if direction is south
        else if(direction == 2)
        {
            //check to see if the animal is close to the boundary
            if(animalY + 1 == boundaryY - 1)
            {
                //move in the opposite direction
                direction = 0;
            }
            else
            {
                //otherwise move one square south.
                animalY += 1;
            }
        }
        //if direction is east
        else if(direction == 3)
        {
            //check to see if the animal is close to the boundary
            if(animalX - 1 == 1)
            {
                //move in the opposite direction
                direction = 1;
            }
            else
            {
                //otherwise move east 1 square
                animalY -= 1;
            }
        }
    }

    //return if the animal will reproduce or not
    boolean reproduce()
    {
        //check to see if the animal reproduces. Use a random generator
        return Helper.nextInt(99)< reproductionRate;
    }

    boolean dieNaturally()
    {
        //check to see if the animal dies
        return Helper.nextInt(99) < deathRate;
    }

}
