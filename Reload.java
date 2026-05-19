import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Reload here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Reload extends World
{
    public static int screen = 0;
    
    
    /**
     * Constructor for objects of class PacManWorld.
     * 
     */
    public Reload(int breite, int hoehe, int screen)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(breite, hoehe, 1); 
        if(screen==1)
        {
            Greenfoot.setWorld(new SettingMenu(getWidth(), getHeight(),PacManWorld.musik,PacManWorld.Volume));
        }
    }
}
