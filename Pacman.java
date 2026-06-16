import greenfoot.*;

public class Pacman extends GridMover
{
    // Spieler steuert Pacman.
    private static final int GESCHWINDIGKEIT = 4;
    private static final int BILD_WARTEZEIT = 4;

    private static final String[] BILDER_RECHTS = {"pacman-right-1.png", "pacman-right-2.png", "pacman-right-3.png", "pacman-right-2.png"};
    private static final String[] BILDER_LINKS = {"pacman-left-1.png", "pacman-left-2.png", "pacman-left-3.png", "pacman-left-2.png"};
    private static final String[] BILDER_OBEN = {"pacman-up-1.png", "pacman-up-2.png", "pacman-up-3.png", "pacman-up-2.png"};
    private static final String[] BILDER_UNTEN = {"pacman-down-1.png", "pacman-down-2.png", "pacman-down-3.png", "pacman-down-2.png"};

    private final int startSpalte;
    private final int startReihe;

    private int wunschRichtungX;
    private int wunschRichtungY;
    private int bildNummer;
    private int bildZaehler;
    private String[] laufBilder;

    public Pacman(int startSpalte, int startReihe)
    {
        // GridMover.java macht die tile-Bewegung.
        super(startSpalte, startReihe, GESCHWINDIGKEIT);
        this.startSpalte = startSpalte;
        this.startReihe = startReihe;
        richtungX = 1;
        richtungY = 0;
        wunschRichtungX = 1;
        wunschRichtungY = 0;
        laufBilder = BILDER_RECHTS;
        bildSetzen();
    }

    public void act()
    {
        PacManWorld welt = (PacManWorld)getWorld();
        // spielLaeuft steht in PacManWorld.java.
        if (!welt.spielLaeuft()) {
            return;
        }

        // Erst bewegen, dann Bild, Punkt und Geist pruefen.
        bewegungMachen();
        animationMachen();
        punkteEssen(welt);
        geistPruefen(welt);
    }

    public int getDirectionX()
    {
        return richtungX;
    }

    public int getDirectionY()
    {
        return richtungY;
    }

    public void resetToStart()
    {
        // Nach Treffer zum Start. zurueckAufTile steht in GridMover.java.
        zurueckAufTile(startSpalte, startReihe);
        richtungX = 1;
        richtungY = 0;
        wunschRichtungX = 1;
        wunschRichtungY = 0;
        laufBilder = BILDER_RECHTS;
        bildNummer = 0;
        bildZaehler = 0;
        bildSetzen();
    }

    private void readKeys()
    {
        // Taste merken.
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) {
            wunschRichtungX = 0;
            wunschRichtungY = -1;
        }
        else if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) {
            wunschRichtungX = 0;
            wunschRichtungY = 1;
        }
        else if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            wunschRichtungX = -1;
            wunschRichtungY = 0;
        }
        else if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            wunschRichtungX = 1;
            wunschRichtungY = 0;
        }
    }

    private void bewegungMachen()
    {
        readKeys();
        // weiterlaufen steht in GridMover.java.
        weiterlaufen();

        if (!bewegtSich()) {
            naechsteRichtungWaehlen();
        }
    }

    private void naechsteRichtungWaehlen()
    {
        // Erst Wunschrichtung probieren, sonst geradeaus weiter.
        // kannLoslaufen/loslaufen stehen in GridMover.java.
        if (kannLoslaufen(wunschRichtungX, wunschRichtungY)) {
            loslaufen(wunschRichtungX, wunschRichtungY);
            bildDrehen();
        }
        else if (kannLoslaufen(richtungX, richtungY)) {
            loslaufen(richtungX, richtungY);
        }
    }

    private void punkteEssen(PacManWorld welt)
    {
        // Wenn ein Punkt beruehrt wird, an die World melden.
        PacDots punkt = (PacDots)getOneIntersectingObject(PacDots.class);
        if (punkt != null) {
            // punktEinsammeln steht in PacManWorld.java.
            welt.punktEinsammeln(punkt);
        }
    }

    private void geistPruefen(PacManWorld welt)
    {
        if(!welt.powerAktiv())
        {
            // Geist beruehrt? Leben weg.
            if (isTouching(Ghost.class)) {
                // pacmanWurdeGefangen steht in PacManWorld.java.
                welt.pacmanWurdeGefangen();
                
            }
        }
        else
        {
            // TODO Powerpill:
            // Hier soll Pacman den Ghost fressen, wenn Powerpill aktiv ist.
            // Schritt 1: Mit getOneIntersectingObject einen Ghost suchen.
            // Eingabe fuer Greenfoot: Ghost.class.
            // Schritt 2: Das Ergebnis in einer Ghost-Variable speichern, z.B. beruehrterGeist.
            // Wichtig: Greenfoot gibt Actor/Object zurueck, deshalb muss daraus ein Ghost gemacht werden.
            // Schritt 3: Pruefen, ob beruehrterGeist nicht null ist.
            // null heisst: Pacman beruehrt gerade keinen Ghost.
            // Schritt 4: Wenn ein Ghost da ist, beruehrterGeist.resetToStart() benutzen.
            // resetToStart steht in Ghost.java und setzt nur diesen einen Ghost zurueck.
            // Variablen, die du wahrscheinlich brauchst:
            // beruehrterGeist: der Ghost, den Greenfoot gefunden hat
            // welt: die PacManWorld, falls spaeter Punkte dazu kommen sollen
            // Haeufiger Fehler: isTouching benutzen und dann keinen Ghost zum resetten haben.
            // Besser: getOneIntersectingObject benutzen, weil man damit das Objekt bekommt.
            // Haeufiger Fehler: resetToStart auf Pacman aufrufen.
            // Besser: resetToStart auf dem gefundenen Ghost aufrufen.
            // Haeufiger Fehler: pacmanWurdeGefangen() hier aufrufen.
            // Dann verliert Pacman trotz Powerpill ein Leben.
            // Bonus spaeter: Nach resetToStart kann man in PacManWorld.java Punkte geben lassen.
        }
    }

    private void bildDrehen()
    {
        // Bildliste passend zur Richtung waehlen.
        if (richtungX < 0) {
            laufBilder = BILDER_LINKS;
        }
        else if (richtungX > 0) {
            laufBilder = BILDER_RECHTS;
        }
        else if (richtungY < 0) {
            laufBilder = BILDER_OBEN;
        }
        else if (richtungY > 0) {
            laufBilder = BILDER_UNTEN;
        }

        bildSetzen();
    }

    private void animationMachen()
    {
        // Mund auf/zu beim Laufen.
        if (!bewegtSich()) {
            return;
        }

        bildZaehler++;
        if (bildZaehler < BILD_WARTEZEIT) {
            return;
        }

        bildZaehler = 0;
        bildNummer++;
        if (bildNummer >= laufBilder.length) {
            bildNummer = 0;
        }
        bildSetzen();
    }

    private void bildSetzen()
    {
        setImage(laufBilder[bildNummer]);
        getImage().scale(PacManWorld.TILE_GROESSE - 6, PacManWorld.TILE_GROESSE - 6);
    }
}
