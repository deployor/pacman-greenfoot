import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pacman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pacman extends Actor
{
    public static String PacManKey="L";
    public static Boolean UpPosible;
    public static Boolean DownPosible;
    public static Boolean RightPosible;
    public static Boolean LeftPosible;
    
    public Pacman()
    {
        getImage().scale(PacManWorld.breite / 30, PacManWorld.hoehe / 20);    
    }
    public void act()
    {
        if(Greenfoot.isKeyDown("Down") || Greenfoot.isKeyDown("s"))
        {
            PacManKey ="D";
        }
        if(Greenfoot.isKeyDown("Up") || Greenfoot.isKeyDown("w"))
        {
            PacManKey ="U";
        }
        if(Greenfoot.isKeyDown("Right") || Greenfoot.isKeyDown("d"))
        {
            PacManKey ="R";
        }
        if(Greenfoot.isKeyDown("Left") || Greenfoot.isKeyDown("a"))
        {
            PacManKey ="L";
        }
        System.out.println(getX() + " X, " + getY() + " Y");
    }
}
