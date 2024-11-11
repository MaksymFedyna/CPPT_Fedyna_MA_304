# Модуль для роботи з файлами: читання та запис результатів у текстовий і двійковий формат

import struct

def write_text_result(filename, result):
    """Запис результату у текстовий файл."""
    with open(filename, 'w') as file:
        file.write(f"{result}\n")


def read_text_result(filename):
    """Читання результату з текстового файлу."""
    with open(filename, 'r') as file:
        return float(file.readline().strip())


def write_binary_result(filename, result):
    """Запис результату у двійковий файл."""
    with open(filename, 'wb') as file:
        file.write(struct.pack('d', result))


def read_binary_result(filename):
    """Читання результату з двійкового файлу."""
    with open(filename, 'rb') as file:
        return struct.unpack('d', file.read())[0]
