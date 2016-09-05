package com.example.model;

import org.cucumbers.astar.Field;

import java.util.List;

/**
 * Created by Mateusz on 2016-09-05.
 */

public class AStarStepEntity {
    private final List<Field> openList;
    private final Field currentField;

    public AStarStepEntity(List<Field> openList, Field currentField) {
        this.openList = openList;
        this.currentField = currentField;
    }

    public List<Field> getOpenList() {
        return openList;
    }

    public Field getCurrentField() {
        return currentField;
    }

    @Override
    public String toString() {
        return "AStarStepEntity{" +
                "openList=" + openList +
                ", currentField=" + currentField +
                '}';
    }
}
