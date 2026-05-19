import greenfoot.*;

public class settings extends Actor
{
    protected void addedToWorld(World w)
    {
        GreenfootImage image = getImage();

        if (w instanceof PacManWorld)
        {
            image.scale(PacManWorld.breite/15, PacManWorld.hoehe/10);
            setLocation(PacManWorld.breite-PacManWorld.breite/30, PacManWorld.hoehe/20);
        }
        else if (w instanceof SettingMenu)
        {
            image.scale(SettingMenu.breite/15, SettingMenu.hoehe/10);
            setLocation(SettingMenu.breite-SettingMenu.breite/30, SettingMenu.hoehe/20);
        }
    }

    public void act()
    {
        
        if (Greenfoot.mouseClicked(this))
        {
            
            if (getWorld() instanceof PacManWorld)
            {
                Greenfoot.setWorld(new SettingMenu(getWorld().getWidth(), getWorld().getHeight(),PacManWorld.musik,PacManWorld.Volume));
            }
            else if (getWorld() instanceof SettingMenu)
            {
                Greenfoot.setWorld(new PacManWorld(getWorld().getWidth(), getWorld().getHeight(),SettingMenu.musik,SettingMenu.Volume));
            }
        }
    }
}
