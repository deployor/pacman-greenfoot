import greenfoot.*;

public class PacDots extends Actor
{
    // Kleiner Punkt, den Pacman einsammeln kann.
    private static final int POINT_VALUE = 10;

    public PacDots()
    {
        GreenfootImage image = new GreenfootImage(10, 10);
        image.setColor(Color.WHITE);
        image.fillOval(0, 0, 10, 10);
        setImage(image);
    }

    public int getPointValue()
    {
        // Normale Punkte geben 10 Punkte.
        return POINT_VALUE;
    }
}
