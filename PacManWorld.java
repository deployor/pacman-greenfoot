import greenfoot.*;

public class PacManWorld extends World
{
    // Diese Klasse verwaltet das ganze Spiel.
    public static int breite;
    public static int hoehe;
    public static double musik;
    public static int Volume;

    public static final int TILE_GROESSE = 32;
    public static final int OBERE_LEISTE = 48;

    private static final int START_WARTEZEIT = 150;

    // # = Wand, . = Punkt, o = grosser Punkt, P = Pacman, G = Geist
    private static final String[] MAZE = {
        "#####################",
        "#.........#.........#",
        "#.###.###.#.###.###.#",
        "#o#...............#o#",
        "#.#.###.#####.###.#.#",
        "#...#.........#.....#",
        "###.#.### ###.###.###",
        "#.....#  G  #.....#.#",
        "#.###.# ### #.###.#.#",
        " .....#G P G#.....  ",
        "#.###.# ### #.###.#.#",
        "#.....#  G  #.....#.#",
        "###.#.#######.###.###",
        "#...#.........#.....#",
        "#.#.###.#####.###.#.#",
        "#o#...............#o#",
        "#.###.###.#.###.###.#",
        "#.........#.........#",
        "#####################"
    };

    private static final int LABYRINTH_SPALTEN = MAZE[0].length();
    private static final int TUNNEL_REIHE = 9;

    private int punkte;
    private int leben;
    private int punkteUebrig;
    private int geisterZaehler;
    private int warteZeit;
    private boolean spielVorbei;
    private boolean gewonnen;
    private Pacman pacman;

    public PacManWorld(int breite, int hoehe, double musik, int Volume)
    {
        // false ist wichtig: sprites duerfen beim Tunnel kurz rauslaufen.
        super(LABYRINTH_SPALTEN * TILE_GROESSE, MAZE.length * TILE_GROESSE + OBERE_LEISTE, 1, false);

        // Werte speichern, damit andere Klassen sie benutzen koennen.
        PacManWorld.breite = getWidth();
        PacManWorld.hoehe = getHeight();
        PacManWorld.musik = musik;
        PacManWorld.Volume = Volume;

        punkte = 0;
        leben = 3;
        punkteUebrig = 0;
        geisterZaehler = 0;
        warteZeit = START_WARTEZEIT;
        spielVorbei = false;
        gewonnen = false;

        setBackground(new GreenfootImage(getWidth(), getHeight()));
        getBackground().setColor(Color.BLACK);
        getBackground().fill();

        // Pacman und Ghosts sollen vor Punkten und Waenden sichtbar sein.
        setPaintOrder(settings.class, Pacman.class, Ghost.class, PacDots.class, PowerPellet.class, Wall.class);
        // Aus der Text-Map werden jetzt echte Objekte gebaut.
        labyrinthBauen();
        addObject(new settings(), getWidth() - 28, 24);
        anzeigeAktualisieren();
    }

    public void act()
    {
        // Pro Runde: Starttext, Sieg und Neustart pruefen.
        startTextAktualisieren();

        if (!spielVorbei && !gewonnen && punkteUebrig == 0) {
            gewonnen = true;
            showText("Gewonnen! Druecke R", getWidth() / 2, 25);
        }

        // Nach win/lose startet R das Spiel neu.
        if ((spielVorbei || gewonnen) && Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new PacManWorld(getWidth(), getHeight(), musik, Volume));
        }
    }

    public boolean spielLaeuft()
    {
        // Bei win/lose stoppt alles.
        return !spielVorbei && !gewonnen;
    }

    public boolean geisterDuerfenLaufen()
    {
        // Geister duerfen erst nach der Wartezeit los.
        return spielLaeuft() && warteZeit == 0;
    }

    public Pacman gibPacman()
    {
        return pacman;
    }

    public int gibLabyrinthReihen()
    {
        return MAZE.length;
    }

    public int gibTileMitteX(int spalte)
    {
        // Spalte zu x machen. spalteEinrollen ist fuer den Tunnel.
        return spalteEinrollen(spalte) * TILE_GROESSE + TILE_GROESSE / 2;
    }

    public int gibTileMitteY(int reihe)
    {
        // Reihe zu y machen.
        return OBERE_LEISTE + reihe * TILE_GROESSE + TILE_GROESSE / 2;
    }

    public int spalteEinrollen(int spalte)
    {
        // Links raus = rechts rein.
        if (spalte < 0) {
            return LABYRINTH_SPALTEN - 1;
        }
        if (spalte >= LABYRINTH_SPALTEN) {
            return 0;
        }
        return spalte;
    }

    public boolean istTunnelAusgang(int spalte, int reihe)
    {
        // Nur hier geht der Tunnel raus.
        return reihe == TUNNEL_REIHE && (spalte < 0 || spalte >= LABYRINTH_SPALTEN);
    }

    public boolean istTileFrei(int spalte, int reihe)
    {
        // Nur # ist Wand.
        if (reihe < 0 || reihe >= MAZE.length) {
            return false;
        }

        if (istTunnelAusgang(spalte, reihe)) {
            return true;
        }

        if (spalte < 0 || spalte >= LABYRINTH_SPALTEN) {
            return false;
        }

        // Zeichen aus der Text-Map holen und Wand pruefen.
        return gibLabyrinthZeichen(spalte, reihe) != '#';
    }

    public void punktEinsammeln(PacDots punkt)
    {
        // Punkt weg und Score hoch.
        // TODO Powerpill:
        // 1. In PacManWorld.java oben eine Zahl machen: private int powerZeit;
        // 2. Hier grob schreiben: if (punkt instanceof PowerPellet) powerZeit = 400;
        // 3. In PacManWorld.act() jede Runde powerZeit runterzaehlen, wenn sie > 0 ist.
        // 4. In PacManWorld.java eine Methode machen: public boolean powerAktiv() { return powerZeit > 0; }
        // 5. In Ghost.java powerAktiv() fragen: wenn true, ghost-blue.png benutzen und weglaufen.
        // 6. In Pacman.java bei Geist-Kontakt powerAktiv() fragen: true = Ghost fressen, false = Leben weg.
        removeObject(punkt);
        // getPointValue steht in PacDots.java und PowerPellet.java.
        punkte += punkt.getPointValue();
        punkteUebrig--;
        anzeigeAktualisieren();
    }

    public void pacmanWurdeGefangen()
    {
        // Geist beruehrt = Leben weg.
        if (!geisterDuerfenLaufen()) {
            return;
        }

        leben--;
        anzeigeAktualisieren();

        if (leben <= 0) {
            spielVorbei = true;
            // TODO Game Over Screen:
            // Hier spaeter zu GameOverWorld wechseln, statt nur Text zu zeigen.
            showText("Verloren! Druecke R", getWidth() / 2, 25);
            removeObject(pacman);
            return;
        }

        // Bei uebrigem Leben starten alle wieder neu.
        figurenZuruecksetzen();
        warteZeit = START_WARTEZEIT / 2;
    }

    private void startTextAktualisieren()
    {
        // Text oben beim Start.
        if (spielVorbei || gewonnen) {
            return;
        }

        if (warteZeit > 0) {
            warteZeit--;
            int sekunden = warteZeit / 50 + 1;
            showText("Geister warten: " + sekunden, getWidth() / 2, 25);
        }
        else {
            showText("", getWidth() / 2, 25);
        }
    }

    private void labyrinthBauen()
    {
        // Map aus Text bauen.
        for (int reihe = 0; reihe < MAZE.length; reihe++) {
            for (int spalte = 0; spalte < LABYRINTH_SPALTEN; spalte++) {
                // Zeichen lesen und passendes Objekt bauen.
                objektAusZeichenBauen(gibLabyrinthZeichen(spalte, reihe), spalte, reihe);
            }
        }
    }

    private char gibLabyrinthZeichen(int spalte, int reihe)
    {
        // Fehlendes Zeichen = leerer Weg.
        if (spalte < 0 || spalte >= MAZE[reihe].length()) {
            return ' ';
        }
        return MAZE[reihe].charAt(spalte);
    }

    private void objektAusZeichenBauen(char zeichen, int spalte, int reihe)
    {
        // Zeichen wird Objekt an der passenden tile-Position.
        int x = gibTileMitteX(spalte);
        int y = gibTileMitteY(reihe);

        if (zeichen == '#') {
            // Wall steht in Wall.java.
            addObject(new Wall(), x, y);
        }
        else if (zeichen == '.') {
            // PacDots steht in PacDots.java.
            addObject(new PacDots(), x, y);
            punkteUebrig++;
        }
        else if (zeichen == 'o') {
            // PowerPellet steht in PowerPellet.java.
            addObject(new PowerPellet(), x, y);
            punkteUebrig++;
        }
        else if (zeichen == 'P') {
            // Pacman steht in Pacman.java.
            pacman = new Pacman(spalte, reihe);
            addObject(pacman, x, y);
        }
        else if (zeichen == 'G') {
            // Ghost steht in Ghost.java.
            addObject(new Ghost(spalte, reihe, geisterZaehler), x, y);
            geisterZaehler++;
        }
    }

    private void figurenZuruecksetzen()
    {
        // Pacman und alle Ghosts wieder zum Start.
        pacman.resetToStart();

        for (Object object : getObjects(Ghost.class)) {
            Ghost ghost = (Ghost)object;
            // resetToStart steht in Ghost.java.
            ghost.resetToStart();
        }
    }

    private void anzeigeAktualisieren()
    {
        // Text oben.
        showText("Punkte: " + punkte, 80, 25);
        showText("Leben: " + leben, 200, 25);
        showText("Punkte verbleibend: " + punkteUebrig, 360, 25);
    }
}
