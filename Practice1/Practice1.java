public class Practice1 {
    public static void main(String[] args) {
        Shape shape = new Shape(4, 6);
        Rectangle rectangle = new Rectangle(5, 8);
        Circle circle = new Circle(10);

        System.out.println("=== Shape ===");
        shape.display();
        System.out.println();
        rectangle.display();
        System.out.println();
        circle.display();

        System.out.println("\n--- Đa hình (polymorphism) ---");
        Shape[] shapes = { shape, rectangle, circle };
        for (Shape s : shapes) {
            s.display();
            System.out.println();
        }
    }
}
