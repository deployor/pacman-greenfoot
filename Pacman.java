import greenfoot.*;

public class Pacman extends GridMover
{
    private static final int SPEED = 4;

    private final int startColumn;
    private final int startRow;

    private int wantedDirectionX;
    private int wantedDirectionY;

    public Pacman(int startColumn, int startRow)
    {
        super(startColumn, startRow, SPEED);
        this.startColumn = startColumn;
        this.startRow = startRow;
        directionX = 1;
        directionY = 0;
        wantedDirectionX = 1;
        wantedDirectionY = 0;
        setPacmanImage("pacman-right.png");
    }

    public void act()
    {
        PacManWorld world = (PacManWorld)getWorld();
        if (!world.isGameRunning()) {
            return;
        }

        readKeys();
        continueMoving();

        if (!isMoving()) {
            chooseNextMove();
        }

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
        resetGridPosition(startColumn, startRow);
        directionX = 1;
        directionY = 0;
        wantedDirectionX = 1;
        wantedDirectionY = 0;
        setPacmanImage("pacman-right.png");
    }

    private void readKeys()
    {
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) {
            wantedDirectionX = 0;
            wantedDirectionY = -1;
        }
        else if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) {
            wantedDirectionX = 0;
            wantedDirectionY = 1;
        }
        else if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            wantedDirectionX = -1;
            wantedDirectionY = 0;
        }
        else if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            wantedDirectionX = 1;
            wantedDirectionY = 0;
        }
    }

    private void chooseNextMove()
    {
        if (canStartMoving(wantedDirectionX, wantedDirectionY)) {
            startMoving(wantedDirectionX, wantedDirectionY);
            updateImageForDirection();
        }
        else if (canStartMoving(directionX, directionY)) {
            startMoving(directionX, directionY);
        }
    }

    private void eatDots(PacManWorld world)
    {
        PacDots dot = (PacDots)getOneIntersectingObject(PacDots.class);
        if (dot != null) {
            world.eatPellet(dot);
        }
    }

    private void checkGhostTouch(PacManWorld world)
    {
        if (isTouching(Ghost.class)) {
            world.pacmanWasCaught();
        }
    }

    private void updateImageForDirection()
    {
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
