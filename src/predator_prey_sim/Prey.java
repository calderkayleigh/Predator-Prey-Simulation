package predator_prey_sim;

import util.Helper;

import java.awt.*;

public class Prey extends Animal
{
    //declare needed variable for reproducing
    private int mutationChance;

    //class if a Prey is added with a new color
    public Prey(int width, int height, World w, Color c)
    {
        super(width, height);
        color = c;
        checkIfColorIsSame(w.getCanvasColor());
        visualDistance = 10;
        mutationChance = 10;
        reproductionRate = 10;
        turnRate = 10;
        eatenDistance = 1;
        moveDistance = 2;
    }

    //class if prey is added without a new color
    public Prey(int width, int height, int animalX, int animalY, World w)
    {
        super(width, height, animalX, animalY);
        color = Helper.newRandColor();
        checkIfColorIsSame(w.getCanvasColor());
        visualDistance = 10;
        mutationChance = 10;
        reproductionRate = 10;
        turnRate = 10;
        eatenDistance = 1;
        moveDistance = 2;
    }

    //compare the color of the prey and color of the canvas
    boolean checkIfColorIsSame(Color c)
    {
       if(color == c)
       {
           //System.out.println("Colors same");
           return true;
       }
        //System.out.println("Colors different");
       return false;
    }

    //get the chance of mutation
    int getMutationChance()
    {
        return mutationChance;
    }

    //move the prey
    public void move()
    {
        //declare a new boolean variable that says if the prey has already moved
        boolean alreadyMoved = false;

        //check if a predator is in sight distance
        for(Predator p: World.predators)
        {
            if(!alreadyMoved)
            {
                alreadyMoved = checkDistanceBetweenPreyAndPredators(p);
            }
        }
        //if the prey has moved, return
        if(alreadyMoved) {return;}
        //if the prey has not moved, move as a normal animal would
        else
        {
            super.move();
        }
    }

    //check the distance between the prey and predators for each possible direction
    private boolean checkDistanceBetweenPreyAndPredators(Predator p)
    {
        if(direction == 0)
        {
            //if the prey sees a predator, it runs in the opposite direction
            if(animalY - p.animalY < visualDistance && animalY + moveDistance < boundaryY  && animalY - p.animalY > eatenDistance)
            {
                //System.out.println("Prey runs away, initial north");
                //run away two squares
                animalY += moveDistance;

                //face the opposite direction
                direction = 2;

                return true;
            }
        }
        else if(direction == 1)
        {
            if(animalX - p.animalX <= visualDistance && animalX - moveDistance < 1  && animalX - p.animalX > eatenDistance)
            {
                //System.out.println("Prey runs away, initial west");
                //move away two squares
                animalX -=moveDistance;

                //move in the opposite direction
                direction = 3;

                return true;
            }
        }
        else if (direction == 2)
        {
            if(p.animalY - animalY <= visualDistance && animalY - moveDistance > 1 && p.animalY - animalY > eatenDistance)
            {
               // System.out.println("Prey runs away, initial south");
                //move away two squares
                animalY -=moveDistance;

                return true;
            }
        }
        else if(direction == 3)
        {
            if(p.animalX - animalX <= visualDistance && animalX + moveDistance < boundaryX && animalX - p.animalX > eatenDistance)
            {
                //System.out.println("Prey runs away, initial east");
                //move away two squares
                animalX +=moveDistance;

                return true;
            }
        }
        //if the prey does not see a predator, return false
        return false;
    }

}

