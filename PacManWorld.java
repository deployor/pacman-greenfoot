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
        
        addObject(new Pacman(), getWidth()/2, getHeight()/2 + getHeight()/ 20* 5);
        addObject(new settings(), getWidth() - (getWidth()/25), getHeight() - (getHeight()/25));
        
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*24) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*22) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*18) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*16) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*14) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*24) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*22) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*18) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*16) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*14) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *30 );
        
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *28 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *28 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *28 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *28 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *28 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *28 );
        
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *26 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *26 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *26 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *26 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *26 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *26 );
        
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*24) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*22) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*18) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*16) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*14) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*0) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*24) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*22) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*18) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*16) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*14) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *24 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *22 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *22 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *22 );
    
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *22 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *22 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *22 );
    
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*24) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*22) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*24) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*22) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *20 );
        
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *18 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *18 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *16 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *16 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *14 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *14 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *12 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *12 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *10 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *10 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *8 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *6 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *6 );
        
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*24) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*22) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*18) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*16) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*14) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*24) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*22) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*18) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*16) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*14) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *4 );
        
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *2);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *2);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *2);
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *2);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *2);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 - getHeight()/46 *2);
        
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*14) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*16) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*18) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*14) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*16) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*18) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5);
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5);
        
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *2 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *2 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *2 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *2 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *2 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *2 );
        
        
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*24) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*22) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*24) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*22) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *4 );
        
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *6 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *6 );
       
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *6 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *6 );
        
        
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*24) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*22) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*18) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*16) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*14) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 - (getWidth()/70*0) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*32) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*30) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*28) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*26) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*24) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*22) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*20) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*18) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*16) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*14) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*12) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*10) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*8) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*6) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*4) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        addObject(new PacDots(), getWidth()/2 + (getWidth()/70*2) , getHeight()/2 + getHeight()/ 20* 5 + getHeight()/46 *8 );
        
    }
}
