package org.cucumbers.astar;

/**
 * Created by Mateusz on 2016-08-14.
 */

class FunctionHManhattan implements FunctionH {
    @Override
    public Double calc(Field currentField, Field target) {
        Coordinates currentCoords = currentField.getCoordinates();
        Coordinates targetCoords = target.getCoordinates();
        int cost = currentField.getFieldCost();
        int shortestWay = Math.abs(currentCoords.x - targetCoords.x) + Math.abs(currentCoords.y - targetCoords.y);

        return (double) cost * shortestWay;
    }
}
