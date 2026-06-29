// Implements the Document interface and defines how a Word document is opened

public class WordDocument implements Document {

    @Override
    public void open() {
        System.out.println("Opening Word Document");
    }

}