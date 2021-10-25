import math
from Salesman import Salesman

def potomstwo(currentNode, allCities):
    howMany = len(allCities)

    if len(currentNode.path) >= howMany:
        return -1

    result = []

    for i in range(howMany):

        temp = Salesman(currentNode.currentCity, currentNode.path, currentNode.cost)
        if i in temp.path:
            continue
        else:
            temp.cost += math.sqrt(
                (allCities[i][1] - allCities[currentNode.currentCity][1]) ** 2 + (allCities[i][2] - allCities[currentNode.currentCity][2]) ** 2)
            temp.path.append(i)
            temp.currentCity = i
            result.append(temp)


    return result
