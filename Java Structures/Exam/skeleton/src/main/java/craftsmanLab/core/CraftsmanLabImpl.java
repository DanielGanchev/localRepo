package craftsmanLab.core;

import craftsmanLab.models.ApartmentRenovation;
import craftsmanLab.models.Craftsman;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class CraftsmanLabImpl implements CraftsmanLab {
    private final Set<ApartmentRenovation> apartmentRenovations = new HashSet<>();
    private final List<String> addresses = new ArrayList<>();
    private final Set<Craftsman> craftsmen = new HashSet<>();
    private final Map<ApartmentRenovation, Craftsman> assignments = new LinkedHashMap<>();

    private final ArrayList<Craftsman> craftsmanAll = new ArrayList<>();



    @Override
    public void addApartment(ApartmentRenovation job) {
        if (addresses.contains(job.address)) {
            throw new IllegalArgumentException();
        }
        apartmentRenovations.add(job);
        addresses.add(job.address);
    }

    @Override
    public void addCraftsman(Craftsman craftsman) {
        if (exists(craftsman)) {
            throw new IllegalArgumentException();
        }
        craftsmen.add(craftsman);
        craftsmanAll.add(craftsman);
    }

    @Override
    public boolean exists(ApartmentRenovation job) {
           return apartmentRenovations.contains(job);
    }

    @Override
    public boolean exists(Craftsman craftsman) {
        return craftsmanAll.contains(craftsman);
    }



    @Override
    public void removeCraftsman(Craftsman craftsman) {
        if (!exists(craftsman) || assignments.containsValue(craftsman)) {
            throw new IllegalArgumentException();
        }
        craftsmen.remove(craftsman);
        craftsmanAll.remove(craftsman);

    }

    @Override
    public Collection<Craftsman> getAllCraftsmen() {
        return Collections.unmodifiableCollection(craftsmanAll);
    }



    @Override
    public void assignRenovations() {
        if (craftsmen.isEmpty() || apartmentRenovations.isEmpty()) {
            throw new IllegalArgumentException();
        }


        List<Craftsman> sortedCraftsmen = new ArrayList<>(craftsmen);
        sortedCraftsmen.sort(Comparator.comparingDouble(craftsmen -> craftsmen.totalEarnings));


        assignments.forEach((apartmentRenovation, craftsman) -> {
            if (craftsman == null) {
              Craftsman  craftsmanTemp = sortedCraftsmen.get(0);
                assignments.put(apartmentRenovation, craftsmanTemp);
                sortedCraftsmen.remove(0);
                craftsmen.remove(craftsman);

            }
        });
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
    if (craftsmen.isEmpty()){
        throw new IllegalArgumentException();
    }
    return craftsmen.stream().min(Comparator.comparingDouble(craftsman -> craftsman.totalEarnings)).orElse(null);
    }

    @Override
    public Collection<ApartmentRenovation> getApartmentsByRenovationCost() {
        List<ApartmentRenovation> sortedApartments = new ArrayList<>(apartmentRenovations);
        sortedApartments.sort(Comparator.comparingDouble(this::calculateRenovationCost).reversed());
        return sortedApartments;
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
        Comparator<ApartmentRenovation> comparator = (e1, e2) -> {
            if (e1.deadline.equals(e2.deadline)) {
                return Double.compare(e1.area, e2.area);
            }
            return e1.deadline.compareTo(e2.deadline);
        };

        return apartmentRenovations.stream()
                .sorted(comparator)
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Craftsman lowestEarningCraftsman(){
        return this.craftsmen.stream().min(Comparator.comparingDouble(craftsman -> craftsman.totalEarnings)).orElse(null);
    }




}