package predator_prey_sim;

import util.Helper;

import java.awt.*;

public class Prey extends Animal
{
    public int mutationChance;
    public int runAwaySquares = 2;
    int eatenDistance = 1;

    public Prey(int width, int height, World w, Color c)
    {
        super(width, height);
        color = c;
        checkIfColorIsSame(w.getCanvasColor());
        visualDistance = 10;
        mutationChance = 10;
        reproductionRate = 20;
        turnRate = 10;
    }


    public Prey(int width, int height, int animalX, int animalY, World w)
    {
        super(width, height, animalX, animalY);
        color = Helper.newRandColor();
        checkIfColorIsSame(w.getCanvasColor());
        visualDistance = 10;
        mutationChance = 10;
        reproductionRate = 5;
        turnRate = 10;
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

    int getMutationChance()
    {
        return mutationChance;
    }
    public void move()
    {
        boolean alreadyMoved = false;

        for(Predator p: World.predators)
        {
            if(!alreadyMoved)
            {
                alreadyMoved = checkDistanceBetweenPreyAndPredators(p);
            }
        }
        if(alreadyMoved) {return;}
        super.move();
    }

    private boolean checkDistanceBetweenPreyAndPredators(Predator p)
    {
        if(direction == 0)
        {
            //if the prey sees a predator, it runs in the opposite direction
            if(animalY - p.animalY < visualDistance && animalY + runAwaySquares < boundaryY  && animalY - p.animalY > eatenDistance)
            {
                //System.out.println("Prey runs away, initial north");
                //run away two squares
                animalY += runAwaySquares;

                //face the opposite direction
                direction = 2;

                return true;
            }
        }
        else if(direction == 1)
        {
            if(animalX - p.animalX <= visualDistance && animalX - runAwaySquares < 1  && animalX - p.animalX > eatenDistance)
            {
                //System.out.println("Prey runs away, initial west");
                //move away two squares
                animalX -=runAwaySquares;

                //move in the opposite direction
                direction = 3;

                return true;
            }
        }
        else if (direction == 2)
        {
            if(p.animalY - animalY <= visualDistance && animalY - runAwaySquares > 1 && p.animalY - animalY > eatenDistance)
            {
               // System.out.println("Prey runs away, initial south");
                //move away two squares
                animalY -=runAwaySquares;

                return true;
            }
        }
        else if(direction == 3)
        {
            if(p.animalX - animalX <= visualDistance && animalX + runAwaySquares < boundaryX && animalX - p.animalX > eatenDistance)
            {
                //System.out.println("Prey runs away, initial east");
                //move away two squares
                animalX +=runAwaySquares;

                return true;
            }
        }
        return false;
    }

}
