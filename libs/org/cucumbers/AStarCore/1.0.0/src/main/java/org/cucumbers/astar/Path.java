package org.cucumbers.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 2016-08-14.
 */

public class Path {

    final List<Field> path;

    Path(Field target) {
        path = new ArrayList<>();

        Field f = target;
        while (f.hasParent()){
            path.add(f);
            f = f.getParent();
        }
        path.add(f);
    }

    public List<Field> getPath(){
        return path;
    }

    public List<Coordinates> getCoordinates() {
        return path.stream()
                .map(Field::getCoordinates)
                .collect(Collectors.toList());
    }
}
