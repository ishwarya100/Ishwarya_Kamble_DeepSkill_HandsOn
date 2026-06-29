// Represents the Stripe payment gateway.

public class StripeGateway {

    public void pay(double amount) {
        System.out.println("Payment of Rs." + amount + " processed through Stripe.");
    }

}