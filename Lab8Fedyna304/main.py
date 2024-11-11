# Основна програма для обчислення значення та роботи з файлами

from equation import calculate, CalcException
from file_handler import write_text_result, read_text_result, write_binary_result, read_binary_result

def main():
    try:
        # Введення кута в градусах від користувача
        x_degrees = float(input("Введіть значення X (у градусах): "))

        # Обчислення значення функції y = 1 / cos(4x)
        result = calculate(x_degrees)
        print(f"Обчислений результат: {result}")

        # Запис результату у текстовий файл
        write_text_result("result.txt", result)
        print("Результат записано у текстовий файл 'result.txt'.")

        # Запис результату у двійковий файл
        write_binary_result("result.bin", result)
        print("Результат записано у двійковий файл 'result.bin'.")

        # Читання з текстового файлу
        text_result = read_text_result("result.txt")
        print(f"Прочитано з текстового файлу: {text_result}")

        # Читання з двійкового файлу
        binary_result = read_binary_result("result.bin")
        print(f"Прочитано з двійкового файлу: {binary_result}")

    except ValueError:
        print("Помилка: введено некоректне значення для X.")
    except CalcException:
        print("Обчислення не виконано через математичну помилку.", CalcException)
        write_binary_result("result.bin", CalcException)
        write_text_result("result.txt", CalcException)
    except FileNotFoundError as e:
        print(f"Файл не знайдено: {e}")
    except Exception as e:
        print(f"Виникла неочікувана помилка: {e}")

if __name__ == "__main__":
    main()
