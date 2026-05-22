import greenfoot.*;

public class PowerPellet extends PacDots
{
    // Grosser Punkt. Er ist auch ein PacDots, nur mit mehr Punkten.
    private static final int POINT_VALUE = 50;

    public PowerPellet()
    {
        // Groesserer weisser Kreis als normaler Punkt.
        GreenfootImage image = new GreenfootImage(18, 18);
        image.setColor(Color.WHITE);
        image.fillOval(0, 0, 18, 18);
        setImage(image);
    }

    public int getPointValue()
    {
        // PacManWorld.punktEinsammeln ruft diese Methode auf.
        return POINT_VALUE;
    }
}
