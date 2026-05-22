import greenfoot.*;

public class Ghost extends GridMover
{
    // Geist laeuft allein.
    private static final int GESCHWINDIGKEIT = 2;
    private static final int ROTER_GEIST = 0;
    private static final int PINKER_GEIST = 1;
    private static final int BLAUER_GEIST = 2;
    private static final int ORANGER_GEIST = 3;

    private final int startSpalte;
    private final int startReihe;
    private final int geistArt;

    public Ghost(int startSpalte, int startReihe, int geistArt)
    {
        // GridMover.java macht die tile-Bewegung.
        super(startSpalte, startReihe, GESCHWINDIGKEIT);
        this.startSpalte = startSpalte;
        this.startReihe = startReihe;
        this.geistArt = geistArt;
        richtungX = 1;
        richtungY = 0;
        geistBildSetzen();
    }

    public void act()
    {
        PacManWorld welt = (PacManWorld)getWorld();
        // World entscheidet, ob Ghosts schon laufen duerfen.
        if (!welt.geisterDuerfenLaufen()) {
            return;
        }

        // TODO Powerpill:
        // Wenn Pacman eine Powerpill gegessen hat, soll dieser Ghost Angst haben.
        // Dann soll er ghost-blue.png benutzen und von Pacman weg laufen.
        // Die Info "Powerpill ist aktiv" sollte aus PacManWorld kommen.
        // weiterlaufen steht in GridMover.java.
        weiterlaufen();

        if (!bewegtSich()) {
            waehleNaechstenWeg(welt);
        }
    }

    public void resetToStart()
    {
        // Zurueck zum Start. zurueckAufTile steht in GridMover.java.
        zurueckAufTile(startSpalte, startReihe);
        richtungX = 1;
        richtungY = 0;
    }

    private void waehleNaechstenWeg(PacManWorld welt)
    {
        // Ziel suchen und beste Richtung dorthin nehmen.
        int[] ziel = zielFeldFinden(welt);
        int besteRichtungX = 0;
        int besteRichtungY = 0;
        int besteEntfernung = Integer.MAX_VALUE;
        boolean wegGefunden = false;

        int[][] richtungen = alleRichtungen();
        for (int i = 0; i < richtungen.length; i++) {
            int richtungX = richtungen[i][0];
            int richtungY = richtungen[i][1];

            if (!istWegOk(richtungX, richtungY)) {
                continue;
            }

            int entfernung = entfernungNachSchritt(richtungX, richtungY, ziel);
            if (!wegGefunden || entfernung < besteEntfernung) {
                besteRichtungX = richtungX;
                besteRichtungY = richtungY;
                besteEntfernung = entfernung;
                wegGefunden = true;
            }
        }

        if (wegGefunden) {
            // loslaufen steht in GridMover.java.
            loslaufen(besteRichtungX, besteRichtungY);
        }
    }

    private int[][] alleRichtungen()
    {
        return new int[][] {
            {1, 0},
            {-1, 0},
            {0, -1},
            {0, 1}
        };
    }

    private boolean istWegOk(int versuchX, int versuchY)
    {
        // kannLoslaufen steht in GridMover.java.
        if (!kannLoslaufen(versuchX, versuchY)) {
            return false;
        }

        // Nicht zuruecklaufen, wenn ein anderer Weg frei ist.
        if (istRueckweg(versuchX, versuchY) && hatWegOhneUmdrehen()) {
            return false;
        }

        return true;
    }

    private boolean hatWegOhneUmdrehen()
    {
        // Prueft, ob es einen Weg gibt, ohne direkt umzudrehen.
        int[][] richtungen = alleRichtungen();

        for (int i = 0; i < richtungen.length; i++) {
            int richtungX = richtungen[i][0];
            int richtungY = richtungen[i][1];

            if (!istRueckweg(richtungX, richtungY) && kannLoslaufen(richtungX, richtungY)) {
                return true;
            }
        }

        return false;
    }

    private boolean istRueckweg(int richtungX, int richtungY)
    {
        return richtungX == -this.richtungX && richtungY == -this.richtungY;
    }

    private int entfernungNachSchritt(int richtungX, int richtungY, int[] ziel)
    {
        PacManWorld welt = (PacManWorld)getWorld();
        // spalteEinrollen steht in PacManWorld.java.
        int naechsteSpalte = welt.spalteEinrollen(spalte + richtungX);
        int naechsteReihe = reihe + richtungY;
        return entfernungQuadrat(naechsteSpalte, naechsteReihe, ziel[0], ziel[1]);
    }

    private int[] zielFeldFinden(PacManWorld welt)
    {
        // Jeder Geist hat ein anderes Ziel. Pacman kommt aus PacManWorld.java.
        Pacman pacman = welt.gibPacman();
        int pacmanSpalte = pacman.spalte;
        int pacmanReihe = pacman.reihe;

        if (geistArt == ROTER_GEIST) {
            return new int[] {pacmanSpalte, pacmanReihe};
        }
        if (geistArt == PINKER_GEIST) {
            return new int[] {
                pacmanSpalte + pacman.getDirectionX() * 4,
                pacmanReihe + pacman.getDirectionY() * 4
            };
        }
        if (geistArt == BLAUER_GEIST) {
            return new int[] {
                pacmanSpalte + pacman.getDirectionX() * 2,
                pacmanReihe + pacman.getDirectionY() * 2
            };
        }

        return zielFuerOrangenGeist(welt, pacmanSpalte, pacmanReihe);
    }

    private int[] zielFuerOrangenGeist(PacManWorld welt, int pacmanSpalte, int pacmanReihe)
    {
        int entfernungZuPacman = entfernungQuadrat(spalte, reihe, pacmanSpalte, pacmanReihe);
        if (entfernungZuPacman < 36) {
            return new int[] {1, welt.gibLabyrinthReihen() - 2};
        }

        return new int[] {pacmanSpalte, pacmanReihe};
    }

    private int entfernungQuadrat(int spalteA, int reiheA, int spalteB, int reiheB)
    {
        // Kleine Zahl = naeher.
        int spaltenAbstand = spalteA - spalteB;
        int reihenAbstand = reiheA - reiheB;
        return spaltenAbstand * spaltenAbstand + reihenAbstand * reihenAbstand;
    }

    private String bildNameFuerGeist()
    {
        // Bild nach Geist-Nummer.
        if (geistArt == ROTER_GEIST) {
            return "ghost-blinky.png";
        }
        if (geistArt == PINKER_GEIST) {
            return "ghost-pinky.png";
        }
        if (geistArt == BLAUER_GEIST) {
            return "ghost-inky.png";
        }
        return "ghost-clyde.png";
    }

    private void geistBildSetzen()
    {
        // Ghost sprite aus images.
        setImage(bildNameFuerGeist());
        getImage().scale(PacManWorld.TILE_GROESSE - 4, PacManWorld.TILE_GROESSE - 4);
    }
}
