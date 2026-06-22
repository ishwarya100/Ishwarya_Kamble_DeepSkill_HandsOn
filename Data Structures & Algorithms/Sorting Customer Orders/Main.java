public class Main {
    public static void main(String[] args) {

        Order[] orders = {

            new Order(101, "Ishwarya", 5000),

            new Order(102, "Niharika", 2000),

            new Order(103, "Akanksha", 8000),

            new Order(104, "Samayra", 3000)

        };

        System.out.println("Before Sorting:");

        SortingOperations.display(orders);

        System.out.println("\nBubble Sort:");

        SortingOperations.bubbleSort(orders);

        SortingOperations.display(orders);

        Order[] orders2 = {

            new Order(101, "Ishwarya", 5000),

            new Order(102, "Niharika", 2000),

            new Order(103, "Akanksha", 8000),

            new Order(104, "Samayra", 3000)

        };

        System.out.println("\nQuick Sort:");

        SortingOperations.quickSort(orders2, 0, orders2.length - 1);

        SortingOperations.display(orders2);

    }
}