import greenfoot.*;

public class Ghost extends GridMover
{
    public static final int RED = 0;
    public static final int PINK = 1;
    public static final int CYAN = 2;
    public static final int ORANGE = 3;

    private static final int SPEED = 2;

    private final int startColumn;
    private final int startRow;
    private final int ghostType;
    private final Color bodyColor;

    public Ghost(int startColumn, int startRow, int ghostType)
    {
        super(startColumn, startRow, SPEED);
        this.startColumn = startColumn;
        this.startRow = startRow;
        this.ghostType = ghostType;
        bodyColor = colorForType(ghostType);
        directionX = 1;
        directionY = 0;
        makeGhostImage();
    }

    public void act()
    {
        PacManWorld world = (PacManWorld)getWorld();
        if (!world.canGhostMove()) {
            return;
        }

        continueMoving();

        if (!isMoving()) {
            chooseNextMove(world);
        }
    }

    public void resetToStart()
    {
        resetGridPosition(startColumn, startRow);
        directionX = 1;
        directionY = 0;
    }

    private void chooseNextMove(PacManWorld world)
    {
        int[] target = getTargetTile(world);
        int[][] moves = {
            {1, 0},
            {-1, 0},
            {0, -1},
            {0, 1}
        };

        int bestX = 0;
        int bestY = 0;
        int bestDistance = Integer.MAX_VALUE;
        boolean foundMove = false;

        for (int i = 0; i < moves.length; i++) {
            int moveX = moves[i][0];
            int moveY = moves[i][1];

            if (!canStartMoving(moveX, moveY)) {
                continue;
            }
            if (isReverseMove(moveX, moveY) && hasOtherMove(world)) {
                continue;
            }

            int nextColumn = world.wrapColumn(column + moveX);
            int nextRow = row + moveY;
            int distance = distanceSquared(nextColumn, nextRow, target[0], target[1]);

            if (!foundMove || distance < bestDistance) {
                bestX = moveX;
                bestY = moveY;
                bestDistance = distance;
                foundMove = true;
            }
        }

        if (foundMove) {
            startMoving(bestX, bestY);
        }
    }

    private boolean hasOtherMove(PacManWorld world)
    {
        int otherMoves = 0;

        if (world.isOpenTile(column + 1, row) && !(directionX == -1 && directionY == 0)) {
            otherMoves++;
        }
        if (world.isOpenTile(column - 1, row) && !(directionX == 1 && directionY == 0)) {
            otherMoves++;
        }
        if (world.isOpenTile(column, row - 1) && !(directionX == 0 && directionY == 1)) {
            otherMoves++;
        }
        if (world.isOpenTile(column, row + 1) && !(directionX == 0 && directionY == -1)) {
            otherMoves++;
        }

        return otherMoves > 0;
    }

    private boolean isReverseMove(int moveX, int moveY)
    {
        return moveX == -directionX && moveY == -directionY;
    }

    private int[] getTargetTile(PacManWorld world)
    {
        Pacman pacman = world.getPacman();
        int pacmanColumn = pacman.column;
        int pacmanRow = pacman.row;

        if (ghostType == RED) {
            return new int[] {pacmanColumn, pacmanRow};
        }
        if (ghostType == PINK) {
            return new int[] {
                pacmanColumn + pacman.getDirectionX() * 4,
                pacmanRow + pacman.getDirectionY() * 4
            };
        }
        if (ghostType == CYAN) {
            return new int[] {
                pacmanColumn + pacman.getDirectionX() * 2,
                pacmanRow + pacman.getDirectionY() * 2
            };
        }

        int distanceFromPacman = distanceSquared(column, row, pacmanColumn, pacmanRow);
        if (distanceFromPacman < 36) {
            return new int[] {1, world.getMazeRows() - 2};
        }

        return new int[] {pacmanColumn, pacmanRow};
    }

    private int distanceSquared(int columnA, int rowA, int columnB, int rowB)
    {
        int columnDistance = columnA - columnB;
        int rowDistance = rowA - rowB;
        return columnDistance * columnDistance + rowDistance * rowDistance;
    }

    private Color colorForType(int type)
    {
        if (type == RED) {
            return Color.RED;
        }
        if (type == PINK) {
            return Color.PINK;
        }
        if (type == CYAN) {
            return Color.CYAN;
        }
        return Color.ORANGE;
    }

    private void makeGhostImage()
    {
        int size = PacManWorld.TILE_SIZE - 4;
        GreenfootImage image = new GreenfootImage(size, size);
        image.setColor(bodyColor);
        image.fillOval(0, 0, size, size);
        image.fillRect(0, size / 2, size, size / 2);
        image.setColor(Color.WHITE);
        image.fillOval(size / 5, size / 4, size / 5, size / 5);
        image.fillOval(size * 3 / 5, size / 4, size / 5, size / 5);
        image.setColor(Color.BLUE);
        image.fillOval(size / 4, size / 3, size / 10, size / 10);
        image.fillOval(size * 13 / 20, size / 3, size / 10, size / 10);
        setImage(image);
    }
}
