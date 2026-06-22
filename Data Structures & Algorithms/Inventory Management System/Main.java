// Main class to test the Inventory Management System
public class Main {
    public static void main(String[] args) {

        InventoryManager manager = new InventoryManager();

        // Add products
        manager.addProduct(new Product(101, "Laptop", 50, 45000));
        manager.addProduct(new Product(102, "Mouse", 200, 500));
        manager.addProduct(new Product(103, "Keyboard", 100, 1200));

        manager.updateProduct(101, 60, 47000);   // Update product

        manager.deleteProduct(102);    // Delete product

        manager.displayProducts();    // Display inventory
    }
}
