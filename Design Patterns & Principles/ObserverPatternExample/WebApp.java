// Receives stock price updates on the web app

public class WebApp implements Observer {

    @Override
    public void update(String price) {
        System.out.println("Web App: Stock price updated to " + price);
    }

}