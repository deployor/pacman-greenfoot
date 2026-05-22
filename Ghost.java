import greenfoot.*;

public class Ghost extends GridMover
{
    // Geister bewegen sich auch auf dem Raster, aber langsamer als Pacman.
    private static final int GESCHWINDIGKEIT = 2;
    private static final int ROTER_GEIST = 0;
    private static final int PINKER_GEIST = 1;
    private static final int BLAUER_GEIST = 2;
    private static final int ORANGER_GEIST = 3;

    private final int startSpalte;
    private final int startReihe;
    private final int geistArt;
    private final Color koerperFarbe;

    public Ghost(int startSpalte, int startReihe, int geistArt)
    {
        super(startSpalte, startReihe, GESCHWINDIGKEIT);
        this.startSpalte = startSpalte;
        this.startReihe = startReihe;
        this.geistArt = geistArt;
        koerperFarbe = farbeFuerGeist();
        directionX = 1;
        directionY = 0;
        zeichneGeist();
    }

    public void act()
    {
        // Geister warten am Anfang und nach einem Treffer kurz.
        PacManWorld welt = (PacManWorld)getWorld();
        if (!welt.canGhostMove()) {
            return;
        }

        continueMoving();

        if (!isMoving()) {
            waehleNaechstenWeg(welt);
        }
    }

    public void resetToStart()
    {
        // Geist zurueck in sein Startfeld setzen.
        resetGridPosition(startSpalte, startReihe);
        directionX = 1;
        directionY = 0;
    }

    private void waehleNaechstenWeg(PacManWorld welt)
    {
        // Von allen moeglichen Wegen wird der beste zum Ziel genommen.
        int[] ziel = zielFeldFinden(welt);
        int besteRichtungX = 0;
        int besteRichtungY = 0;
        int besteEntfernung = Integer.MAX_VALUE;
        boolean wegGefunden = false;

        int[][] richtungen = alleRichtungen();
        for (int i = 0; i < richtungen.length; i++) {
            int richtungX = richtungen[i][0];
            int richtungY = richtungen[i][1];

            if (!istGuterWeg(welt, richtungX, richtungY)) {
                continue;
            }

            int entfernung = entfernungNachSchritt(welt, richtungX, richtungY, ziel);
            if (!wegGefunden || entfernung < besteEntfernung) {
                besteRichtungX = richtungX;
                besteRichtungY = richtungY;
                besteEntfernung = entfernung;
                wegGefunden = true;
            }
        }

        if (wegGefunden) {
            startMoving(besteRichtungX, besteRichtungY);
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

    private boolean istGuterWeg(PacManWorld welt, int richtungX, int richtungY)
    {
        if (!canStartMoving(richtungX, richtungY)) {
            return false;
        }

        if (istRueckweg(richtungX, richtungY) && hatWegOhneUmdrehen(welt)) {
            return false;
        }

        return true;
    }

    private boolean hatWegOhneUmdrehen(PacManWorld welt)
    {
        // Wenn es einen anderen Weg gibt, soll der Geist nicht direkt umdrehen.
        int[][] richtungen = alleRichtungen();

        for (int i = 0; i < richtungen.length; i++) {
            int richtungX = richtungen[i][0];
            int richtungY = richtungen[i][1];

            if (!istRueckweg(richtungX, richtungY) && canStartMoving(richtungX, richtungY)) {
                return true;
            }
        }

        return false;
    }

    private boolean istRueckweg(int richtungX, int richtungY)
    {
        return richtungX == -directionX && richtungY == -directionY;
    }

    private int entfernungNachSchritt(PacManWorld welt, int richtungX, int richtungY, int[] ziel)
    {
        int naechsteSpalte = welt.wrapColumn(column + richtungX);
        int naechsteReihe = row + richtungY;
        return entfernungQuadrat(naechsteSpalte, naechsteReihe, ziel[0], ziel[1]);
    }

    private int[] zielFeldFinden(PacManWorld welt)
    {
        // Jeder Geist jagt Pacman anders.
        Pacman pacman = welt.getPacman();
        int pacmanSpalte = pacman.column;
        int pacmanReihe = pacman.row;

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
        int entfernungZuPacman = entfernungQuadrat(column, row, pacmanSpalte, pacmanReihe);
        if (entfernungZuPacman < 36) {
            return new int[] {1, welt.getMazeRows() - 2};
        }

        return new int[] {pacmanSpalte, pacmanReihe};
    }

    private int entfernungQuadrat(int spalteA, int reiheA, int spalteB, int reiheB)
    {
        // Rechnung, um zu sehen, welches Feld naeher am Ziel ist.
        int spaltenAbstand = spalteA - spalteB;
        int reihenAbstand = reiheA - reiheB;
        return spaltenAbstand * spaltenAbstand + reihenAbstand * reihenAbstand;
    }

    private Color farbeFuerGeist()
    {
        // Jeder Geister-Typ bekommt seine feste Farbe.
        if (geistArt == ROTER_GEIST) {
            return Color.RED;
        }
        if (geistArt == PINKER_GEIST) {
            return Color.PINK;
        }
        if (geistArt == BLAUER_GEIST) {
            return Color.CYAN;
        }
        return Color.ORANGE;
    }

    private void zeichneGeist()
    {
        // Geist-Bild, fest, aus ovalen.
        int groesse = PacManWorld.TILE_SIZE - 4;
        GreenfootImage bild = new GreenfootImage(groesse, groesse);
        bild.setColor(koerperFarbe);
        bild.fillOval(0, 0, groesse, groesse);
        bild.fillRect(0, groesse / 2, groesse, groesse / 2);
        bild.setColor(Color.WHITE);
        bild.fillOval(groesse / 5, groesse / 4, groesse / 5, groesse / 5);
        bild.fillOval(groesse * 3 / 5, groesse / 4, groesse / 5, groesse / 5);
        bild.setColor(Color.BLUE);
        bild.fillOval(groesse / 4, groesse / 3, groesse / 10, groesse / 10);
        bild.fillOval(groesse * 13 / 20, groesse / 3, groesse / 10, groesse / 10);
        setImage(bild);
    }
}
