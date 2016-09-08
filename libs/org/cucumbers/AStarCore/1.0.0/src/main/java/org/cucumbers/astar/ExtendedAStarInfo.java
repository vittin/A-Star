package org.cucumbers.astar;

import java.util.List;

/**
 * Created by Mateusz on 2016-09-05.
 */

public interface ExtendedAStarInfo {

    void enableTracking(Coordinates from, Coordinates target);

    boolean nextStep();

    List<Field> getOpenList();

    List<Field> getCloseList();

}
