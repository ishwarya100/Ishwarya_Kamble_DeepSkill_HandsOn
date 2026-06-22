import java.util.HashMap;

// Class to manage inventory operations
class InventoryManager {

    // HashMap to store products using productId as key
    HashMap<Integer, Product> inventory = new HashMap<>(); 

    void addProduct(Product product) {
        inventory.put(product.productId, product);
        System.out.println("Product added successfully.");
    }

    void updateProduct(int productId, int quantity, double price) {
        Product product = inventory.get(productId);

        if (product != null) {
            product.quantity = quantity;
            product.price = price;
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    void deleteProduct(int productId) {
        if (inventory.remove(productId) != null) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    void displayProducts() {
        System.out.println("\nInventory Details:");

        for (Product p : inventory.values()) {
            System.out.println(
                "ID: " + p.productId +
                ", Name: " + p.productName +
                ", Quantity: " + p.quantity +
                ", Price: " + p.price
            );
        }
    }
}
