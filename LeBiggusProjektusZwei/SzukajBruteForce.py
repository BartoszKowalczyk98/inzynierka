import random
import time

import matplotlib.pyplot as plt
from Salesman import Salesman
from Potomstwo import potomstwo

howManyCities = int(input("Podaj liczbe miast: "))
starttime = time.time()
cities = []
for i in range(howManyCities):
    cities.append([i,random.randint(1,40),random.randint(1,40)])


startingIndex = random.randrange(0, howManyCities)
currentState = Salesman(startingIndex, [startingIndex], 0.0)

states = potomstwo(currentState, cities)

while len(states[0].path)!=howManyCities:
    appender = []
    for i in states:
        temp = potomstwo(i,cities)
        for j in temp:
            appender.append(j)
    states = appender


states.sort()

endtime = time.time()
x = []
y = []
for i in states[0].path:
    x.append(cities[i][1])
    y.append(cities[i][2])

x.append(cities[states[0].path[0]][1])
y.append(cities[states[0].path[0]][2])
plt.plot(x, y)
for i in range(len(x)-1):
    label=states[0].path[i]
    plt.annotate(label,  # this is the text
                 (x[i], y[i]),  # this is the point to label
                 textcoords="offset points",  # how to position the text
                 xytext=(0, 10),  # distance from text to points (x,y)
                 ha='center')  # horizontal alignment can be left, right or center
print("kolejność odwiedzonych miast: ")
print(states[0].path)
print("czas na bruteforce: "+str(endtime-starttime))
plt.show()
