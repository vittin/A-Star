package org.cucumbers.astar;

/**
 * Created by Mateusz on 2016-08-14.
 */

class FunctionGSimpleImpl implements FunctionG {

    public Double calc(Field previousField, Field currentField){
        Double previousCost = previousField.getCurrentCost();
        Double currentCost;

        Integer previousFieldCost = previousField.getFieldCost();
        Integer currentFieldCost = currentField.getFieldCost();

        Coordinates current = currentField.getCoordinates();
        Coordinates previous = previousField.getCoordinates();

        if (current.x == previous.x || current.y == previous.y){
            currentCost = previousCost + currentFieldCost;
        } else {
            currentCost = previousCost + Math.sqrt(Math.pow(previousFieldCost,2) + Math.pow(currentFieldCost,2));
        }

        return currentCost;
    }
}
