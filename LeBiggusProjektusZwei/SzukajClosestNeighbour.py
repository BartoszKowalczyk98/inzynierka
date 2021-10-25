import random
import time

import matplotlib.pyplot as plt
from Salesman import Salesman
from Potomstwo import potomstwo


def findClosest(listOfStates):
    smallestCost = listOfStates[0].cost
    indexOfClosestNeighbour = 0
    for i in range(len(listOfStates)):
        if listOfStates[i].cost < smallestCost:
            smallestCost = listOfStates[i].cost
            indexOfClosestNeighbour = i

    return indexOfClosestNeighbour


howManyCities = int(input("Podaj liczbe miast: "))
starttime = time.time()
cities = []
for i in range(howManyCities):
    cities.append([i,random.randint(1,40),random.randint(1,40)])


startingIndex = random.randrange(0, howManyCities)
currentState = Salesman(startingIndex, [startingIndex], 0.0)

states = currentState

for i in range(howManyCities - 1):
    temp = potomstwo(states, cities)
    states = temp[findClosest(temp)]

endtime = time.time()
x = []
y = []
for i in states.path:
    x.append(cities[i][1])
    y.append(cities[i][2])

x.append(cities[states.path[0]][1])
y.append(cities[states.path[0]][2])
plt.plot(x, y)
for i in range(len(x)-1):
    label=states.path[i]
    plt.annotate(label,  # this is the text
                 (x[i], y[i]),  # this is the point to label
                 textcoords="offset points",  # how to position the text
                 xytext=(0, 10),  # distance from text to points (x,y)
                 ha='center')  # horizontal alignment can be left, right or center
print("kolejność odwiedzonych miast: ")
print(states.path)
print("czas na najbliżeszego sąsiada: "+str(endtime-starttime))
plt.show()
