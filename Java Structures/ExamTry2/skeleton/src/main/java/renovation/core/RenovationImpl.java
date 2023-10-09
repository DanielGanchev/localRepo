package renovation.core;

import renovation.models.Laminate;
import renovation.models.Tile;
import renovation.models.WoodType;

import java.util.Collection;
import java.util.Iterator;

public class RenovationImpl implements Renovation {
    @Override
    public void deliverTile(Tile tile) {

    }

    @Override
    public void deliverFlooring(Laminate laminate) {

    }

    @Override
    public double getDeliveredTileArea() {
        return 0;
    }

    @Override
    public boolean isDelivered(Laminate laminate) {
        return false;
    }

    @Override
    public void returnTile(Tile tile) {

    }

    @Override
    public void returnLaminate(Laminate laminate) {

    }

    @Override
    public Collection<Laminate> getAllByWoodType(WoodType wood) {
        return null;
    }

    @Override
    public Collection<Tile> getAllTilesFitting(double width, double height) {
        return null;
    }

    @Override
    public Collection<Tile> sortTilesBySize() {
        return null;
    }

    @Override
    public Iterator<Laminate> layFlooring() {
        return null;
    }
}
