# Restaurant simulation
*Restaurant simulation* is a software simulating a restaurant's activity, from food being ordered to customers requesting the bill, through meals being prepared in the kitchen, collected by waiters who deliver these to the right tables.

## Architecture
The architectural pattern MVC (Model-View-Controller) has been used for building the restaurant simulation solution.

## Technologies
- Java
- Swing (for building the graphical user interface)
- Java threads to simulate the orders which are gradualy sent to the kitchen for preparation and then gradually delivered to the customers at their tables, i.e. therads for:
    1. Threads for the waiters that take note of the orders.
    2. Thread for the kitchen that cooks the orders.
    3. Threads for the waiters that serve the ready orders.
    4. Threads for the tables that have customers.
    5. Threads for the customers in the tables.

## How to run
You can compile and run the program yourself (with the *javac* and *java* commands) or run the jar file *restaurant_simulation.jar* provided at the root of the project.

## How to use
- The simulation can be started and terminated by respectively pressing the *START* and *STOP* buttons.
- The simulation speed can be adjusted at any time via the slider GUI component.
- The restaurant's kitchen can be closed at any time during the simulation via the *Close Kitchen* button. Pressing this button forces the kitchen to close prematurely, which informs the kitchen thread not to accept any more orders.
- The *Request Bill* button, enabled at the end of the simulation, prints out when pressed the bill of a table or a full report of all tables.
- Additionally, every event corresponding to every key steps of the restaurant process is logged in the *log.txt* file.
- The menu of the simulated restaurant is defined in the *menu.txt* file.

## Sample screenshots
![restaurant_simulation1](https://user-images.githubusercontent.com/16817374/105645092-c438dc00-5e99-11eb-8949-7e764da1b88e.JPG)
![restaurant_simulation2](https://user-images.githubusercontent.com/16817374/105645107-c733cc80-5e99-11eb-9434-20e03e662f17.JPG)

## Important notes
The *restaurant simulation* software has been submitted as part of a university assessment and is licensed here under the Apache License, version 2.0.

Plagiarism is :thumbsdown: and you are not allowed to re-use any part of this project without giving due credit.
