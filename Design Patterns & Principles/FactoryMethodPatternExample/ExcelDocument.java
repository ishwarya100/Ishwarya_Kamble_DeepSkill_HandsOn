// Implements the Document interface and defines how an Excel document is opened

public class ExcelDocument implements Document {

    @Override
    public void open() {
        System.out.println("Opening Excel Document");
    }

}