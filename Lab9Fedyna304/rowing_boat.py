class RowingBoat:
    def __init__(self, length: float, width: float, name: str, capacity: int):
        self.__length = length  # Length of the boat
        self.__width = width      # Width of the boat
        self.__name = name        # Name of the boat
        self.__capacity = capacity  # Number of people the boat can hold
        self.__available = True    # Availability status

    def get_length(self) -> float:
        return self.__length

    def get_width(self) -> float:
        return self.__width

    def get_name(self) -> str:
        return self.__name

    def get_capacity(self) -> int:
        return self.__capacity

    def is_available(self) -> bool:
        return self.__available

    def rent(self):
        if self.__available:
            self.__available = False
            print(f"{self.__name} has been rented.")
        else:
            print(f"{self.__name} is currently not available.")

    def return_boat(self):
        self.__available = True
        print(f"{self.__name} has been returned.")

    def row(self):
        print(f"{self.__name} is being rowed.")

    def __str__(self):
        availability = "Available" if self.__available else "Not Available"
        return f"RowingBoat(Name: {self.__name}, Length: {self.__length}m, Width: {self.__width}m, Capacity: {self.__capacity}, Status: {availability})"
