package org.softuni.exam;

import org.softuni.exam.entities.Airline;
import org.softuni.exam.entities.Flight;
import org.softuni.exam.structures.AirlinesManager;
import org.softuni.exam.structures.AirlinesManagerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        AirlinesManager airlinesManager = new AirlinesManagerImpl();

        Airline airline = getRandomAirline();
        Airline airline1 = getRandomAirline();

        Flight flight1 = getRandomFlight();
        Flight flight2 = getRandomFlight();
        Flight flight3 = getRandomFlight();



       airlinesManager.addAirline(airline);
       airlinesManager.addFlight(airline, flight1);
       airlinesManager.addFlight(airline, flight2);
       airlinesManager.addFlight(airline, flight3);

       airlinesManager.performFlight(airline, flight2);
        airlinesManager.performFlight(airline, flight3);


       Set<Flight> completedFlights = (Set<Flight>) airlinesManager.getCompletedFlights();
        ArrayList<Flight> allFlights = (ArrayList<Flight>) airlinesManager.getAllFlights();
        System.out.println(completedFlights.size());
        System.out.println(allFlights.size());




    }

    private static Airline getRandomAirline() {
        return new Airline(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                Math.min(1, Math.random() * 1_000_000_000));
    }

    private static Flight getRandomFlight() {
        return new Flight(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                ((Math.random() * 1_000) < 500));
    }
}
