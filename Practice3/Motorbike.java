class Motorbike extends Vehicle {
    private int capacity; 

    public Motorbike(String plate, String manufacturer, int year, String color, Owner owner, int capacity) {
        super(plate, manufacturer, year, color, owner);
        if (capacity <= 0) throw new IllegalArgumentException("Dung tích phải > 0");
        this.capacity = capacity;
    }

    @Override public String getType() { return "Xe máy"; }
    @Override protected String extraInfo() { return "Dung tích: " + capacity + " cc"; }
}
