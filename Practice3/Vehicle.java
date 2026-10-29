import java.time.Year;

abstract class Vehicle {
    public static final String[] MANUFACTURERS = {"Honda", "Yamaha", "Toyota", "Suzuki"};

    protected String plate;
    protected String manufacturer;
    protected int year;
    protected String color;
    protected Owner owner;

    public Vehicle(String plate, String manufacturer, int year, String color, Owner owner) {
        if (plate == null || plate.length() != 5)
            throw new IllegalArgumentException("Biển số phải có đúng 5 ký tự");
        String chuanHang = chuanHoaHang(manufacturer);
        if (chuanHang == null)
            throw new IllegalArgumentException("Hãng chỉ gồm: " + String.join(", ", MANUFACTURERS));
        int namHienTai = Year.now().getValue();
        if (year <= 2000 || year > namHienTai)
            throw new IllegalArgumentException("Năm sản xuất phải > 2000 và <= " + namHienTai);
        if (owner == null)
            throw new IllegalArgumentException("Phương tiện phải có chủ xe");
        this.plate = plate;
        this.manufacturer = chuanHang;
        this.year = year;
        this.color = color;
        this.owner = owner;
    }

    public static String chuanHoaHang(String m) {
        if (m == null) return null;
        for (String h : MANUFACTURERS) if (h.equalsIgnoreCase(m.trim())) return h;
        return null;
    }

    public String getPlate() { return plate; }
    public String getManufacturer() { return manufacturer; }
    public int getYear() { return year; }
    public String getColor() { return color; }
    public Owner getOwner() { return owner; }

    public abstract String getType();

    protected abstract String extraInfo();

    @Override
    public String toString() {
        return String.format("[%s] Biển số: %s | Hãng: %s | Năm: %d | Màu: %s | %s | %s",
                getType(), plate, manufacturer, year, color, extraInfo(), owner);
    }
}
