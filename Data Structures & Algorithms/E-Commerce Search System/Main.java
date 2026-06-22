public class Main {
    public static void main(String[] args) {

        // Array for linear search (not sorted)
        Product[] products = {
            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Mouse", "Accessories"),
            new Product(103, "Keyboard", "Accessories"),
            new Product(104, "Mobile", "Electronics")
        };

        int linearResult = SearchOperations.linearSearch(products, "Mouse");

        if (linearResult != -1) {
            System.out.println("Linear Search: Product found at index " + linearResult);
        } 
        else {
            System.out.println("Linear Search: Product not found");
        }

        // Sorted array for binary search
        Product[] sortedProducts = {
            new Product(101, "Keyboard", "Accessories"),
            new Product(102, "Laptop", "Electronics"),
            new Product(103, "Mobile", "Electronics"),
            new Product(104, "Mouse", "Accessories")
        };

        int binaryResult = SearchOperations.binarySearch(sortedProducts, "Mouse");


        if (binaryResult != -1) {
            System.out.println("Binary Search: Product found at index " + binaryResult);
        } 
        else {
            System.out.println("Binary Search: Product not found");
        }

    }
}