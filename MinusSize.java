import greenfoot.*;

public class MinusSize extends Actor {
    private int FunktionsAnwendung;

    public MinusSize(int Funktion) {
        this.FunktionsAnwendung = Funktion;
    }

    protected void addedToWorld(World w) {
        if (w instanceof SettingMenu) {
            getImage().scale(SettingMenu.breite / 15, SettingMenu.hoehe / 10);
        }
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            handleAction();
        }
    }

    private void handleAction() {
        UserInfo myData = UserInfo.isStorageAvailable() ? UserInfo.getMyInfo() : null;
        int w = getWorld().getWidth();
        int h = getWorld().getHeight();
        
        // Lade den sauberen Wert aus dem Speicher (Standard 50)
        int currentVol = (myData != null) ? myData.getInt(0) : 50;

        if (FunktionsAnwendung == 1) { // Breite -
            int neueBreite = Math.max(200, w - (w / 30 * 2));
            if (myData != null) { myData.setInt(1, neueBreite); myData.store(); }
            Greenfoot.setWorld(new SettingMenu(neueBreite, h, SettingMenu.musik, currentVol));
        } 
        else if (FunktionsAnwendung == 2) { // Höhe -
            int neueHoehe = Math.max(200, h - (w / 30 * 2));
            if (myData != null) { myData.setInt(2, neueHoehe); myData.store(); }
            Greenfoot.setWorld(new SettingMenu(w, neueHoehe, SettingMenu.musik, currentVol));
        } 
        else if (FunktionsAnwendung == 3) { // Lautstärke -
            if (currentVol >= 10) {
                int newVol = currentVol - 10;
                Start.bgMusic.setVolume(newVol);
                if (myData != null) { myData.setInt(0, newVol); myData.store(); }
                System.out.println(newVol + " New Volume Minus");
                Greenfoot.setWorld(new SettingMenu(w, h, SettingMenu.musik, newVol));
            }
        }
    }
}
