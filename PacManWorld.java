import greenfoot.*;

public class PacManWorld extends World
{
    public static int breite;
    public static int hoehe;
    public static double musik;
    public static int Volume;

    public static final int TILE_SIZE = 32;
    public static final int TOP_BAR_HEIGHT = 48;

    private static final int START_WAIT_TIME = 150;
    private static final String[] MAZE = {
        "#####################",
        "#.........#.........#",
        "#.###.###.#.###.###.#",
        "#o#...............#o#",
        "#.#.###.#####.###.#.#",
        "#...#.........#.....#",
        "###.#.### ###.#.#####",
        "#.....#  G  #.....#.#",
        "#.###.# ### #.###.#.#",
        " .....#G P G#.....  ",
        "#.###.# ### #.###.#.#",
        "#.....#  G  #.....#.#",
        "###.#.#######.#.###.#",
        "#...#.........#.....#",
        "#.#.###.#####.###.#.#",
        "#o#...............#o#",
        "#.###.###.#.###.###.#",
        "#.........#.........#",
        "#####################"
    };

    private int score;
    private int lives;
    private int pelletsLeft;
    private int ghostCount;
    private int waitTime;
    private boolean gameOver;
    private boolean won;
    private Pacman pacman;

    public PacManWorld(int breite, int hoehe, double musik, int Volume)
    {
        super(MAZE[0].length() * TILE_SIZE, MAZE.length * TILE_SIZE + TOP_BAR_HEIGHT, 1);

        PacManWorld.breite = getWidth();
        PacManWorld.hoehe = getHeight();
        PacManWorld.musik = musik;
        PacManWorld.Volume = Volume;

        score = 0;
        lives = 3;
        pelletsLeft = 0;
        ghostCount = 0;
        waitTime = START_WAIT_TIME;
        gameOver = false;
        won = false;

        setPaintOrder(settings.class, Pacman.class, Ghost.class, PacDots.class, PowerPellet.class, Wall.class);
        buildMaze();
        addObject(new settings(), getWidth() - 28, 24);
        updateScoreBoard();
    }

    public void act()
    {
        updateStartText();

        if (!gameOver && !won && pelletsLeft == 0) {
            won = true;
            showText("Gewonnen! Druecke R", getWidth() / 2, 25);
        }

        if ((gameOver || won) && Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new PacManWorld(getWidth(), getHeight(), musik, Volume));
        }
    }

    public boolean isGameRunning()
    {
        return !gameOver && !won;
    }

    public boolean canGhostMove()
    {
        return isGameRunning() && waitTime == 0;
    }

    public Pacman getPacman()
    {
        return pacman;
    }

    public int getMazeRows()
    {
        return MAZE.length;
    }

    public int getTileCenterX(int column)
    {
        return wrapColumn(column) * TILE_SIZE + TILE_SIZE / 2;
    }

    public int getTileCenterY(int row)
    {
        return TOP_BAR_HEIGHT + row * TILE_SIZE + TILE_SIZE / 2;
    }

    public int wrapColumn(int column)
    {
        if (column < 0) {
            return MAZE[0].length() - 1;
        }
        if (column >= MAZE[0].length()) {
            return 0;
        }
        return column;
    }

    public boolean isOpenTile(int column, int row)
    {
        if (row < 0 || row >= MAZE.length) {
            return false;
        }

        column = wrapColumn(column);
        return MAZE[row].charAt(column) != '#';
    }

    public void eatPellet(PacDots pellet)
    {
        removeObject(pellet);
        score += pellet.getPointValue();
        pelletsLeft--;
        updateScoreBoard();
    }

    public void pacmanWasCaught()
    {
        if (!canGhostMove()) {
            return;
        }

        lives--;
        updateScoreBoard();

        if (lives <= 0) {
            gameOver = true;
            showText("Verloren! Druecke R", getWidth() / 2, 25);
            removeObject(pacman);
            return;
        }

        resetActors();
        waitTime = START_WAIT_TIME / 2;
    }

    private void updateStartText()
    {
        if (gameOver || won) {
            return;
        }

        if (waitTime > 0) {
            waitTime--;
            int seconds = waitTime / 50 + 1;
            showText("Geister warten: " + seconds, getWidth() / 2, 25);
        }
        else {
            showText("", getWidth() / 2, 25);
        }
    }

    private void buildMaze()
    {
        for (int row = 0; row < MAZE.length; row++) {
            for (int column = 0; column < MAZE[row].length(); column++) {
                addTile(MAZE[row].charAt(column), column, row);
            }
        }
    }

    private void addTile(char tile, int column, int row)
    {
        int x = getTileCenterX(column);
        int y = getTileCenterY(row);

        if (tile == '#') {
            addObject(new Wall(), x, y);
        }
        else if (tile == '.') {
            addObject(new PacDots(), x, y);
            pelletsLeft++;
        }
        else if (tile == 'o') {
            addObject(new PowerPellet(), x, y);
            pelletsLeft++;
        }
        else if (tile == 'P') {
            pacman = new Pacman(column, row);
            addObject(pacman, x, y);
        }
        else if (tile == 'G') {
            addObject(new Ghost(column, row, ghostCount), x, y);
            ghostCount++;
        }
    }

    private void resetActors()
    {
        pacman.resetToStart();

        for (Object object : getObjects(Ghost.class)) {
            Ghost ghost = (Ghost)object;
            ghost.resetToStart();
        }
    }

    private void updateScoreBoard()
    {
        showText("Punkte: " + score, 80, 25);
        showText("Leben: " + lives, 200, 25);
        showText("Punkte verbleibend: " + pelletsLeft, 360, 25);
    }
}
