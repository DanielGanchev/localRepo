package org.softuni.exam.structures;

import org.softuni.exam.entities.Airline;
import org.softuni.exam.entities.Flight;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AirlinesManagerImpl implements AirlinesManager {

    private HashMap<Airline, List<Flight>> airlineFlightHashMap;
    private List<Flight> flights;
    private HashSet<Flight> completedFlights;
    private Set<Flight> flightSet;
    private Map<Flight, Airline> flightToAirline;

    public AirlinesManagerImpl() {
        this.airlineFlightHashMap = new HashMap<>();
        this.flights = new ArrayList<>();
        this.completedFlights = new HashSet<>();
        this.flightSet = new HashSet<>();
        this.flightToAirline = new HashMap<>();
    }

    @Override
    public void addAirline(Airline airline) {
        this.airlineFlightHashMap.putIfAbsent(airline, new ArrayList<>());
    }

    @Override
    public void addFlight(Airline airline, Flight flight) {
        if (airline == null || flight == null) {
            throw new IllegalArgumentException();
        }
        this.airlineFlightHashMap.get(airline).add(flight);
        this.flights.add(flight);
        this.flightSet.add(flight);
        this.flightToAirline.put(flight, airline);
        if (flight.isCompleted()) {
            this.completedFlights.add(flight);
        }
    }

    @Override
    public boolean contains(Airline airline) {
        return this.airlineFlightHashMap.containsKey(airline);
    }

    @Override
    public boolean contains(Flight flight) {
        return this.flightSet.contains(flight);
    }

    @Override
    public void deleteAirline(Airline airline) throws IllegalArgumentException {
        if (!this.airlineFlightHashMap.containsKey(airline)) {
            throw new IllegalArgumentException();
        } else {
            List<Flight> airlineFlights = this.airlineFlightHashMap.get(airline);
            for (Flight flight : airlineFlights) {
                this.flightSet.remove(flight);
                this.flightToAirline.remove(flight);
                this.flights.remove(flight);
                this.completedFlights.remove(flight);
            }
            this.airlineFlightHashMap.remove(airline);
        }
    }

    @Override
    public Iterable<Flight> getAllFlights() {
        return this.flights;
    }

    @Override
    public Flight performFlight(Airline airline, Flight flight) throws IllegalArgumentException {
        if (!this.airlineFlightHashMap.containsKey(airline) || !this.airlineFlightHashMap.get(airline).contains(flight)) {
            throw new IllegalArgumentException();
        }
        flight.setCompleted(true);
        this.completedFlights.add(flight);
        return flight;
    }

    @Override
    public Iterable<Flight> getCompletedFlights() {
        return this.completedFlights;
    }

    @Override
    public Iterable<Flight> getFlightsOrderedByNumberThenByCompletion() {
        return this.airlineFlightHashMap.values().stream()
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Flight::isCompleted).thenComparing(Flight::getNumber))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Airline> getAirlinesOrderedByRatingThenByCountOfFlightsThenByName() {
        return this.airlineFlightHashMap.keySet()
                .stream()
                .sorted(Comparator.comparing(Airline::getRating, Comparator.reverseOrder())
                        .thenComparing(airline -> this.airlineFlightHashMap.get(airline).size(), Comparator.reverseOrder())
                        .thenComparing(Airline::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Airline> getAirlinesWithFlightsFromOriginToDestination(String origin, String destination) {
        return this.airlineFlightHashMap.keySet().stream()
                .filter(airline -> this.airlineFlightHashMap.get(airline).stream()
                        .anyMatch(flight -> !flight.isCompleted() && flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)))
                .collect(Collectors.toList());
    }
}
