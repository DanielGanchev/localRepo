package craftsmanLab.core;

import craftsmanLab.models.ApartmentRenovation;
import craftsmanLab.models.Craftsman;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class CraftsmanLabImpl implements CraftsmanLab {



    private final Map<ApartmentRenovation, Craftsman> assignments = new LinkedHashMap<>();
    private final Map<Craftsman, List<ApartmentRenovation>> craftsmanAll = new HashMap<>();
    PriorityQueue<Craftsman> craftsmanQueue = new PriorityQueue<>(Comparator.comparingDouble(craftsman -> craftsman.totalEarnings));
    PriorityQueue<ApartmentRenovation> apartmentRenovationQueue = new PriorityQueue<>(Comparator.comparing(apartmentRenovation -> apartmentRenovation.deadline));





    @Override
    public void addApartment(ApartmentRenovation job) {
        if (exists(job)) {
            throw new IllegalArgumentException();
        }
        apartmentRenovationQueue.add(job);

        assignments.put(job, null);
    }

    @Override
    public void addCraftsman(Craftsman craftsman) {
        if (exists(craftsman)) {
            throw new IllegalArgumentException();
        }


        craftsmanAll.put(craftsman,new ArrayList<>());
        craftsmanQueue.add(craftsman);


    }

    @Override
    public boolean exists(ApartmentRenovation job) {
      return   apartmentRenovationQueue.contains(job);
    }

    @Override
    public boolean exists(Craftsman craftsman) {
       return craftsmanQueue.contains(craftsman);
    }



    @Override
    public void removeCraftsman(Craftsman craftsman) {
       if (!exists(craftsman) || assignments.containsValue(craftsman)) {
            throw new IllegalArgumentException();
        }
        craftsmanAll.remove(craftsman);
        craftsmanQueue.remove(craftsman);


    }

    @Override
    public Collection<Craftsman> getAllCraftsmen() {
        return craftsmanAll.keySet();
    }



    @Override
    public void assignRenovations() {



        for (ApartmentRenovation apartmentRenovation : assignments.keySet()) {
            if (assignments.get(apartmentRenovation) == null && !craftsmanQueue.isEmpty()) {
                Craftsman craftsman = craftsmanQueue.poll();
                assignments.put(apartmentRenovation, craftsman);
                craftsman.totalEarnings += apartmentRenovation.workHoursNeeded * craftsman.hourlyRate;
                craftsmanQueue.add(craftsman);
            }
        }

    }



    @Override
    public Craftsman getContractor(ApartmentRenovation job) {
        Craftsman contractor = assignments.get(job);
        if (contractor == null) {
            throw new IllegalArgumentException();
        }
        return contractor;
    }

    @Override
    public Craftsman getLeastProfitable() {
        if (craftsmanAll.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return craftsmanQueue.poll();
    }

    @Override
    public Collection<ApartmentRenovation> getApartmentsByRenovationCost() {
        return assignments.keySet().stream().sorted(Comparator.comparingDouble(this::calculateRenovationCost).reversed()).collect(Collectors.toList());
    }

    private double calculateRenovationCost(ApartmentRenovation apartment) {
        Craftsman contractor = assignments.get(apartment);
        if (contractor != null) {
            return apartment.workHoursNeeded * contractor.hourlyRate;
        } else {
            return apartment.workHoursNeeded;
        }
    }

    @Override
    public Collection<ApartmentRenovation> getMostUrgentRenovations(int limit) {
        return apartmentRenovationQueue.stream().limit(limit).collect(Collectors.toList());

    }






}