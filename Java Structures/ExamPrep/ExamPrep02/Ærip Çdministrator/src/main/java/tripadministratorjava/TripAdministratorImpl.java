package tripadministratorjava;

import java.util.*;
import java.util.stream.Collectors;

public class TripAdministratorImpl implements TripAdministrator {

    private List<String> companies;
    private List<Trip> trips;
    private Map<Company,List<Trip>> companyTrips;

    public TripAdministratorImpl() {
        this.companies = new ArrayList<>();
        this.trips = new ArrayList<>();
        this.companyTrips = new LinkedHashMap<>();

    }



    @Override
    public void addCompany(Company c) {
        if(!this.companies.contains(c.name)){
            this.companies.add(c.name);
            this.companyTrips.put(c, new ArrayList<>());
        }else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void addTrip(Company c, Trip t) {
        if(this.companies.contains(c.name)){
            if(!this.trips.contains(t)){
                this.trips.add(t);
                this.companyTrips.get(c).add(t);
            }else {
                throw new IllegalArgumentException();
            }
        }else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public boolean exist(Company c) {
        return companies.contains(c.name);
    }

    @Override
    public boolean exist(Trip t) {
        return trips.contains(t);
    }

    @Override
    public void removeCompany(Company c) {
        if(this.companies.contains(c.name)){
            this.companies.remove(c.name);
            this.trips.removeAll(this.companyTrips.get(c));
            this.companyTrips.get(c).clear();
            this.companyTrips.remove(c);
        }else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Collection<Company> getCompanies() {
        return this.companyTrips.keySet();
    }

    @Override
    public Collection<Trip> getTrips() {
        return this.trips;
    }

    @Override
    public void executeTrip(Company c, Trip t) {
        if (!exist(c) || !exist(t)) {
            throw new IllegalArgumentException();
        }
        if (this.companyTrips.get(c).contains(t)) {
            this.companyTrips.get(c).remove(t);
            this.trips.remove(t);
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Collection<Company> getCompaniesWithMoreThatNTrips(int n) {
        return companyTrips.keySet().stream().filter(c -> companyTrips.get(c).size() > n).collect(Collectors.toList());
    }

    @Override
    public Collection<Trip> getTripsWithTransportationType(Transportation t) {
        return trips.stream().filter(trip -> trip.transportation.equals(t)).collect(Collectors.toList());
    }

    @Override
    public Collection<Trip> getAllTripsInPriceRange(int lo, int hi) {
        return trips.stream().filter(trip -> trip.price >= lo && trip.price <= hi).collect(Collectors.toList());
    }
}
