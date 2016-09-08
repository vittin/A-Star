package org.cucumbers.astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mateusz on 2016-08-14.
 */

class Board {

    private final Map<Coordinates, Field> fields;
    private final int width;
    private int height;

    Board(int width){
        this.width = width;
        this.height = 0;
        this.fields = new HashMap<>();
    }

    Board(List<Field> fields, int width){
        this(width);
        fields.forEach(f -> {
            int id = f.getId();
            int col = id % width;
            height = (id / width);
            f.setCoordinates(col, height);
            this.fields.put(f.getCoordinates(), f);
        });
    }

    void setRow(int row, List<Field> blockedFields) throws IndexOutOfBoundsException, IllegalArgumentException {
        int length = blockedFields.size();
        if (length != width){
            throw new IllegalArgumentException("Map have only " + width + "columns, you are trying add " + length + " elements!");
        } else {
            blockedFields.forEach(f -> fields.put(f.getCoordinates(), f));
            if (row > height){
                height +=1;
            }
        }
    }

    Field getField(Coordinates coords){
        return fields.get(coords);
    }

    List<Coordinates> getNeighbours(Coordinates fieldCoords) {
        int currentX = fieldCoords.x;
        int currentY = fieldCoords.y;

        List<Coordinates> neighboursCoords = new ArrayList<>();

        Coordinates coords;
        for (int x = -1; x <= 1; x++){
            for (int y = -1; y <= 1; y++){
                if (x != 0 || y != 0) {
                    coords = new Coordinates(currentX + x, currentY + y);
                    //height is integer and it is rounded down, so "<="
                    if (coords.x >= 0 && coords.y >= 0 && coords.x < width && coords.y <= height){
                        neighboursCoords.add(coords);
                    }
                }
            }
        }
        return neighboursCoords;
    }
}
