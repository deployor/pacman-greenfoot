import greenfoot.*;

public class PacDots extends Actor
{
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
        return POINT_VALUE;
    }
}
