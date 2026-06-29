// Creates different Computer objects using the Builder pattern

public class Test {

    public static void main(String[] args) {

        Computer computer1 = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM("8 GB")
                .setStorage("512 GB SSD")
                .build();

        Computer computer2 = new Computer.Builder()
                .setCPU("AMD Ryzen 7")
                .setRAM("16 GB")
                .setStorage("1 TB SSD")
                .build();

        computer1.display();
        System.out.println();

        computer2.display();
    }
}