// Adapts PayPalGateway to the PaymentProcessor interface.

public class PayPalAdapter implements PaymentProcessor {

    private PayPalGateway paypal = new PayPalGateway();

    @Override
    public void processPayment(double amount) {
        paypal.makePayment(amount);
    }

}