package org.softuni.exam.structures;

import org.softuni.exam.entities.Deliverer;
import org.softuni.exam.entities.Package;

import java.util.*;
import java.util.stream.Collectors;

public class DeliveriesManagerImpl implements DeliveriesManager {


    private List<Deliverer> deliverers;

    private List<Package> packages;

    private Map<Deliverer, List<Package>> delivererPackageMap;

    public DeliveriesManagerImpl() {
        this.deliverers = new ArrayList<>();
        this.packages = new ArrayList<>();
        this.delivererPackageMap = new HashMap<>();
    }



    @Override
    public void addDeliverer(Deliverer deliverer) {
        if (deliverer == null) {
            throw new IllegalArgumentException();
        }
        this.deliverers.add(deliverer);
        
    }

    @Override
    public void addPackage(Package _package) {
        if (_package == null) {
            throw new IllegalArgumentException();
        }
        this.packages.add(_package);

    }

    @Override
    public boolean contains(Deliverer deliverer) {
       return this.deliverers.contains(deliverer);
    }

    @Override
    public boolean contains(Package _package) {
        return this.packages.contains(_package);
    }

    @Override
    public Iterable<Deliverer> getDeliverers() {
       return this.deliverers;
    }

    @Override
    public Iterable<Package> getPackages() {
        return this.packages;
    }

    @Override
    public void assignPackage(Deliverer deliverer, Package _package) throws IllegalArgumentException {
        if (deliverer == null || _package == null) {
            throw new IllegalArgumentException();
        }
        if (!this.deliverers.contains(deliverer) || !this.packages.contains(_package)) {
            throw new IllegalArgumentException();
        }
        if (!this.delivererPackageMap.containsKey(deliverer)) {
            this.delivererPackageMap.put(deliverer, new ArrayList<>());
        }
        this.delivererPackageMap.get(deliverer).add(_package);
        packages.remove(_package);



    }

    @Override
    public Iterable<Package> getUnassignedPackages() {
       return this.packages;
    }

    @Override
    public Iterable<Package> getPackagesOrderedByWeightThenByReceiver() {
       return this.packages.stream().sorted(Comparator.comparing(Package::getWeight).reversed().thenComparing(Package::getReceiver)).collect(Collectors.toList());

    }

    @Override
    public Iterable<Deliverer> getDeliverersOrderedByCountOfPackagesThenByName() {
        return delivererPackageMap.entrySet().stream().sorted((e1, e2) -> {
            int result = Integer.compare(e2.getValue().size(), e1.getValue().size());
            if (result == 0) {
                result = e1.getKey().getName().compareTo(e2.getKey().getName());
            }
            return result;
        }).map(Map.Entry::getKey).collect(Collectors.toList());
    }
}
