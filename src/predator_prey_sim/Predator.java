package predator_prey_sim;

import util.Helper;

import java.awt.*;
import java.util.ArrayList;

public class Predator extends Animal
{
    ArrayList<Predator> predators = new ArrayList();

    public Predator (int width, int height)
    {
        //get the width and height from the parent class
        super(width, height);
        //list out the functions of predators
        color = Color.RED;
        turnRate = 5;
        deathRate = 5;
        reproductionRate = 1;
        visualDistance = 15;
    }
    //check to see if the predator is alive
    boolean checkAlive()
    {
        return super.checkAlive();
    }

    //check to see if the predator will reproduce
    boolean reproduce()
    {
        return super.reproduce();
    }

    public void changeDirection()
    {
        //determine if the predator will change direction
        if(Helper.nextInt(99)> turnRate)
        {
            //change direction
            super.changeDirection();
        }

    }

    //update predator position
    //directions are up, left, down, and right
    boolean move()
    {
        for(Predator p: predators)
        {
            if(direction == 0 && animalY + 1 < boundaryY)
            {
                animalY +=1;
                return true;
            }
            else if(direction == 1 && animalX -1 > boundaryX)
            {
                animalX -=1;
                return true;
            }
            else if(direction == 2 && animalY -1 > boundaryY)
            {
                    animalY -=1;
                    return true;

            }
            else if(direction ==3 && animalX +1 < boundaryX)
            {
                    animalX+=1;
                    return true;
            }
            System.out.println("The predator did not move");
            return false;
        }

        return false;
    }

}
