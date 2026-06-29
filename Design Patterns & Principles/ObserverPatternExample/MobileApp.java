// Receives stock price updates on the mobile app

public class MobileApp implements Observer {

    @Override
    public void update(String price) {
        System.out.println("Mobile App: Stock price updated to " + price);
    }

}