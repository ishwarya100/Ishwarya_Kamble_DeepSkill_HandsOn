// Demonstrates the Observer pattern by notifying registered observers.

public class Test {

    public static void main(String[] args) {

        StockMarket stock = new StockMarket();

        Observer mobile = new MobileApp();
        Observer web = new WebApp();

        stock.registerObserver(mobile);
        stock.registerObserver(web);

        stock.setPrice("₹500");

    }

}