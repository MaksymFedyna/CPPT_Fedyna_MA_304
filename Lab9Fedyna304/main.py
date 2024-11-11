from motorboat import Motorboat
from rowing_boat import RowingBoat
from user import User
from history import History

class BoatManager:
    def __init__(self):
        self.boats = []
        self.history = History()

    def add_boat(self, boat):
        self.boats.append(boat)
        print(f"{boat.get_name()} added.")

    def remove_boat(self, name):
        self.boats = [boat for boat in self.boats if boat.get_name() != name]
        print(f"Boat named '{name}' removed.")

    def list_boats(self):
        if not self.boats:
            print("No boats available.")
        for boat in self.boats:
            print(boat)

    def perform_action(self, name, action, user: User):
        for boat in self.boats:
            if boat.get_name() == name:
                if action == "rent":
                    boat.rent()
                    user.rent_boat(boat)
                    self.history.add_record("Rented", boat.get_name(), user.username)
                elif action == "return":
                    boat.return_boat()
                    user.return_boat(boat)
                    self.history.add_record("Returned", boat.get_name(), user.username)
                return
        print(f"No boat found with the name '{name}'.")

def main():
    manager = BoatManager()
    user = User("TestUser")  # This could be dynamic in a real system
    while True:
        print("\n=== Boat Management Menu ===")
        print("1. Add Rowing Boat")
        print("2. Add Motorboat")
        print("3. Remove Boat")
        print("4. List Boats")
        print("5. Rent Boat")
        print("6. Return Boat")
        print("7. View User Rented Boats")
        print("8. View History")
        print("9. Exit")

        choice = input("Choose an option (1-9): ")

        if choice == "1":
            name = input("Enter boat name: ")
            length = float(input("Enter length: "))
            width = float(input("Enter width: "))
            capacity = int(input("Enter capacity: "))
            manager.add_boat(RowingBoat(length, width, name, capacity))

        elif choice == "2":
            name = input("Enter boat name: ")
            length = float(input("Enter length: "))
            width = float(input("Enter width: "))
            engine_power = int(input("Enter engine power: "))
            capacity = int(input("Enter capacity: "))
            manager.add_boat(Motorboat(length, width, engine_power, name, capacity))

        elif choice == "3":
            name = input("Enter the name of the boat to remove: ")
            manager.remove_boat(name)

        elif choice == "4":
            manager.list_boats()

        elif choice == "5":
            name = input("Enter the name of the boat to rent: ")
            manager.perform_action(name, "rent", user)

        elif choice == "6":
            name = input("Enter the name of the boat to return: ")
            manager.perform_action(name, "return", user)

        elif choice == "7":
            user.list_rented_boats()

        elif choice == "8":
            manager.history.show_history()

        elif choice == "9":
            print("Exiting the program.")
            break

        else:
            print("Invalid choice, please try again.")

if __name__ == "__main__":
    main()
