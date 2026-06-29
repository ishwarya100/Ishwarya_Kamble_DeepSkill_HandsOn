// Demonstrates Dependency Injection using constructor injection

public class Test {

    public static void main(String[] args) {

        CustomerRepository repository = new CustomerRepositoryImpl();

        CustomerService service = new CustomerService(repository);

        service.getCustomer(50);

    }

}