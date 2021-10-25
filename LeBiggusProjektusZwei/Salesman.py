import copy


class Salesman:
    currentCity = -1
    path = []
    cost = 0

    def __init__(self, index, path, cost) -> None:
        super().__init__()
        self.currentCity = index
        self.path = copy.deepcopy(path)
        self.cost = cost

    def __lt__(self, other):
        return self.cost < other.cost