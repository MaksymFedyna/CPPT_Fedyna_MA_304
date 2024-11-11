class User:
    def __init__(self, username: str):
        self.username = username
        self.rented_boats = []

    def rent_boat(self, boat):
        self.rented_boats.append(boat)
        print(f"{self.username} rented {boat.get_name()}.")

    def return_boat(self, boat):
        if boat in self.rented_boats:
            self.rented_boats.remove(boat)
            print(f"{self.username} returned {boat.get_name()}.")
        else:
            print(f"{self.username} does not have {boat.get_name()} rented.")

    def list_rented_boats(self):
        if not self.rented_boats:
            print(f"{self.username} has no rented boats.")
        else:
            print(f"{self.username}'s rented boats:")
            for boat in self.rented_boats:
                print(boat)
