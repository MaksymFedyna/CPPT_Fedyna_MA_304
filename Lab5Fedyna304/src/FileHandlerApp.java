import java.io.*;
import java.util.Scanner;

public class FileHandlerApp {
    public static void main(String[] args) {
        try {
            // Створюємо об'єкт Equation для обчислень
            Equation eq = new Equation();
            Scanner s = new Scanner(System.in);

            // Введення даних від користувача
            System.out.print("Enter X (in degrees): ");
            double xDegrees = s.nextDouble();

            // Обчислення результату
            double result = eq.calculate(xDegrees);
            System.out.println("Calculated result: " + result);

            // Створення об'єкта FileHandler для запису/читання файлів
            FileHandler fileHandler = new FileHandler();

            // Запис результату у текстовий файл
            fileHandler.writeTextResult("result.txt", result);
            System.out.println("Result written to text file.");

            // Запис результату у двійковий файл
            fileHandler.writeBinaryResult("result.bin", result);
            System.out.println("Result written to binary file.");

            // Читання з текстового файлу
            double textResult = fileHandler.readTextResult("result.txt");
            System.out.println("Read from text file: " + textResult);

            // Читання з двійкового файлу
            double binaryResult = fileHandler.readBinaryResult("result.bin");
            System.out.println("Read from binary file: " + binaryResult);

        } catch (IOException | CalcException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// Клас для обчислення виразу y = 1 / cos(4x), який був створений у лабораторній роботі №4
class Equation {
    public double calculate(double xDegrees) throws CalcException {
        double y;
        try {
            double xRadians = Math.toRadians(xDegrees);
            double cosValue = Math.cos(4 * xRadians);

            if (Math.abs(cosValue) < 1e-10) {
                throw new ArithmeticException("cos(4x) is too close to zero, division by near-zero value");
            }

            y = 1 / cosValue;
        } catch (ArithmeticException ex) {
            throw new CalcException("Exception reason: " + ex.getMessage());
        }
        return y;
    }
}

// Клас CalcException для обробки помилок
class CalcException extends ArithmeticException {
    public CalcException() {}
    public CalcException(String cause) {
        super(cause);
    }
}

// Клас для роботи з файлами
class FileHandler {
    // Метод для запису результату у текстовий файл
    public void writeTextResult(String fileName, double result) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.printf("%f", result);
        }
    }

    // Метод для читання результату з текстового файлу
    public double readTextResult(String fileName) throws FileNotFoundException {
        double result = 0;
        File file = new File(fileName);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                result = scanner.nextDouble();
            }
        } else {
            throw new FileNotFoundException("File " + fileName + " not found.");
        }
        return result;
    }

    // Метод для запису результату у двійковий файл
    public void writeBinaryResult(String fileName, double result) throws IOException {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeDouble(result);
        }
    }

    // Метод для читання результату з двійкового файлу
    public double readBinaryResult(String fileName) throws IOException {
        double result;
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(fileName))) {
            result = inputStream.readDouble();
        }
        finally {
            return result;
        }
    }
}
