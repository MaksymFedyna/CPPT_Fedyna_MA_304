from rowing_boat import RowingBoat

class Motorboat(RowingBoat):
    def __init__(self, length: float, width: float, engine_power: int, name: str, capacity: int):
        super().__init__(length, width, name, capacity)
        self.__engine_power = engine_power  # Power of the engine
        self.__engine_running = False         # Engine status

    def get_engine_power(self) -> int:
        return self.__engine_power

    def start_engine(self):
        if not self.__engine_running:
            self.__engine_running = True
            print(f"The motorboat '{self.get_name()}' with power {self.__engine_power} HP is started.")
        else:
            print(f"The engine of '{self.get_name()}' is already running.")

    def stop_engine(self):
        if self.__engine_running:
            self.__engine_running = False
            print(f"The engine of '{self.get_name()}' is stopped.")
        else:
            print(f"The engine of '{self.get_name()}' is already stopped.")

    def move(self):
        if self.__engine_running:
            print(f"The motorboat '{self.get_name()}' is moving swiftly.")
        else:
            print(f"Start the engine of '{self.get_name()}' before moving.")

    def __str__(self):
        availability = "Available" if self.is_available() else "Not Available"
        return f"Motorboat(Name: {self.get_name()}, Length: {self.get_length()}m, Width: {self.get_width()}m, Engine Power: {self.__engine_power} HP, Capacity: {self.get_capacity()}, Status: {availability})"
