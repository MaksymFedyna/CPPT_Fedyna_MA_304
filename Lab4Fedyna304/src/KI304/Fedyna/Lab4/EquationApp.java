package KI304.Fedyna.Lab4;

import java.util.Scanner;
import static java.lang.System.out;

/**
 * Class <code>EquationApp</code> Implements driver for Equation class
 * @author Fedyna Maksym
 * @version 1.0
 */
public class EquationApp {
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            out.print("Enter X (in degrees): ");

            // Зчитуємо введене значення та замінюємо кому на крапку
            String input = in.next().replace(",", ".");

            // Перетворюємо рядок на число
            double xDegrees = Double.parseDouble(input);

            try {
                Equation eq = new Equation();
                double result = eq.calculate(xDegrees);
                out.println("Result: " + result);
            } catch (CalcException ex) {
                out.println(ex.getMessage());
            }
        } catch (NumberFormatException ex) {
            out.println("Invalid input: not a valid number");
        } catch (Exception ex) {
            out.println("An unexpected error occurred: " + ex.getMessage());
        }
    }
}

/**
 * Class <code>CalcException</code> handles errors during calculation
 * @author Fedyna Maksym
 * @version 1.0
 */
class CalcException extends ArithmeticException {
    public CalcException() {}

    public CalcException(String cause) {
        super(cause);
    }
}

/**
 * Class <code>Equation</code> implements method for y = 1 / cos(4x)
 * @author Fedyna Maksym
 * @version 1.0
 */
/**
 * Class <code>Equation</code> implements method for y = 1 / cos(4x)
 * @author Fedyna Maksym
 * @version 1.0
 */
class Equation {
    /**
     * Method calculates the expression y = 1 / cos(4x), where x is in degrees
     * @param xDegrees The value in degrees
     * @return Calculated value of y
     * @throws CalcException if an illegal value for cos(4x) is encountered
     */
    public double calculate(double xDegrees) throws CalcException {
        double y;
        try {
            // Перетворюємо градуси в радіани
            double xRadians = Math.toRadians(xDegrees);
            out.println("Radians: " + xRadians);
            // Обчислюємо косинус від 4x (у радіанах)
            double cosValue = Math.cos(4 * xRadians);
            out.println("Cos: " + cosValue);

            // Якщо косинус дуже близький до нуля, ми генеруємо помилку
            if (Math.abs(cosValue) < 1e-10) {
                throw new ArithmeticException("cos(4x) is too close to zero, division by near-zero value");
            }

            // Обчислюємо y = 1 / cos(4x)
            y = 1 / cosValue;
        } catch (ArithmeticException ex) {
            // Якщо сталося ділення на нуль або близьке до нуля значення
            throw new CalcException("Exception reason: " + ex.getMessage());
        }
        return y;
    }
}
