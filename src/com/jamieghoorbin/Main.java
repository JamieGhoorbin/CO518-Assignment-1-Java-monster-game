package com.jamieghoorbin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Main class for the Monster game
 *
 * @author (Jamie Ghoorbin)
 * @version (12 / 10 / 2018)
 */
//Created in IntelliJ IDEA 2017.3.2 x64
public class Main
{
    /**
     * Creates a Scanner for input
     * Creates a new board
     */
    private static Scanner scanner;
    private static Board board1;

    public static void main(String[] args)
    {
        scanner = new Scanner(System.in);
        board1  = new Board();
        boolean quit = false;
        viewBoard();
        printActions();
        while (!quit)
        {
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    addMonster();
                    System.out.println();
                    printActions();
                    break;

                case "2":
                    launchAttack();
                    System.out.println();
                    printActions();
                    break;

                case "3":
                    System.out.println();
                    viewBoard();
                    printActions();
                    break;

                case "4":
                    retrieveMonsterScore();
                    System.out.println();
                    printActions();
                    break;

                case "5":
                    listAllPlayers();
                    System.out.println();
                    printActions();
                    break;

                case "6":
                    System.out.println("\nThe game has now ended.\n" +
                            "Thank you for playing...");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid input\n" +
                            "Enter a value between 1 - 6...");
            }
        }
    }

    /**
     * Prints the actions/menu to the console.
     */
    private static void printActions()
    {
        System.out.println("Please choose an option from the menu");
        System.out.println("1 - Enter a monster onto the board\n" +
                "2 - Launch an attack on a monster\n" +
                "3 - View the board\n" +
                "4 - Retrieve a score\n" +
                "5 - List all players\n" +
                "6 - Quit playing");
    }

    /**
     * A method for adding monsters to the board.
     */
    private static void addMonster()
    {
        System.out.println("Enter the name of the monster: ");
        String name = scanner.next();
        if (name.charAt(0) == '=' || name.charAt(0) == '*')
        {
            System.out.println("Name can't contain a '=' or '*'...");
        }
        else
        {
            Monster newMonster = Monster.addMonster(name); //creates the object with name
            if (board1.addMonster(newMonster))
            {
                System.out.println(name + " has been added to the board.");
            }
            else
            {
                System.out.println(name + " could not be added.");
            }
        }
    }

    /**
     * A method for launching an attack on a Monster.
     */
    private static void launchAttack()
    {
        if (board1.monsterListIsEmpty())
        {
            System.out.println("You are unable to launch an attack.\nNo monsters have entered the game.");
        }
        else
        {
            List<Monster> mList = new ArrayList<>();
            System.out.println("Enter the name of the monster you wish to use: ");
            String name = scanner.next();
            if (board1.getMonster(name) != null)
            {
                mList = board1.launchAttack(name);
                if (mList != null)
                {
                    System.out.println("Type which monster you would like to attack from the list below: ");
                    mList.forEach(monster -> System.out.println(monster.getMonsterName() + " "));
                    if (board1.getMonster(name) != null)
                    {
                        name = scanner.next();
                        board1.removeMonster(name);
                    }
                    else
                    {
                        System.out.println(name + " is not a valid monster.");
                    }
                }
                else
                {
                    System.out.println(name + " has no monsters near by.");
                    return;
                }
            }
            else
            {
                System.out.println("Unable to find " + name + ".");
            }
        }
    }

    /**
     * Prints the board to the console.
     */
    private static void viewBoard()
    {
        board1.viewBoard();
    }

    /**
     * A method for retrieving the score of a Monster.
     */
    private static void retrieveMonsterScore()
    {
        System.out.println("Enter the name of the monster: ");
        String name = scanner.next();
        int monsterScore = board1.getMonsterScore(name);
        if (monsterScore != -1)
        {
            System.out.println(name + " has scored " + monsterScore);
        }
        else
        {
            System.out.println("Unable to find score...");
        }
    }

    /**
     * Prints a list of the Monsters to the console.
     */
    private static void listAllPlayers()
    {
        if (!board1.listAllMonsters())
        {
            System.out.println("No monsters have entered the game.\n" +
                    "Choose option 1 to enter a monster.");
        }
    }
}