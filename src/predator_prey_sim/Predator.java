package predator_prey_sim;

import java.awt.*;

public class Predator extends Animal
{

    public Predator (int width, int height)
    {
        //get the width and height from the parent class
        super(width, height);
        //list out the functions of predators
        color = Color.RED;
        turnRate = 5;
        deathRate = 1;
        reproductionRate = 1;
        visualDistance = 15;
        moveDistance = 1;
        eatenDistance = 1;
    }

    //update predator position
    public void move()
    {
        //System.out.println("entering predator move class");
        //need to define a boolean to determine if a predator has already been moved
        boolean alreadyMoved = false;
        for(Prey p: World.prey)
        {
            //eat prey if it does not blend into the canvas
            if(!p.checkIfColorIsSame(World.canvasColor))
            {
                //System.out.println("entering predator move class and colors are different");
                //if the distance between a predator and prey is less than 1, eat prey
                if(Math.abs(animalX - p.animalX) <= eatenDistance || Math.abs(animalY - p.animalY) <= eatenDistance)
                {
                   // System.out.println("Prey was eaten");
                    //eat prey
                    p.eaten();
                }
            }
            //if the predator is not busy eating prey, check if there is a prey in sight
            if(!alreadyMoved && !p.getEaten())
            {
                alreadyMoved = checkDistanceBetweenPredatorsAndPrey(p);
            }
        }
        if(alreadyMoved){return;}
        else
        {
            //System.out.println("Predator moves normally");
            //move the predator normally
            super.move();
        }
    }

    public boolean checkDistanceBetweenPredatorsAndPrey(Prey p)
    {
        //check the distance if the prey does not blend into the canvas for each direction
        if(!p.checkIfColorIsSame(World.canvasColor))
        {
            if(direction == 0)
            {
                if(animalY - p.animalY <= visualDistance && animalY - moveDistance >1 && animalY - p.animalY > eatenDistance)
                {
                    //System.out.println("Predator moves towards prey north");
                    animalY -=moveDistance;
                    return true;
                }
            }
            else if(direction == 1)
            {
                if(p.animalX - animalX <= visualDistance && animalX + moveDistance < boundaryX && p.animalX - animalX > eatenDistance)
                {
                    //System.out.println("Predator moves towards prey west");
                    animalX +=moveDistance;
                    return true;
                }
            }
            else if(direction == 2)
            {
                if(p.animalY - animalY <= visualDistance && animalY + moveDistance < boundaryY && p.animalY - animalY > eatenDistance)
                {
                    //System.out.println("Predator moves towards prey south");
                    animalY +=moveDistance;
                    return true;
                }
            }
            else if(direction == 3)
            {
                if(animalX - p.animalX <= visualDistance && animalX - moveDistance > 1 && animalX - p.animalX > eatenDistance)
                {
                    //System.out.println("Predator moves towards prey east");
                    animalX -=moveDistance;
                    return true;
                }
            }
        }
        //otherwise, the predator does not see a prey, so return false
        return false;
    }

}
