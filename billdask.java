import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}

public class BillingSoftware {

    private List<Product> products;
    private double taxRate;

    public BillingSoftware(double taxRate) {
        this.products = new ArrayList<>();
        this.taxRate = taxRate;
    }

    // Method to add a product
    public void addProduct(String name, double price, int quantity) {
        Product product = new Product(name, price, quantity);
        products.add(product);
    }

    // Method to calculate the total price (without tax)
    public double calculateTotalBeforeTax() {
        double total = 0;
        for (Product product : products) {
            total += product.getTotalPrice();
        }
        return total;
    }

    // Method to calculate tax
    public double calculateTax() {
        return calculateTotalBeforeTax() * taxRate;
    }

    // Method to calculate total price (including tax)
    public double calculateTotalAfterTax() {
        return calculateTotalBeforeTax() + calculateTax();
    }

    // Method to print the bill
    public void printBill() {
        System.out.println("********** BILL **********");
        System.out.println("Product\t\tPrice\tQuantity\tTotal");
        for (Product product : products) {
            System.out.printf("%-15s%-10.2f%-10d%-10.2f%n", product.getName(), product.getPrice(), product.getQuantity(), product.getTotalPrice());
        }
        System.out.println("--------------------------------");
        System.out.printf("Total (Before Tax): %.2f%n", calculateTotalBeforeTax());
        System.out.printf("Tax (%.2f%%): %.2f%n", taxRate * 100, calculateTax());
        System.out.printf("Total (After Tax): %.2f%n", calculateTotalAfterTax());
        System.out.println("*****************************");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create billing software instance with 10% tax
        BillingSoftware billingSoftware = new BillingSoftware(0.10);

        // Sample loop for adding products to the bill
        while (true) {
            System.out.print("Enter product name (or type 'done' to finish): ");
            String productName = scanner.nextLine();
            if (productName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter price of " + productName + ": ");
            double price = scanner.nextDouble();

            System.out.print("Enter quantity of " + productName + ": ");
            int quantity = scanner.nextInt();

            // Consume the leftover newline character after the number input
            scanner.nextLine();

            billingSoftware.addProduct(productName, price, quantity);
        }

        // Print the generated bill
        billingSoftware.printBill();
    }
}
