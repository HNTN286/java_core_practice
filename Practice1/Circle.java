class Circle extends Shape {
    public Circle(double diameter) {
        super(diameter, diameter);
    }

    public double getDiameter() { return width; }

    public double getRadius() { return width / 2; }

    public double getArea() {
        return Math.PI * getRadius() * getRadius();
    }

    public double getCircumference() {
        return getDiameter() * 3.14;
    }

    @Override
    public void display() {
        System.out.println("=== Circle ===");
        System.out.printf("Diameter = %.2f, Radius = %.2f%n", getDiameter(), getRadius());
        System.out.printf("Area          = %.2f%n", getArea());
        System.out.printf("Circumference = %.2f%n", getCircumference());
    }
}
