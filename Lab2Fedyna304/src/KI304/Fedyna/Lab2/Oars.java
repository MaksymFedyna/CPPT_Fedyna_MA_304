package KI304.Fedyna.Lab2;

public class Oars {
    private int count; // Кількість весел

    public Oars() {
        this.count = 2; // За замовчуванням 2 весла
    }

    public Oars(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
