import greenfoot.*;

public class Wall extends Actor
{
    public Wall()
    {
        GreenfootImage image = new GreenfootImage(PacManWorld.TILE_SIZE, PacManWorld.TILE_SIZE);
        image.setColor(new Color(20, 40, 180));
        image.fillRect(0, 0, PacManWorld.TILE_SIZE, PacManWorld.TILE_SIZE);
        image.setColor(new Color(80, 120, 255));
        image.drawRect(1, 1, PacManWorld.TILE_SIZE - 3, PacManWorld.TILE_SIZE - 3);
        setImage(image);
    }
}
