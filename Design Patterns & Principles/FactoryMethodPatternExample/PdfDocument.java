// Implements the Document interface and defines how a PDF document is opened

public class PdfDocument implements Document {

    @Override
    public void open() {
        System.out.println("Opening PDF Document");
    }

}