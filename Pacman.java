import greenfoot.*;

public class Pacman extends GridMover
{
    // Pacman merkt sich eine Wunschrichtung und laeuft bis zur naechsten Wand.
    private static final int GESCHWINDIGKEIT = 4;

    private final int startSpalte;
    private final int startReihe;

    private int wunschRichtungX;
    private int wunschRichtungY;

    public Pacman(int startSpalte, int startReihe)
    {
        super(startSpalte, startReihe, GESCHWINDIGKEIT);
        this.startSpalte = startSpalte;
        this.startReihe = startReihe;
        directionX = 1;
        directionY = 0;
        wunschRichtungX = 1;
        wunschRichtungY = 0;
        setPacmanImage("pacman-right.png");
    }

    public void act()
    {
        // Reihenfolge: Eingabe lesen, bewegen, Punkte essen, Geist pruefen.
        PacManWorld world = (PacManWorld)getWorld();
        if (!world.isGameRunning()) {
            return;
        }

        handleMovement();
        eatDots(world);
        checkGhostTouch(world);
    }

    public int getDirectionX()
    {
        return directionX;
    }

    public int getDirectionY()
    {
        return directionY;
    }

    public void resetToStart()
    {
        // Wird nach einem verlorenen Leben benutzt.
        resetGridPosition(startSpalte, startReihe);
        directionX = 1;
        directionY = 0;
        wunschRichtungX = 1;
        wunschRichtungY = 0;
        setPacmanImage("pacman-right.png");
    }

    private void readKeys()
    {
        // Die gedrueckte Taste wird nur als Wunschrichtung gespeichert.
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

    private void handleMovement()
    {
        // Bewegung ist in einer eigenen Methode, damit act() kurz bleibt.
        readKeys();
        continueMoving();

        if (!isMoving()) {
            chooseNextMove();
        }
    }

    private void chooseNextMove()
    {
        // Gewuenschte Richtung hat Vorrang, sonst weiter geradeaus.
        if (canStartMoving(wunschRichtungX, wunschRichtungY)) {
            startMoving(wunschRichtungX, wunschRichtungY);
            updateImageForDirection();
        }
        else if (canStartMoving(directionX, directionY)) {
            startMoving(directionX, directionY);
        }
    }

    private void eatDots(PacManWorld world)
    {
        // Wenn Pacman einen Punkt beruehrt, wird er eingesammelt.
        PacDots dot = (PacDots)getOneIntersectingObject(PacDots.class);
        if (dot != null) {
            world.eatPellet(dot);
        }
    }

    private void checkGhostTouch(PacManWorld world)
    {
        // Beruehrt Pacman einen aktiven Geist, verliert er ein Leben.
        if (isTouching(Ghost.class)) {
            world.pacmanWasCaught();
        }
    }

    private void updateImageForDirection()
    {
        // Bild passend zur Laufrichtung setzen.
        if (directionX < 0) {
            setPacmanImage("pacman-left.png");
        }
        else if (directionX > 0) {
            setPacmanImage("pacman-right.png");
        }
        else if (directionY < 0) {
            setPacmanImage("pacman-up.png");
        }
        else if (directionY > 0) {
            setPacmanImage("pacman-down.png");
        }
    }

    private void setPacmanImage(String imageName)
    {
        setImage(imageName);
        getImage().scale(PacManWorld.TILE_SIZE - 6, PacManWorld.TILE_SIZE - 6);
    }
}
