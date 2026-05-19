import greenfoot.*;

public class PacManWorld extends World
{
    public static int breite;
    public static int hoehe;
    public static double musik;
    public static int Volume;
    public PacManWorld(int breite, int hoehe, double musik,int Volume)
    {
        super(breite, hoehe, 1);

        PacManWorld.breite = getWidth();
        PacManWorld.hoehe = getHeight();
        //Greenfoot.playSound("PacmannMusikFürGreenFoot.mp3");
        
        addObject(new Pacman(), getWidth()/2, getHeight()/2);
        addObject(new settings(), getWidth() - (getWidth()/25), getHeight() - (getHeight()/25));
    }
}
