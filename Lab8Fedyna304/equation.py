# Модуль для обчислення математичного виразу y = 1 / cos(4x)
from file_handler import write_text_result, read_text_result, write_binary_result, read_binary_result
import math

class CalcException(Exception):
    """Клас для обробки арифметичних помилок."""
    def __init__(self, message="Помилка обчислення"):
        self.message = message
        super().__init__(self.message)


def calculate(x_degrees):
    """Функція для обчислення y = 1 / cos(4x), де x в градусах."""
    try:
        # Переводимо градуси у радіани
        x_radians = math.radians(x_degrees)
        # Обчислення косинуса
        cos_value = math.cos(4 * x_radians)

        # Перевірка на мале значення cos(4x)
        if abs(cos_value) < 1e-10:
            raise CalcException("cos(4x) дуже близький до нуля, ділення на нуль неможливе")

        # Обчислення виразу
        return 1 / cos_value

    except CalcException as e:
        print(f"Помилка: {e}")
        return e.message
        raise
