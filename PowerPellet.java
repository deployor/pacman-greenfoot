import greenfoot.*;

public class PowerPellet extends PacDots
{
    // Groesserer Punkt mit mehr Wert.
    private static final int POINT_VALUE = 50;

    public PowerPellet()
    {
        GreenfootImage image = new GreenfootImage(18, 18);
        image.setColor(Color.WHITE);
        image.fillOval(0, 0, 18, 18);
        setImage(image);
    }

    public int getPointValue()
    {
        // Power-Pellets geben mehr Punkte.
        return POINT_VALUE;
    }
}
