import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Musik here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Musik extends Actor
{
    protected void addedToWorld(World w)
    {
        GreenfootImage image = getImage();

        if (w instanceof SettingMenu)
        {
            image.scale(SettingMenu.breite/15, SettingMenu.hoehe/10);
        }
    }
    public void act()
    {
        // Add your action code here.
    }
}
