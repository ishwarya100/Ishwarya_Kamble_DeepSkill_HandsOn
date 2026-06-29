// Maintains a list of observers and notifies them of stock price changes

import java.util.ArrayList;

public class StockMarket implements Stock {

    private ArrayList<Observer> observers = new ArrayList<>();
    private String price;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(price);
        }
    }

    public void setPrice(String price) {
        this.price = price;
        notifyObservers();
    }

}