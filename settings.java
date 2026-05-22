import greenfoot.*;

public class settings extends Actor
{
    protected void addedToWorld(World w)
    {
        GreenfootImage image = getImage();

        if (w instanceof PacManWorld)
        {
            image.scale(PacManWorld.breite/18, PacManWorld.hoehe/12);
            setLocation(PacManWorld.breite-PacManWorld.breite/32, PacManWorld.hoehe/24);
        }
        else if (w instanceof SettingMenu)
        {
            image.scale(SettingMenu.breite/18, SettingMenu.hoehe/12);
            setLocation(SettingMenu.breite-SettingMenu.breite/32, SettingMenu.hoehe/24);
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
