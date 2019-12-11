package com.jamieghoorbin;

import java.util.*;

/**
 * This class represents a 2D monster game board.
 *
 * @author (Jamie Ghoorbin)
 * @version (12 / 10 / 2018)
 */

public class Board
{
    private final int SIZE = 5;
    public char board[][];
    private Random rand;
    private int first = 1;
    private int last = SIZE - 1;
    private List<Monster> monsterList;

    /**
     * Constructor fills the board initially with *
     * and sets up the surrounding edges / hedge "="
     */
    public Board()
    {
        board = new char[SIZE][SIZE];
        rand = new Random();
        monsterList = new ArrayList<>();
        for (int i = first; i <= last; i++)
            for (int j = first; j <= last; j++)
                board[i][j] = '*';
        for (int i = 0; i < SIZE; i++)
        {
            board[0][i] = '=';
            board[i][0] = '=';
            board[i][SIZE - 1] = '=';
            board[SIZE - 1][i] = '=';
        }
    }

    /**
     * Prints the board to the terminal.
     */
    public void viewBoard()
    {
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    /**
     * Allocates a monster a random position on the grid within the "*" section.
     *
     * @return foundCoordinates The available coordinates on the board.
     */
    private Coordinates findPlaceMonster()
    {
        int randRow = rand.nextInt(last);
        int randCol = rand.nextInt(last);
        while (board[randRow][randCol] != '*')
        {
            randRow = rand.nextInt(last);
            randCol = rand.nextInt(last);
        }
        Coordinates foundCoordinate = new Coordinates(randRow, randCol);
        return foundCoordinate;
    }

    /**
     * Launches an attack on another monster north, south, west or
     * east of it.
     *
     * @param name The name of the monster that will attack.
     * @return tempList The list of monsters available to attack; otherwise return null.
     */
    public List launchAttack(String name)
    {
        if (monsterList.isEmpty())
        {
            return null;
        }
        else
        {
            Monster monster = getMonster(name); //Get the monster that wishes to attack.
            int coordX = monster.getMonsterPosition().getX(); //Get the x coordinates of the monster.
            int coordY = monster.getMonsterPosition().getY();//Get the y coordinates of the monster.
            List<Monster> tempList = new ArrayList<>(); //Create temp list of possible monster to attack.
            for (Monster m : monsterList)
            {
                //look north
                if (m.getMonsterPosition().getX() - 1 == coordX && m.getMonsterPosition().getY() == coordY)
                {
                    tempList.add(m);
                }
                //look south
                if (m.getMonsterPosition().getX() + 1 == coordX && m.getMonsterPosition().getY() == coordY)
                {
                    tempList.add(m);
                }
                //look west
                if (m.getMonsterPosition().getX() == coordX && m.getMonsterPosition().getY() - 1 == coordY)
                {
                    tempList.add(m);
                }
                //look east
                if (m.getMonsterPosition().getX() == coordX && m.getMonsterPosition().getY() + 1 == coordY)
                {
                    tempList.add(m);
                }
            }
            if (tempList.size() > 0)
            {
                monster.incrementScore(); //increment the monsters score
                return tempList;
            }
            else
            {
                return null;
            }
        }
    }

    /**
     * Removes a monster from the board and removes it from the monsterList.
     * Adds dead monster to deadMonsterList.
     *
     * @param name The name of the monster to remove.
     */
    public void removeMonster(String name)
    {
        Monster monster = getMonster(name);
        if (monster == null)
        {
            System.out.println("Invalid monster...");
        }
        else
        {
            int coordX = monster.getMonsterPosition().getX();
            int coordY = monster.getMonsterPosition().getY();
            board[coordX][coordY] = '*';
            System.out.println(monster.getMonsterName() + " was destroyed!");
            monsterList.remove(monster);
        }
    }

    /**
     * Adds a monster to the monsterList, adds the monsters first character to the board
     * and updates the monsters coordinate.
     *
     * @param monster The monster.
     * @return true if the monster was added: otherwise false.
     */
    public boolean addMonster(Monster monster)
    {
        if (!(monsterList.size() < Math.pow((SIZE - 2), 2)))
        {
            System.out.println("The board is full.");
            return false;
        }
        if (findMonster(monster.getMonsterName()) >= 0)
        {
            return false;
        }
        else
        {
            //add new monster to list.
            monsterList.add(monster);
            //get the coordinates of the free space on the board
            Coordinates foundCoordinate = findPlaceMonster();
            //add char to the board
            board[foundCoordinate.getX()][foundCoordinate.getY()] = monster.nameToChar();
            //set the monster coordinates in monsterList
            monster.getMonsterPosition().setX(foundCoordinate.getX());
            monster.getMonsterPosition().setY(foundCoordinate.getY());
            return true;
        }
    }

    /**
     * Returns the monsters score.
     *
     * @param name The name of the monster.
     * @return The monsters score.
     */
    public int getMonsterScore(String name)
    {
        if (getMonster(name) == null)
        {
            return -1;
        }
        return getMonster(name).getScore();
    }

    /**
     * Finds a monster and returns the index of the monster.
     *
     * @param name The name of the monster.
     * @return i The index of the monster in the list.
     */
    private int findMonster(String name)
    {
        for (int i = 0; i < this.monsterList.size(); i++)
        {
            Monster monster = this.monsterList.get(i);
            if (monster.getMonsterName().equals(name))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the monster object with a given name.
     *
     * @param name The name of the monster.
     * @return The monster; otherwise null;
     */
    public Monster getMonster(String name)
    {
        int position = findMonster(name);
        if (position >= 0)
        {
            return this.monsterList.get(position);
        }
        else
        {
            return null;
        }
    }

    /**
     * Prints a list of all the monsters that have not been attacked / removed from the board.
     *
     * @return true if the monster list contains monsters: otherwise false.
     */
    public boolean listAllMonsters()
    {
        if (monsterList.isEmpty())
        {
            return false;
        }
        else
        {
            System.out.println("Monster List:");
            monsterList.forEach(m -> System.out.println("Name: " + m.getMonsterName()));
        }
        return true;
    }

    /**
     * Checks if the monsterList is empty.
     *
     * @return true if this list is empty: otherwise false.
     */
    public boolean monsterListIsEmpty()
    {
        if (monsterList.isEmpty())
        {
            return true;
        }
        return false;
    }
}