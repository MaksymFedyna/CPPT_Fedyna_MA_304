package KI304.Fedyna.Lab6;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас Jar представляє контейнер, що містить елементи типу T.
 * @param <T> тип елементів, що зберігаються у банці
 * @author Fedyna Maksym
 * @version 1.1
 */
public class Jar<T> {
    private List<T> items;

    /**
     * Конструктор, який створює порожню банку.
     */
    public Jar() {
        items = new ArrayList<>();
    }

    /**
     * Додає елемент до банки.
     * @param item елемент, який додається
     */
    public void add(T item) {
        items.add(item);
        System.out.println("Додано елемент: " + item);
    }

    /**
     * Видаляє елемент з банки та повертає його.
     * @return видалений елемент
     * @throws IllegalStateException якщо банка порожня
     */
    public T remove() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Банка порожня!");
        }
        T item = items.remove(items.size() - 1);
        System.out.println("Видалено елемент: " + item);
        return item;
    }

    /**
     * @return кількість елементів
     */
    public int getCount() {
        return items.size();
    }

    /**
     * Повертає максимальний елемент у банці для непарних варіантів (для парних - мінімальний).
     * @return максимальний або мінімальний елемент
     * @throws IllegalStateException якщо банка порожня
     * @throws ClassCastException якщо елементи не підтримують порівняння
     */
    public T findMax() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Банка порожня!");
        }

        T max = items.get(0);
        for (T item : items) {
            if (((Comparable<T>) item).compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    /**
     * Показує вміст банки.
     */
    public void displayContents() {
        if (items.isEmpty()) {
            System.out.println("Банка порожня!");
        } else {
            System.out.println("Вміст банки: " + items);
        }
    }

    public static void main(String[] args) {
        Jar<Integer> integerJar = new Jar<>();
        integerJar.add(15);
        integerJar.add(42);
        integerJar.add(7);
        integerJar.add(99);
        integerJar.displayContents();
        System.out.println("Максимальний елемент: " + integerJar.findMax());
        integerJar.remove();
        integerJar.add(100);
        integerJar.displayContents();
        System.out.println("Number of strings: " + integerJar.getCount());

        Jar<String> stringJar = new Jar<>();
        stringJar.add("Apple");
        stringJar.add("Banana");
        stringJar.add("Cherry");
        stringJar.displayContents();
        System.out.println("Максимальний елемент: " + stringJar.findMax());
        stringJar.remove();
        stringJar.displayContents();
        System.out.println("Number of strings: " + stringJar.getCount() + '\n' +"Number of numbers: " + integerJar.getCount());

    }
}
