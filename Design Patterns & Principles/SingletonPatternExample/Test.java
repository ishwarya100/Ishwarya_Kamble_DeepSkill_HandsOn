public class Test {

    public static void main(String[] args) {

        Logger log1 = Logger.getInstance();
        Logger log2 = Logger.getInstance();

        System.out.println(log1 == log2);
    }
}