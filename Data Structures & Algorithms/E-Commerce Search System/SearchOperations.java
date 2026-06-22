class SearchOperations {

    // Linear Search
    static int linearSearch(Product[] products, String name) {

        for (int i = 0; i < products.length; i++) {

            if (products[i].productName.equalsIgnoreCase(name)) {
                return i; // Return index if product is found
            }
        }

        return -1; // Product not found
    }


    // Binary Search
    static int binarySearch(Product[] products, String name) {

        int low = 0;
        int high = products.length - 1;


        while (low <= high) {

            int mid = (low + high) / 2;

            int result = products[mid].productName.compareToIgnoreCase(name);


            if (result == 0) {
                return mid; // Product found
            }

            else if (result < 0) {
                low = mid + 1; // Search right side
            }

            else {
                high = mid - 1; // Search left side
            }
        }

        return -1; // Product not found
    }
}