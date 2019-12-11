package com.jamieghoorbin;

/**
 * A class for storing the attributes of a monster.
 * Contains a static method for generating a new Monster from the Main method.
 *
 * @author (Jamie Ghoorbin)
 * @version (12 / 10 / 2018)
 */

public class Monster
{
    private String name;
    private int score;
    private Coordinates position;

    /**
     * The Constructor for the monster class.
     * @param name The name of the monster.
     */
    public Monster(String name)
    {
        this.name = name;
        this.score = 0;
        this.position = new Coordinates(0, 0);
    }

    /**
     * Returns the name of the monster.
     *
     * @return name The position of the monster.
     */
    public String getMonsterName()
    {
        return name;
    }

    /**
     * Returns the score of the monster.
     *
     * @return score The score of the monster.
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Returns the position of the monster.
     *
     * @return position The position of the monster.
     */
    public Coordinates getMonsterPosition()
    {
        return position;
    }

    /**
     * Creates a new Monster object.
     *
     * @param name The name of the monster.
     * @return Monster The new monster.
     */
    public static Monster addMonster(String name)
    {
        return new Monster(name);
    }

    /**
     * Returns the first character of the monster name.
     * @return char at (0)
     */
    public char nameToChar()
    {
        return getMonsterName().charAt(0);
    }

    /**
     * Increments the monster score.
     */
    public void incrementScore()
    {
        score++;
    }
}