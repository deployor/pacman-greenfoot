import greenfoot.*;

public class SettingMenu extends World
{
    public static int breite;
    public static int hoehe;
    public static double musik;
    public static int Volume;

    public SettingMenu(int breite, int hoehe,double musik,int Volume)
    {
        super(breite, hoehe, 1);

        SettingMenu.breite = getWidth();
        SettingMenu.hoehe = getHeight();
        

        addObject(new settings(), getWidth() - (getWidth()/25), getHeight() - (getHeight()/25));
        addObject(new ResetButton(), getWidth() - (getWidth()/20), getHeight()/2);
        addObject(new screenSize(), getWidth()/2 - (getWidth()/4), getHeight()/8);
        addObject(new PlusSize(1), getWidth()/2 , getHeight()/8);
        addObject(new MinusSize(1), getWidth()/2 + (getWidth()/4), getHeight()/8);
        addObject(new screenHight(),getWidth()/2 - (getWidth()/4), getHeight()/2);
        addObject(new PlusSize(2), getWidth()/2 , getHeight()/2);
        addObject(new MinusSize(2), getWidth()/2 + (getWidth()/4), getHeight()/2);
        addObject(new Musik(),getWidth()/2 - (getWidth()/4),getHeight()-getHeight()/10);
        addObject(new PlusSize(3), getWidth()/2 , getHeight()-getHeight()/10);
        addObject(new MinusSize(3), getWidth()/2 + (getWidth()/4), getHeight()-getHeight()/10);
    }
}
