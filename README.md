# Predator Prey Simulation

**Kayleigh Calder**
 - calderkayleigh@gwu.edu
 - calderkayleigh

The starter code creates an empty world and draws it to the screen. Look through the code and read the comments to learn what the different classes and functions do. You are free to modify *any* code in the template project

This code generates a predator and prey simulation using a GUI window based on a given set of rules. There are 5 main classes and 2 utility classes: World, PPSim, Animal, Predator, and Prey and Helper and DotPanel. 

**PPSim**
PPSim holds the main method of the program. The main method creates a new predator prey simulation (PPSim). This class holds many of the GUI features including creating the GUI window, keyboard commands and mouse commands. Within this class is an infinite loop that updates the rules for the world, draws predators and prey to the screen, and controls how quickly the simulation will run. This class additionally creates a new world with a given width, height, initial number of prey, and initial number of predators, calling on the World class to execute this function. 

**World**
	The world class holds the elements of the world that the predator and prey live in. It uses array lists to keep track of new prey and predators that are introduced in the world. It also uses a function called “populate” to add predators and prey into the world. Another main function of this class is that it updates all of the actions by the predators and prey within the update method. This method holds array lists of animals to remove and to add. When the simulation updates, it checks if predators reproduced, died naturally, or moved within the world. For prey, it checks if they reproduced (and if so, mutated), if they were eaten by predators, if they died naturally, or if they simply moved within the world. This class also draws the predators to the screen using functions defined in the DotPanel class. In addition, it controls changing the canvas’s background and resetting the simulation. The simulation can be reset using the key controls created in PPSim. 

**Animal**
	The animal class defines a general functionality for all animals that live within this world. It outlines functions so that animals can change directions, move in general, reproduce, and die naturally. Animal is the parent class for the Predator and Prey classes. 

**Predator**
	The Predator class is a child class of the Animal class. This means that it inherits the features from the Animal class, but also has functions that are specific to this type of species. For example, Predators move in a different way than Prey. If a Predator sees a Prey within 15 squares of the direction it is facing, it will follow that Prey. If a Predator is horizontally or vertically next to a Prey, it will eat it. Predators are represented by red squares. 

**Prey**
	Similar to the Predator class, the Prey class is also a child of the Animal class. Specific characteristics of Prey include their ability to mutate, meaning that they have a child who is represented by a different color and how they run in the opposite direction of a Predator if they see it within 10 squares of themselves. Prey are represented by randomly colored circles, and for this reason, are able to camouflage if their distinct color matches the canvas color. If this is so, they are unable to be eaten by a Predator.
	
**Additional Features**
	This project incorporates additional GUI Features that were not outlined in the project instructions: 
- buttons to add prey and predators from a pre-defined location
- an alternative reset of the simulation that allows the user to input how many prey and predators they would like when they select the back space button on their keyboard
- a program escape option that occurs when the user hits escape on their keyboard
	These features increase the overall functionality of the program. Allowing users to easily reset the simulation and determine what they would like to see customizes this code for the user, specifying it to their needs. This saves time for the user, as they do not have to re-write code when they want to see different scenarios of the simulation. The program escape option is another easy way for users to quickly exit out of programs, saving them time. The buttons are also easy ways to add prey and predators into the simulation. They enter the program at a pre-defined location, which acts as a gateway or entrance into this world. 

