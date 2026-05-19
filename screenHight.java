import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class screenHight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class screenHight extends Actor
{
    protected void addedToWorld(World w)
    {
        GreenfootImage image = getImage();

        if (w instanceof SettingMenu)
        {
            setRotation(90);
            image.scale(SettingMenu.breite/5, SettingMenu.hoehe/15);
        }
    }
}
