import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class VehicleManager {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final List<Owner> owners = new ArrayList<>();

    public Owner findOwner(String cmnd) {
        for (Owner o : owners) if (o.getCmnd().equals(cmnd)) return o;
        return null;
    }

    public void registerOwner(Owner o) { owners.add(o); }

    public boolean add(Vehicle v) {
        if (search(v.getPlate()) != null) return false;
        vehicles.add(v);
        return true;
    }

    public Vehicle search(String plate) {
        for (Vehicle v : vehicles) if (v.getPlate().equalsIgnoreCase(plate.trim())) return v;
        return null;
    }

    public List<Vehicle> findByOwner(String cmnd) {
        List<Vehicle> kq = new ArrayList<>();
        for (Vehicle v : vehicles) if (v.getOwner().getCmnd().equals(cmnd)) kq.add(v);
        return kq;
    }

    public int deleteByManufacturer(String manufacturer) {
        int truoc = vehicles.size();
        vehicles.removeIf(v -> v.getManufacturer().equalsIgnoreCase(manufacturer.trim()));
        return truoc - vehicles.size();
    }

    private Map<String, Integer> countByManufacturer() {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Vehicle v : vehicles) map.merge(v.getManufacturer(), 1, Integer::sum);
        return map;
    }

    public List<String> topManufacturers() {
        Map<String, Integer> map = countByManufacturer();
        int max = 0;
        for (int c : map.values()) max = Math.max(max, c);
        List<String> kq = new ArrayList<>();
        if (max == 0) return kq;
        for (Map.Entry<String, Integer> e : map.entrySet())
            if (e.getValue() == max) kq.add(e.getKey() + " (" + max + " xe)");
        return kq;
    }

    public void sortByManufacturerCountDesc() {
        final Map<String, Integer> count = countByManufacturer();
        vehicles.sort(Comparator
                .comparing((Vehicle v) -> count.get(v.getManufacturer())).reversed()
                .thenComparing(Vehicle::getManufacturer)
                .thenComparing(Vehicle::getPlate));
    }

    public void statisticsByType() {
        int car = 0, moto = 0, truck = 0;
        for (Vehicle v : vehicles) {
            if (v instanceof Car) car++;
            else if (v instanceof Motorbike) moto++;
            else if (v instanceof Truck) truck++;
        }
        System.out.println("Ô tô: " + car + " | Xe máy: " + moto + " | Xe tải: " + truck
                + " | Tổng: " + vehicles.size());
    }

    public void display() {
        if (vehicles.isEmpty()) { System.out.println("(Danh sách rỗng)"); return; }
        int i = 1;
        for (Vehicle v : vehicles) System.out.println((i++) + ". " + v);
    }

    public int size() { return vehicles.size(); }
}
