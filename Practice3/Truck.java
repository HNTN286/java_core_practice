class Truck extends Vehicle {
    private double tonnage;

    public Truck(String plate, String manufacturer, int year, String color, Owner owner, double tonnage) {
        super(plate, manufacturer, year, color, owner);
        if (tonnage <= 0) throw new IllegalArgumentException("Trọng tải phải > 0");
        this.tonnage = tonnage;
    }

    @Override public String getType() { return "Xe tải"; }
    @Override protected String extraInfo() { return "Trọng tải: " + tonnage + " tấn"; }
}
