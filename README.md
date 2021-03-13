# CO518-Assignment 1 - Java Monster Game

### Course: Algorithms, Correctness and Efficiency

## **Final grade:** 96%

A basic CLI (command line interface) monster game set on a grid. The main objective was to write an efficient algorithm to launch a monster attack using linear data structures types and whilst respecting certain constraints. 

Assignment description
-------------------------------------------------------------------------------

Develop a Monster Game that is set on a grid with rows and columns, as indicated below:

```
= = = = =
= * * * =
= * * * =
= * * * =
= = = = =
```

The grid above is made up of a hedge (`=`) that surrounds the stars (`*`). The aim of the game is to place monsters onto the grid by replacing the star (`*`) with the first letter of a monster’s name. No monster can be placed on a hedge (`=`).

Monsters can attack each other in turn within their cardinal directions only (North, West, South, East). So If there were monsters placed in the grid:

```
= = = = =
= A * * =
= * B C =
= * D * =
= = = = =
```

Only `B`, `C` and `D` can attack each other. Whoever attacks first eliminates the rest. (You should be able to add more monsters later).

Whenever a monsters eliminates another, increase their score by 1.
