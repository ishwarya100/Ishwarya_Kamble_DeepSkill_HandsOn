// Demonstrates the Proxy pattern using ProxyImage

public class Test {

    public static void main(String[] args) {

        Image image = new ProxyImage("sample.jpg");

        image.display();
        System.out.println();

        image.display();

    }

}