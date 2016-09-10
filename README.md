# A-Star
## The leading pathfinding algorithm. A combination of Dijkstra's algorithm and best-first algorithm.

![autoMode](http://g.recordit.co/QxMDP6yWzB.gif)

A* algorithm is widely used in pathfinding and graph traversal. Noted for its performance and accuracy, it enjoys widespread use.


Project is segmented, each module using only public api to communicate with other.

The **CoreLibrary**, is precompiled and included as maven dependency. It use pure Java 8.

The **RestApi**, using CoreLibrary. It was written in Java 8 and Spring MVC.

The **UI** using Bootstrap, JS (ECMA 6) and JQuery. 



## Application have 4 different way to display shortest path:

### 1) Find Path 

Just show the shortest way

![justshowpath](https://cloud.githubusercontent.com/assets/9084222/18342429/2073dc78-75af-11e6-9d0e-94d2328374fc.jpg)

### 2) Move

Show step by step shortest path

![move](http://g.recordit.co/QEsUOL5aTz.gif)

### 3)Enable tracking -> next step -> next step -> ...

Show step by step how algorithm works and which fields are checked. 

![tracking1](https://cloud.githubusercontent.com/assets/9084222/18342689/58d75bc0-75b0-11e6-9011-a75134ab18c9.jpg)
![tracking2](https://cloud.githubusercontent.com/assets/9084222/18342692/58de15be-75b0-11e6-8737-4d9e0c158085.jpg)
![tracking3](https://cloud.githubusercontent.com/assets/9084222/18342690/58d8a520-75b0-11e6-8fe2-8f91baae56b2.jpg)

### 4) Auto

![autoMode](http://g.recordit.co/QxMDP6yWzB.gif)
