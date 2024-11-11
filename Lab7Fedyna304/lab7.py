
def generate_pattern(n, fill_char):
    """Генерація зубчастого списку для лівого та правого трикутників."""
    jagged_array = []

    # Формування зубчастого масиву
    for i in range(n):
        # Якщо i < n // 2 (верхня частина матриці) - заповнюємо рядки зліва та справа
        if i < n // 2:
            row_length = i + 1
        # Якщо i >= n // 2 (нижня частина матриці) - зменшуємо довжину заповнених елементів
        else:
            row_length = n - i

        # Створення зубчастого рядка з потрібною кількістю заповнених елементів
        jagged_row = [fill_char] * row_length
        jagged_array.append(jagged_row)

    return jagged_array


def format_and_print_pattern(jagged_array, n, fill_char):
    """Форматований вивід зубчастого масиву у вигляді симетричної матриці."""
    for row in jagged_array:
        # Довжина лівої частини
        row_length = len(row)

        # Вираховуємо кількість пробілів, враховуючи розмір матриці
        # `spaces` потрібно обчислювати як n - 2 * row_length для коректного відступу
        spaces = n - 2 * row_length + 1  # Додаємо 1, щоб скоригувати для непарного n

        # Виведення рядка з відповідними пробілами
        print(fill_char * row_length + ' ' * spaces + fill_char * (row_length-1) + 's')


if __name__ == "__main__":
    try:
        # Введення розміру матриці та символу-заповнювача
        n = int(input("Введіть розмір квадратної матриці (ціле число): "))
        fill_char = input("Введіть символ-заповнювач (один символ): ")

        # Перевірка введення символу-заповнювача
        if len(fill_char) != 1:
            raise ValueError("Помилка: введіть лише один символ для заповнення!")

        # Генерація та виведення шаблону
        pattern = generate_pattern(n, fill_char)
        format_and_print_pattern(pattern, n, fill_char)

    except ValueError as e:
        print(e)
