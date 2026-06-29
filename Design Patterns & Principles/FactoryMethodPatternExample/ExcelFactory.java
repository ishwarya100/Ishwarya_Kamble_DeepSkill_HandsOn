// Implements createDocument() to return an ExcelDocument

public class ExcelFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }

}