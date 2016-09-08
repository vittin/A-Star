package org.cucumbers.astar;

import java.util.List;

/**
 * Created by Mateusz on 2016-09-05.
 */

class ExtendedAStarInfoImpl implements ExtendedAStarInfo{

    private AStarCore algorithm;

    ExtendedAStarInfoImpl(AStarCore algorithm){
        this.algorithm = algorithm;
    }

    @Override
    public void enableTracking(Coordinates from, Coordinates target) {
        Board board = algorithm.getBoard();
        Field start = board.getField(from);
        Field finish = board.getField(target);

        algorithm.prepareBoard(start, finish);
    }

    //is next step exists?
    @Override
    public boolean nextStep() {
        List<Field> openList = getOpenList();
        Field bestField = openList.get(0);
        if (algorithm.isTarget(bestField)){
            return false;
        } else {
            algorithm.nextStep(bestField);
            algorithm.sortList();
            return true;
        }
    }

    @Override
    public List<Field> getOpenList() {
        return algorithm.getOpen();
    }

    @Override
    public List<Field> getCloseList() {
        return algorithm.getClose();
    }
}
