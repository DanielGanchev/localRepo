package renovation;

import renovation.core.RenovationImpl;
import renovation.models.Tile;

public class Main {

    public static void main(String[] args) {

        //write a test for the getdeliveredtile area method

        RenovationImpl renovation = new RenovationImpl();
        Tile tile = new Tile(1, 1, 1);
        Tile tile2 = new Tile(2, 2, 2);
        Tile tile3 = new Tile(5,  5, 3);
        renovation.deliverTile(tile);
        renovation.deliverTile(tile2);
        renovation.deliverTile(tile3);

        System.out.println(renovation.getDeliveredTileArea());

    }
}
