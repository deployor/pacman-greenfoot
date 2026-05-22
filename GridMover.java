import greenfoot.*;

public class GridMover extends Actor
{
    // Basisklasse fuer alles, was sich auf dem Kachel-Raster bewegt.
    protected int column;
    protected int row;
    protected int directionX;
    protected int directionY;

    private int zielX;
    private int zielY;
    private int geschwindigkeit;
    private boolean tunnelAktiv;
    private boolean istSchonAufAndereSeite;

    public GridMover(int startSpalte, int startReihe, int geschwindigkeit)
    {
        column = startSpalte;
        row = startReihe;
        this.geschwindigkeit = geschwindigkeit;
    }

    protected void addedToWorld(World world)
    {
        // Beim Einfuegen direkt auf richtige kachel setzen.
        setGridPosition(column, row);
    }

    public boolean isMoving()
    {
        // Solange das sprite ihr Ziel noch nicht erreicht hat, bewegt sie sich.
        return getX() != zielX || getY() != zielY;
    }

    public void resetGridPosition(int newColumn, int newRow)
    {
        column = newColumn;
        row = newRow;
        tunnelAktiv = false;
        istSchonAufAndereSeite = false;
        setGridPosition(column, row);
    }

    protected boolean canStartMoving(int nextDirectionX, int nextDirectionY)
    {
        // Erst pruefen, ob die naechste Kachel frei ist.
        PacManWorld world = (PacManWorld)getWorld();
        return world.isOpenTile(column + nextDirectionX, row + nextDirectionY);
    }

    protected void startMoving(int nextDirectionX, int nextDirectionY)
    {
        // Eine neue Bewegung geht immer genau zur naechsten Kachel.
        if (!canStartMoving(nextDirectionX, nextDirectionY)) {
            return;
        }

        int oldColumn = column;
        PacManWorld world = (PacManWorld)getWorld();

        directionX = nextDirectionX;
        directionY = nextDirectionY;
        column = world.wrapColumn(column + directionX);
        row = row + directionY;

        tunnelAktiv = directionY == 0 && Math.abs(column - oldColumn) > 1;
        istSchonAufAndereSeite = false;
        zielX = world.getTileCenterX(column);
        zielY = world.getTileCenterY(row);
    }

    protected void continueMoving()
    {
        // Bewegt die Figur Schritt fuer Schritt zum Ziel.
        if (!isMoving()) {
            return;
        }

        if (tunnelAktiv) {
            moveThroughTunnel();
        }
        else {
            moveNormally();
        }
    }

    private void setGridPosition(int newColumn, int newRow)
    {
        // Setzt die Figur sofort auf eine Kachelmitte.
        PacManWorld world = (PacManWorld)getWorld();
        zielX = world.getTileCenterX(newColumn);
        zielY = world.getTileCenterY(newRow);
        setLocation(zielX, zielY);
    }

    private void moveNormally()
    {
        // Normale Bewegung ohne Tunnel.
        int nextX = moveValueToward(getX(), zielX);
        int nextY = moveValueToward(getY(), zielY);
        setLocation(nextX, nextY);
    }

    private void moveThroughTunnel()
    {
        // Erst rauslaufen, dann auf der anderen Seite reinkommen.
        PacManWorld world = (PacManWorld)getWorld();
        int nextX = getX() + directionX * geschwindigkeit;
        int outsideLeft = -PacManWorld.TILE_SIZE / 2;
        int outsideRight = world.getWidth() + PacManWorld.TILE_SIZE / 2;

        if (!istSchonAufAndereSeite && directionX < 0 && nextX <= outsideLeft) {
            nextX = outsideRight;
            istSchonAufAndereSeite = true;
        }
        else if (!istSchonAufAndereSeite && directionX > 0 && nextX >= outsideRight) {
            nextX = outsideLeft;
            istSchonAufAndereSeite = true;
        }

        if (istSchonAufAndereSeite && directionX < 0 && nextX <= zielX) {
            stopTunnelAt(zielX);
            return;
        }
        if (istSchonAufAndereSeite && directionX > 0 && nextX >= zielX) {
            stopTunnelAt(zielX);
            return;
        }

        setLocation(nextX, zielY);
    }

    private void stopTunnelAt(int x)
    {
        // Tunnel ist fertig, Figur sitzt wieder auf einer Kachel.
        tunnelAktiv = false;
        istSchonAufAndereSeite = false;
        setLocation(x, zielY);
    }

    private int moveValueToward(int value, int target)
    {
        // Hilfsmethode: bewegt eine Zahl langsam zum Zielwert.
        if (value < target) {
            return Math.min(value + geschwindigkeit, target);
        }
        if (value > target) {
            return Math.max(value - geschwindigkeit, target);
        }
        return value;
    }
}
