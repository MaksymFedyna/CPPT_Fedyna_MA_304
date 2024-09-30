import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lab1FedynaKI304 {

    /**
     * Точка входу в програму.
     *
     * @param args Аргументи командного рядка.
     * @throws FileNotFoundException Якщо файл не знайдено.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);

        // Запит розміру квадратної матриці
        System.out.print("Введіть розмір квадратної матриці: ");
        int size = in.nextInt();
        in.nextLine(); // Очистити буфер після введення числа

        // Запит символу-заповнювача
        System.out.print("Введіть символ-заповнювач: ");
        String filler = in.nextLine();

        // Перевірка введення символу-заповнювача
        if (filler.length() != 1) {
            System.out.println("Помилка: потрібно ввести рівно один символ.");
            return;
        }

        char fillChar = filler.charAt(0);

        // Ініціалізація квадратної матриці
        char[][] matrix = new char[size][size];

        // Заповнення матриці
        int middle = size / 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i <= middle) {
                    if (j >= middle - i && j <= middle + i) {
                        matrix[i][j] = fillChar;
                    } else {
                        matrix[i][j] = ' ';
                    }
                } else {
                    if (j >= i - middle && j <= size - 1 - (i - middle)) {
                        matrix[i][j] = fillChar;
                    } else {
                        matrix[i][j] = ' ';
                    }
                }
            }
        }

        // Виведення матриці на екран та запис у файл
        File dataFile = new File("MyFile.txt");
        try (PrintWriter fout = new PrintWriter(dataFile)) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(matrix[i][j] + " ");
                    fout.print(matrix[i][j] + " ");
                }
                System.out.println();
                fout.println();
            }
        }

        System.out.println("Матриця збережена у файл MyFile.txt");
    }
}
