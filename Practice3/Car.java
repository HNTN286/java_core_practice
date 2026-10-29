class Car extends Vehicle {
    private int seats;
    private String engineType;

    public Car(String plate, String manufacturer, int year, String color, Owner owner,
               int seats, String engineType) {
        super(plate, manufacturer, year, color, owner);
        if (seats <= 0) throw new IllegalArgumentException("Số chỗ ngồi phải > 0");
        this.seats = seats;
        this.engineType = engineType;
    }

    @Override public String getType() { return "Ô tô"; }
    @Override protected String extraInfo() { return "Số chỗ: " + seats + ", Động cơ: " + engineType; }
}
