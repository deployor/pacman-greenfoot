import greenfoot.*;

public class PacManWorld extends World
{
    // Die World ist das Spielfeld und verwaltet Punkte, Leben und das Labyrinth.
    public static int breite;
    public static int hoehe;
    public static double musik;
    public static int Volume;

    public static final int TILE_SIZE = 32;
    public static final int TOP_BAR_HEIGHT = 48;

    private static final int START_WAIT_TIME = 150;

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

    private static final int MAZE_COLUMNS = MAZE[0].length();

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
        super(MAZE_COLUMNS * TILE_SIZE, MAZE.length * TILE_SIZE + TOP_BAR_HEIGHT, 1);

        PacManWorld.breite = getWidth();
        PacManWorld.hoehe = getHeight();
        PacManWorld.musik = musik;
        PacManWorld.Volume = Volume;

        punkte = 0;
        leben = 3;
        punkteUebrig = 0;
        geisterZaehler = 0;
        warteZeit = START_WAIT_TIME;
        spielVorbei = false;
        gewonnen = false;

        setPaintOrder(settings.class, Pacman.class, Ghost.class, PacDots.class, PowerPellet.class, Wall.class);
        buildMaze();
        addObject(new settings(), getWidth() - 28, 24);
        updateScoreBoard();
    }

    public void act()
    {
        // Wird von Greenfoot immer wieder automatisch aufgerufen.
        updateStartText();

        if (!spielVorbei && !gewonnen && punkteUebrig == 0) {
            gewonnen = true;
            showText("Gewonnen! Druecke R", getWidth() / 2, 25);
        }

        if ((spielVorbei || gewonnen) && Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new PacManWorld(getWidth(), getHeight(), musik, Volume));
        }
    }

    public boolean isGameRunning()
    {
        // Pacman und Geister bewegen sich nur, solange das Spiel laeuft.
        return !spielVorbei && !gewonnen;
    }

    public boolean spielLaeuft()
    {
        return isGameRunning();
    }

    public boolean canGhostMove()
    {
        // Am Anfang sollen die Geister kurz warten.
        return isGameRunning() && warteZeit == 0;
    }

    public boolean geisterDuerfenLaufen()
    {
        return canGhostMove();
    }

    public Pacman getPacman()
    {
        return pacman;
    }

    public Pacman gibPacman()
    {
        return pacman;
    }

    public int getMazeRows()
    {
        return MAZE.length;
    }

    public int gibLabyrinthReihen()
    {
        return MAZE.length;
    }

    public int getTileCenterX(int column)
    {
        // Rechnet eine Spalte im Labyrinth in eine X-Position um.
        return wrapColumn(column) * TILE_SIZE + TILE_SIZE / 2;
    }

    public int gibKachelMitteX(int spalte)
    {
        return getTileCenterX(spalte);
    }

    public int getTileCenterY(int row)
    {
        // Rechnet eine Reihe im Labyrinth in eine Y-Position um.
        return TOP_BAR_HEIGHT + row * TILE_SIZE + TILE_SIZE / 2;
    }

    public int gibKachelMitteY(int reihe)
    {
        return getTileCenterY(reihe);
    }

    public int wrapColumn(int column)
    {
        // Fuer den Tunnel links/rechts.
        if (column < 0) {
            return MAZE_COLUMNS - 1;
        }
        if (column >= MAZE_COLUMNS) {
            return 0;
        }
        return column;
    }

    public int spalteUmwickeln(int spalte)
    {
        return wrapColumn(spalte);
    }

    public boolean isOpenTile(int column, int row)
    {
        // Nur Waende blockieren den Weg.
        if (row < 0 || row >= MAZE.length) {
            return false;
        }

        return gibLabyrinthZeichen(wrapColumn(column), row) != '#';
    }

    public boolean istKachelFrei(int spalte, int reihe)
    {
        return isOpenTile(spalte, reihe);
    }

    public void eatPellet(PacDots pellet)
    {
        // Punkt entfernen und Score erhoehen.
        removeObject(pellet);
        punkte += pellet.getPointValue();
        punkteUebrig--;
        updateScoreBoard();
    }

    public void punktEinsammeln(PacDots punkt)
    {
        eatPellet(punkt);
    }

    public void pacmanWasCaught()
    {
        // Wenn Pacman einen Geist beruehrt, verliert er ein Leben.
        if (!canGhostMove()) {
            return;
        }

        leben--;
        updateScoreBoard();

        if (leben <= 0) {
            spielVorbei = true;
            showText("Verloren! Druecke R", getWidth() / 2, 25);
            removeObject(pacman);
            return;
        }

        resetActors();
        warteZeit = START_WAIT_TIME / 2;
    }

    public void pacmanWurdeGefangen()
    {
        pacmanWasCaught();
    }

    private void updateStartText()
    {
        // Zeigt den Countdown an, bevor die Geister loslaufen.
        if (spielVorbei || gewonnen) {
            return;
        }

        if (warteZeit > 0) {
            warteZeit--;
            int seconds = warteZeit / 50 + 1;
            showText("Geister warten: " + seconds, getWidth() / 2, 25);
        }
        else {
            showText("", getWidth() / 2, 25);
        }
    }

    private void buildMaze()
    {
        // Baut die Welt aus dem Text-Labyrinth oben.
        for (int row = 0; row < MAZE.length; row++) {
            for (int column = 0; column < MAZE_COLUMNS; column++) {
                addTile(gibLabyrinthZeichen(column, row), column, row);
            }
        }
    }

    private char gibLabyrinthZeichen(int spalte, int reihe)
    {
        // Kuerzere Tunnel-Zeilen werden wie leerer Weg behandelt.
        if (spalte < 0 || spalte >= MAZE[reihe].length()) {
            return ' ';
        }
        return MAZE[reihe].charAt(spalte);
    }

    private void addTile(char tile, int column, int row)
    {
        // Erstellt das passende Objekt fuer ein Zeichen im Labyrinth.
        int x = getTileCenterX(column);
        int y = getTileCenterY(row);

        if (tile == '#') {
            addObject(new Wall(), x, y);
        }
        else if (tile == '.') {
            addObject(new PacDots(), x, y);
            punkteUebrig++;
        }
        else if (tile == 'o') {
            addObject(new PowerPellet(), x, y);
            punkteUebrig++;
        }
        else if (tile == 'P') {
            pacman = new Pacman(column, row);
            addObject(pacman, x, y);
        }
        else if (tile == 'G') {
            addObject(new Ghost(column, row, geisterZaehler), x, y);
            geisterZaehler++;
        }
    }

    private void resetActors()
    {
        // Nach einem Treffer starten Pacman und Geister wieder neu.
        pacman.resetToStart();

        for (Object object : getObjects(Ghost.class)) {
            Ghost ghost = (Ghost)object;
            ghost.resetToStart();
        }
    }

    private void updateScoreBoard()
    {
        // Text oben im Spielfeld.
        showText("Punkte: " + punkte, 80, 25);
        showText("Leben: " + leben, 200, 25);
        showText("Punkte verbleibend: " + punkteUebrig, 360, 25);
    }
}
