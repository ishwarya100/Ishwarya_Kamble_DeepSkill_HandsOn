// Class containing sorting algorithms

class SortingOperations {

    static void bubbleSort(Order[] orders) {

        for(int i = 0; i < orders.length - 1; i++) {

            for(int j = 0; j < orders.length - i - 1; j++) {

                if(orders[j].totalPrice > orders[j + 1].totalPrice) {

                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;

                }
            }
        }
    }

    // Quick Sort by totalPrice
    static void quickSort(Order[] orders, int low, int high) {

        if(low < high) {

            int pivot = partition(orders, low, high);

            quickSort(orders, low, pivot - 1);

            quickSort(orders, pivot + 1, high);
        }
    }

    static int partition(Order[] orders, int low, int high) {


        double pivot = orders[high].totalPrice;

        int i = low - 1;

        for(int j = low; j < high; j++) {

            if(orders[j].totalPrice < pivot) {

                i++;

                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;

            }
        }

        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    // Display orders
    static void display(Order[] orders) {

        for(Order o : orders) {

            System.out.println(
                o.orderId + " " +
                o.customerName + " " +
                o.totalPrice
            );
        }
    }
}