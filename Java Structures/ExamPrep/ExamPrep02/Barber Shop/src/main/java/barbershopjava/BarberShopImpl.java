package barbershopjava;

import java.util.*;
import java.util.stream.Collectors;

public class BarberShopImpl implements BarberShop {
    private Set<String> barberNames;
    private List<Barber> barbers;
    private List<Client> clients;
    private Map<Barber, List<Client>> barberClientMap;
    private List<Client> clientsWithNoBarber;

    public BarberShopImpl() {
        barberNames = new HashSet<>();
        barbers = new ArrayList<>();
        clients = new ArrayList<>();
        barberClientMap = new LinkedHashMap<>();
        clientsWithNoBarber = new ArrayList<>();
    }

    @Override
    public void addBarber(Barber b) {
        if (barberNames.contains(b.name)) {
            throw new IllegalArgumentException();
        }

        barberNames.add(b.name);
        barbers.add(b);
        barberClientMap.put(b, new ArrayList<>());
    }

    @Override
    public void addClient(Client c) {
        if (clients.stream().anyMatch(client -> client.name.equals(c.name))) {
            throw new IllegalArgumentException();
        }
        if (c.barber != null) {
           c.barber = null;
        }
        clientsWithNoBarber.add(c);

        clients.add(c);
    }

    @Override
    public boolean exist(Barber b) {
        return barbers.contains(b);
    }

    @Override
    public boolean exist(Client c) {
        return clients.contains(c);
    }

    @Override
    public Collection<Barber> getBarbers() {
        return Collections.unmodifiableList(barbers);
    }

    @Override
    public Collection<Client> getClients() {
        return Collections.unmodifiableList(clients);
    }

    @Override
    public void assignClient(Barber b, Client c) {
        if (!exist(b) || !exist(c)) {
            throw new IllegalArgumentException();
        }

        if (!barberClientMap.get(b).contains(c)) {
            clientsWithNoBarber.remove(c);
            c.barber = b;
            barberClientMap.get(b).add(c);

        }
    }

    @Override
    public void deleteAllClientsFrom(Barber b) {
        if (!exist(b)) {
            throw new IllegalArgumentException();
        }
        barberClientMap.get(b).forEach(client -> client.barber = null);

        barberClientMap.get(b).clear();
    }

    @Override
    public Collection<Client> getClientsWithNoBarber() {
        return Collections.unmodifiableList(clientsWithNoBarber);
    }

    @Override
    public Collection<Barber> getAllBarbersSortedWithClientsCountDesc() {
        return barberClientMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().size() - entry1.getValue().size())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Barber> getAllBarbersSortedWithStarsDescendingAndHaircutPriceAsc() {
        return barbers.stream()
                .sorted((b1, b2) -> {
                    if (b1.stars == b2.stars) {
                        return Integer.compare(b1.haircutPrice, b2.haircutPrice);
                    }
                    return Integer.compare(b2.stars, b1.stars);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Client> getClientsSortedByAgeDescAndBarbersStarsDesc() {
        return barberClientMap.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream().map(client -> new AbstractMap.SimpleEntry<>(client, entry.getKey())))
                .sorted((entry1, entry2) -> {
                    if (entry1.getKey().age == entry2.getKey().age) {
                        return Integer.compare(entry2.getValue().stars, entry1.getValue().stars);
                    }
                    return Integer.compare(entry2.getKey().age, entry1.getKey().age);
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
