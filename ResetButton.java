import greenfoot.*;

public class ResetButton extends Actor
{
    protected void addedToWorld(World w) {
        if (w instanceof SettingMenu) {
            getImage().scale(SettingMenu.breite / 10, SettingMenu.hoehe / 15);
        }
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            resetToDefault();
        }
    }

    private void resetToDefault()
    {
        // 1. Standardwerte festlegen
        int defaultWidth = 1200;
        int defaultHeight = 800;
        int defaultVolume = 50;

        // 2. Speicher überschreiben
        if (UserInfo.isStorageAvailable()) {
            UserInfo myData = UserInfo.getMyInfo();
            if (myData != null) {
                myData.setInt(0, defaultVolume); // Index 0: Lautstärke
                myData.setInt(1, defaultWidth);  // Index 1: Breite
                myData.setInt(2, defaultHeight); // Index 2: Höhe
                myData.store(); // Speichern
            }
        }

        // 3. Musik sofort anpassen
        Start.bgMusic.setVolume(defaultVolume);

        // 4. Welt mit Standardwerten neu laden
        System.out.println("Einstellungen auf Standard zurückgesetzt!");
        Greenfoot.setWorld(new SettingMenu(defaultWidth, defaultHeight, SettingMenu.musik, defaultVolume));
    }
}
