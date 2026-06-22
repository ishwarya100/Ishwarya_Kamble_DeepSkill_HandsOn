public class Main {

    static Employee[] employees = new Employee[10];

    static int count = 0;

    static void add(Employee e) {

        employees[count] = e;
        count++;
    }

    static void search(int id) {

        for(int i = 0; i < count; i++) {

            if(employees[i].employeeId == id) {

                System.out.println("Employee Found:");
                employees[i].display();
                return;
            }
        }

        System.out.println("Employee not found");
    }

    static void traverse() {

        for(int i = 0; i < count; i++) {

            employees[i].display();
        }
    }

    static void delete(int id) {

        for(int i = 0; i < count; i++) {


            if(employees[i].employeeId == id) {

                for(int j = i; j < count - 1; j++) { // Shift elements after deleting

                    employees[j] = employees[j + 1];
                }

                count--;

                System.out.println("Employee deleted");
                return;
            }
        }

        System.out.println("Employee not found");
    }

    public static void main(String[] args) {

        // Adding employee records
        add(new Employee(101,"Ishwarya","System Administrator",90000));
        add(new Employee(102,"Poonam","Tester",40000));
        add(new Employee(103,"Anjali","Manager",70000));

        // Display all employee records
        System.out.println("Employee Records:");
        traverse();

        // Searching employee
        System.out.println("\nSearch Result:");
        search(102);

        // Deleting employee
        System.out.println("\nDelete Operation:");
        delete(101);

        // Display records after deletion
        System.out.println("\nUpdated Employee Records:");
        traverse();

    }
}