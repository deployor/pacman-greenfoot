import greenfoot.*;

public class screenSize extends Actor
{
    protected void addedToWorld(World w)
    {
        GreenfootImage image = getImage();

        if (w instanceof SettingMenu)
        {
            image.scale(SettingMenu.breite/5, SettingMenu.hoehe/15);
        }
    }
}
