import greenfoot.*;

public class GridMover extends Actor
{
    protected int column;
    protected int row;
    protected int directionX;
    protected int directionY;

    private int targetX;
    private int targetY;
    private int speed;

    public GridMover(int startColumn, int startRow, int speed)
    {
        column = startColumn;
        row = startRow;
        this.speed = speed;
    }

    protected void addedToWorld(World world)
    {
        PacManWorld pacWorld = (PacManWorld)world;
        setLocation(pacWorld.getTileCenterX(column), pacWorld.getTileCenterY(row));
        targetX = getX();
        targetY = getY();
    }

    public boolean isMoving()
    {
        return getX() != targetX || getY() != targetY;
    }

    public void resetGridPosition(int newColumn, int newRow)
    {
        PacManWorld world = (PacManWorld)getWorld();
        column = newColumn;
        row = newRow;
        targetX = world.getTileCenterX(column);
        targetY = world.getTileCenterY(row);
        setLocation(targetX, targetY);
    }

    protected boolean canStartMoving(int nextDirectionX, int nextDirectionY)
    {
        PacManWorld world = (PacManWorld)getWorld();
        return world.isOpenTile(column + nextDirectionX, row + nextDirectionY);
    }

    protected void startMoving(int nextDirectionX, int nextDirectionY)
    {
        if (!canStartMoving(nextDirectionX, nextDirectionY)) {
            return;
        }

        PacManWorld world = (PacManWorld)getWorld();
        directionX = nextDirectionX;
        directionY = nextDirectionY;
        column = world.wrapColumn(column + directionX);
        row = row + directionY;
        targetX = world.getTileCenterX(column);
        targetY = world.getTileCenterY(row);
    }

    protected void continueMoving()
    {
        if (!isMoving()) {
            return;
        }

        int nextX = moveValueToward(getX(), targetX);
        int nextY = moveValueToward(getY(), targetY);
        setLocation(nextX, nextY);
    }

    private int moveValueToward(int value, int target)
    {
        if (value < target) {
            return Math.min(value + speed, target);
        }
        if (value > target) {
            return Math.max(value - speed, target);
        }
        return value;
    }
}
