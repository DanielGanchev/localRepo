package craftsmanLab;

import craftsmanLab.core.CraftsmanLabImpl;
import craftsmanLab.models.ApartmentRenovation;
import craftsmanLab.models.Craftsman;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        CraftsmanLabImpl craftsmanLab = new CraftsmanLabImpl();

        //write a test for getApartmentsByRenovationCost() method

        ApartmentRenovation apartmentRenovation = new ApartmentRenovation("Sofia", 100, 12, LocalDate.now());
        craftsmanLab.addApartment(apartmentRenovation);
        Craftsman craftsman = new Craftsman("Pesho", 10, 1000);
        craftsmanLab.addCraftsman(craftsman);
        craftsmanLab.assignRenovations();
        Craftsman craftsman1 = new Craftsman("Gosho", 10, 500);

        ApartmentRenovation apartmentRenovation1 = new ApartmentRenovation("Sofia", 100, 10, LocalDate.of(2021, 10, 10));

        craftsmanLab.addApartment(apartmentRenovation1);

        craftsmanLab.exists(apartmentRenovation1);
        craftsmanLab.exists(craftsman1);


        System.out.println(craftsmanLab.getApartmentsByRenovationCost());
        System.out.println(craftsmanLab.getMostUrgentRenovations(1));
        System.out.println(craftsmanLab.getContractor(apartmentRenovation));

    }
}
