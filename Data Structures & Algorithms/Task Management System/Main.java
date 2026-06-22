public class Main {

    // Head node of linked list
    static Task head = null;

    static void add(Task task) {

        if(head == null) {
            head = task;
        }
        else {
            Task temp = head;
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = task;
        }
    }

    // Method to search task using task ID
    static void search(int id) {

        Task temp = head;

        while(temp != null) {
            if(temp.taskId == id) {
                System.out.println("Task Found:");
                temp.display();
                return;
            }

            temp = temp.next;
        }

        System.out.println("Task not found");
    }

    // Traverse all tasks
    static void traverse() {

        Task temp = head;

        while(temp != null) {

            temp.display();
            temp = temp.next;
        }
    }

    // Delete task
    static void delete(int id) {

        if(head == null) {

            System.out.println("List is empty");
            return;
        }

        if(head.taskId == id) {

            head = head.next;
            System.out.println("Task deleted");
            return;
        }

        Task temp = head;

        while(temp.next != null) {

            if(temp.next.taskId == id) {

                temp.next = temp.next.next;

                System.out.println("Task deleted");
                return;
            }

            temp = temp.next;
        }

        System.out.println("Task not found");
    }

    public static void main(String[] args) {

        // Adding tasks
        add(new Task(1,"Complete Assignment","Pending"));
        add(new Task(2,"Learn DSA","In Progress"));
        add(new Task(3,"Project Submission","Completed"));

        // Display tasks
        System.out.println("Task List:");
        traverse();

        // Search task
        System.out.println("\nSearch Result:");
        search(2);

        // Delete task
        System.out.println("\nDelete Operation:");
        delete(1);

        // Display after deletion
        System.out.println("\nUpdated Task List:");
        traverse();

    }
}