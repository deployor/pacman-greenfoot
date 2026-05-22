import greenfoot.*;

public class Wall extends Actor
{
    // Wand tile.
    public Wall()
    {
        // Die Groesse kommt aus PacManWorld.TILE_GROESSE.
        GreenfootImage image = new GreenfootImage(PacManWorld.TILE_GROESSE, PacManWorld.TILE_GROESSE);
        image.setColor(new Color(20, 40, 180));
        image.fillRect(0, 0, PacManWorld.TILE_GROESSE, PacManWorld.TILE_GROESSE);
        image.setColor(new Color(80, 120, 255));
        image.drawRect(1, 1, PacManWorld.TILE_GROESSE - 3, PacManWorld.TILE_GROESSE - 3);
        setImage(image);
    }
}
