class History:
    def __init__(self):
        self.records = []

    def add_record(self, action: str, boat_name: str, user: str):
        self.records.append(f"{action} - {boat_name} by {user}")

    def show_history(self):
        if not self.records:
            print("No history records available.")
        else:
            print("Action History:")
            for record in self.records:
                print(record)
