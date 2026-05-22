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
    private boolean usesTunnel;

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
        usesTunnel = false;
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

        int oldColumn = column;
        column = world.wrapColumn(column + directionX);
        row = row + directionY;

        usesTunnel = directionY == 0 && Math.abs(column - oldColumn) > 1;
        targetX = world.getTileCenterX(column);
        targetY = world.getTileCenterY(row);
    }

    protected void continueMoving()
    {
        if (!isMoving()) {
            return;
        }

        if (usesTunnel) {
            moveThroughTunnel();
            return;
        }

        int nextX = moveValueToward(getX(), targetX);
        int nextY = moveValueToward(getY(), targetY);
        setLocation(nextX, nextY);
    }

    private void moveThroughTunnel()
    {
        PacManWorld world = (PacManWorld)getWorld();
        int nextX = getX() + directionX * speed;

        if (nextX < -PacManWorld.TILE_SIZE / 2) {
            nextX = world.getWidth() + PacManWorld.TILE_SIZE / 2;
        }
        else if (nextX > world.getWidth() + PacManWorld.TILE_SIZE / 2) {
            nextX = -PacManWorld.TILE_SIZE / 2;
        }

        if (directionX > 0 && nextX >= targetX && nextX < world.getWidth()) {
            nextX = targetX;
            usesTunnel = false;
        }
        else if (directionX < 0 && nextX <= targetX && nextX > 0) {
            nextX = targetX;
            usesTunnel = false;
        }

        setLocation(nextX, targetY);
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
