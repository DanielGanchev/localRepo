package renovation.core;

import renovation.models.Laminate;
import renovation.models.Tile;
import renovation.models.WoodType;

import java.util.*;
import java.util.stream.Collectors;

public class RenovationImpl implements Renovation {

    private final Stack<Tile> deliveredTiles;
    private final Stack<Laminate> deliveredLaminates;

    double totalTileArea = 0;


    public RenovationImpl() {
           this.deliveredTiles = new Stack<>();
            this.deliveredLaminates = new Stack<>();
    }


    @Override
    public void deliverTile(Tile tile) {
        if ( this.calculateTileArea(tile) + this.totalTileArea > 30){
            throw new IllegalArgumentException();
        }
        this.deliveredTiles.push(tile);
        this.totalTileArea += this.calculateTileArea(tile);


    }

    @Override
    public void deliverFlooring(Laminate laminate) {
        this.deliveredLaminates.push(laminate);


    }

    @Override
    public double getDeliveredTileArea() {

        return this.totalTileArea;
    }

    @Override
    public boolean isDelivered(Laminate laminate) {
        return this.deliveredLaminates.contains(laminate);
    }

    @Override
    public void returnTile(Tile tile) {
        if (this.deliveredTiles.contains(tile)){
            this.deliveredTiles.remove(tile);
            this.totalTileArea -= this.calculateTileArea(tile);
        }else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void returnLaminate(Laminate laminate) {
        if (this.deliveredLaminates.contains(laminate)){
            this.deliveredLaminates.remove(laminate);


        }else {
            throw new IllegalArgumentException();
        }



    }

    @Override
    public Collection<Laminate> getAllByWoodType(WoodType wood) {
       return this.deliveredLaminates.stream().filter(laminate -> laminate.woodType.equals(wood)).collect(Collectors.toList());
    }

    @Override
    public Collection<Tile> getAllTilesFitting(double width, double height) {
       return this.deliveredTiles.stream().filter(tile -> tile.width <= width && tile.height <= height).collect(Collectors.toList());
    }
//then compare by depth
    @Override
    public Collection<Tile> sortTilesBySize() {
        return this.deliveredTiles.stream().sorted(Comparator.comparing(this::calculateTileArea).thenComparing(tile -> tile.depth)).collect(Collectors.toList());
    }



    @Override
    public Iterator<Laminate> layFlooring() {
     return new Iterator<Laminate>() {
         @Override
         public boolean hasNext() {
             return !deliveredLaminates.isEmpty();
         }

         @Override
         public Laminate next() {
             return deliveredLaminates.pop();
         }
     };
    }


    private double calculateTileArea(Tile tile){
        return tile.width * tile.height;
    }

    private double calculateLaminateArea(Laminate laminate){
        return laminate.width * laminate.length;
    }
}
