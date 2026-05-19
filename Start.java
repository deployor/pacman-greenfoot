import greenfoot.*;

public class Start extends World
{
    // Die Musik wird static definiert, damit sie weltübergreifend bleibt
    public static GreenfootSound bgMusic = new GreenfootSound("PacmannMusikFürGreenFoot.mp3");

    public Start()
    {    
        // Temporäre Weltgröße beim Initialisieren
        super(600, 400, 1); 
        
        // Standardwerte festlegen (falls noch nichts gespeichert wurde)
        int resX = 600;
        int resY = 400;
        int volume = 50;

        // Versuchen, gespeicherte Einstellungen zu laden
        if (UserInfo.isStorageAvailable()) {
            UserInfo myData = UserInfo.getMyInfo();
            
            if (myData != null) {
                // Wir nutzen Index 0 für Lautstärke, 1 für X, 2 für Y
                // Nur laden, wenn die Werte größer als 0 sind (vermeidet 0x0 Auflösung)
                if (myData.getInt(1) > 0) resX = myData.getInt(1);
                if (myData.getInt(2) > 0) resY = myData.getInt(2);
                
                // Lautstärke laden (Index 0)
                volume = myData.getInt(0);
            }
        }
        
        // Musik starten und geladene Lautstärke anwenden
        if (!bgMusic.isPlaying()) {
            bgMusic.playLoop();
        }
        bgMusic.setVolume(volume);

        // Sofort in die Hauptwelt wechseln mit den geladenen Werten
        // (Ich übernehme hier deine Parameter: X, Y, Score=0, Volume)
        Greenfoot.setWorld(new PacManWorld(resX, resY, 0, volume));
    }
}