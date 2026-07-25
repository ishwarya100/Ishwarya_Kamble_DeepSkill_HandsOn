import org.springframework.stereotype.Service;

// Simple service that adds two numbers
@Service
public class CalculatorService {

    public int add(int a, int b) {
        return a + b;
    }
}
