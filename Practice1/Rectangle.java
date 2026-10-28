class Rectangle extends Shape {
    public Rectangle(double width, double length) {
        super(width, length);
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void display() {
        System.out.println("=== Rectangle ===");
        System.out.printf("Width = %.2f, Length = %.2f%n", width, height);
        System.out.printf("Area      = %.2f%n", getArea());
        System.out.printf("Perimeter = %.2f%n", getPerimeter());
    }
}
