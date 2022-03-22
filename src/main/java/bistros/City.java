package bistros;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class City {

    private Set<Bistro> bistros = new HashSet<>();

    public void addBistro (Bistro bistro) {
        bistros.add(bistro);
    }

    public Set<Bistro> getBistros() {
        return Set.copyOf(bistros);
    }

    public Bistro findBistroByAddress(Address address) {
        return bistros.stream()
                .filter(bistro -> bistro.getAddress().equals(address))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Cannot find bistro in address: " + address.toString() + "!"));
    }

    public Bistro findLongestMenu() {
        return bistros.stream()
                .max(Comparator.comparingInt(b -> b.getMenu().size()))
                .orElseThrow(() -> new IllegalArgumentException("Empty list"));
    }

    public List<Bistro> findBistroWithMenuItem(String menuItem) {
        return bistros.stream()
                .filter(bistro -> bistro.getMenu().stream().map(MenuItem::getName).toList().contains(menuItem))
                .toList();
    }
}
