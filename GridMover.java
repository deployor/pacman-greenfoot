import greenfoot.*;

public class GridMover extends Actor
{
    // Diese Klasse bewegt Pacman und Ghosts tile fuer tile.
    protected int spalte;
    protected int reihe;
    protected int richtungX;
    protected int richtungY;

    private int zielX;
    private int zielY;
    private int geschwindigkeit;
    private boolean tunnelAktiv;

    public GridMover(int startSpalte, int startReihe, int geschwindigkeit)
    {
        spalte = startSpalte;
        reihe = startReihe;
        this.geschwindigkeit = geschwindigkeit;
    }

    protected void addedToWorld(World world)
    {
        // Sprite direkt aufs Start-tile setzen.
        setzeTilePosition(spalte, reihe);
    }

    public boolean bewegtSich()
    {
        // Noch nicht am Ziel.
        return getX() != zielX || getY() != zielY;
    }

    public void zurueckAufTile(int neueSpalte, int neueReihe)
    {
        spalte = neueSpalte;
        reihe = neueReihe;
        tunnelAktiv = false;
        setzeTilePosition(spalte, reihe);
    }

    protected boolean kannLoslaufen(int neueRichtungX, int neueRichtungY)
    {
        // Vor dem Laufen pruefen, ob das naechste tile frei ist.
        PacManWorld welt = (PacManWorld)getWorld();
        // istTileFrei steht in PacManWorld.java.
        return welt.istTileFrei(spalte + neueRichtungX, reihe + neueRichtungY);
    }

    protected void loslaufen(int neueRichtungX, int neueRichtungY)
    {
        // Immer nur ein tile weiter laufen.
        if (!kannLoslaufen(neueRichtungX, neueRichtungY)) {
            return;
        }

        PacManWorld welt = (PacManWorld)getWorld();

        richtungX = neueRichtungX;
        richtungY = neueRichtungY;
        // Tunnel und Einrollen werden in PacManWorld.java entschieden.
        tunnelAktiv = welt.istTunnelAusgang(spalte + richtungX, reihe + richtungY);
        spalte = welt.spalteEinrollen(spalte + richtungX);
        reihe = reihe + richtungY;

        // Ziel ist die Mitte vom neuen tile.
        zielX = welt.gibTileMitteX(spalte);
        zielY = welt.gibTileMitteY(reihe);
    }

    protected void weiterlaufen()
    {
        // Jeden Act etwas weiter bis zum Ziel.
        if (!bewegtSich()) {
            return;
        }

        if (tunnelAktiv) {
            durchTunnelLaufen();
        }
        else {
            normalLaufen();
        }
    }

    private void setzeTilePosition(int neueSpalte, int neueReihe)
    {
        // Sofort in die Mitte vom tile springen.
        PacManWorld welt = (PacManWorld)getWorld();
        zielX = welt.gibTileMitteX(neueSpalte);
        zielY = welt.gibTileMitteY(neueReihe);
        setLocation(zielX, zielY);
    }

    private void normalLaufen()
    {
        // X und Y langsam zum Ziel schieben.
        int neuesX = zahlZumZielBewegen(getX(), zielX);
        int neuesY = zahlZumZielBewegen(getY(), zielY);
        setLocation(neuesX, neuesY);
    }

    private void durchTunnelLaufen()
    {
        // Rauslaufen, andere Seite rein.
        PacManWorld welt = (PacManWorld)getWorld();
        int neuesX = getX() + richtungX * geschwindigkeit;
        int linksDraussen = -PacManWorld.TILE_GROESSE / 2;
        int rechtsDraussen = welt.getWidth() + PacManWorld.TILE_GROESSE / 2;

        if (richtungX < 0 && neuesX <= linksDraussen) {
            neuesX = rechtsDraussen;
        }
        else if (richtungX > 0 && neuesX >= rechtsDraussen) {
            neuesX = linksDraussen;
        }

        if (richtungX < 0 && neuesX <= zielX) {
            tunnelStoppenBei(zielX);
            return;
        }
        if (richtungX > 0 && neuesX >= zielX) {
            tunnelStoppenBei(zielX);
            return;
        }

        setLocation(neuesX, zielY);
    }

    private void tunnelStoppenBei(int x)
    {
        // Wieder genau im tile.
        tunnelAktiv = false;
        setLocation(x, zielY);
    }

    private int zahlZumZielBewegen(int wert, int ziel)
    {
        // Zahl Richtung Ziel schieben.
        if (wert < ziel) {
            return Math.min(wert + geschwindigkeit, ziel);
        }
        if (wert > ziel) {
            return Math.max(wert - geschwindigkeit, ziel);
        }
        return wert;
    }
}
