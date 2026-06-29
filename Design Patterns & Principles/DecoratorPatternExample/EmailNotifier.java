// Sends notifications via Email

public class EmailNotifier implements Notifier {

    @Override
    public void send() {
        System.out.println("Sending notification via Email");
    }

}