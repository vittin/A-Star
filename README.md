# A-Star
## The leading pathfinding algorithm. A combination of Dijkstra's algorithm and best-first algorithm.

![autoMode](http://g.recordit.co/QxMDP6yWzB.gif)

A* algorithm is widely used in pathfinding and graph traversal. Noted for its performance and accuracy, it enjoys widespread use.


Project is segmented, each module using only public api to communicate with other.

The **CoreLibrary**, which is precompiled and included as maven dependency, using pure Java 8.

The **RestApi**, which using CoreLibrary, was written in Java 8 and Spring MVC.

The **UI** using Bootstrap, JS (ECMA 6) and JQuery. 

All modules have been written by me.


## Application have 4 different way to show shortest path:

### 1) Find Path 

Just show the shortest way

![justshowpath](https://cloud.githubusercontent.com/assets/9084222/18342429/2073dc78-75af-11e6-9d0e-94d2328374fc.jpg)

### 2) Move

Show step by step shortest path

![move0](https://cloud.githubusercontent.com/assets/9084222/18342520/8946d96c-75af-11e6-8492-7b25f834f7fc.jpg)
![move1](https://cloud.githubusercontent.com/assets/9084222/18342522/8952648a-75af-11e6-95da-6ff592358d61.png)
![move2](https://cloud.githubusercontent.com/assets/9084222/18342521/894aa5c4-75af-11e6-89b9-2f13bc5975c7.jpg)

### 3)Enable tracking -> next step -> next step -> ...

Show step by step how algorithm works and which fields are checked. 

![tracking1](https://cloud.githubusercontent.com/assets/9084222/18342689/58d75bc0-75b0-11e6-9011-a75134ab18c9.jpg)
![tracking2](https://cloud.githubusercontent.com/assets/9084222/18342692/58de15be-75b0-11e6-8737-4d9e0c158085.jpg)
![tracking3](https://cloud.githubusercontent.com/assets/9084222/18342691/58dd6344-75b0-11e6-83ea-97b552b0f82d.jpg)
![tracking4](https://cloud.githubusercontent.com/assets/9084222/18342690/58d8a520-75b0-11e6-8fe2-8f91baae56b2.jpg)

### 4) Auto

![autoMode](http://g.recordit.co/QxMDP6yWzB.gif)
