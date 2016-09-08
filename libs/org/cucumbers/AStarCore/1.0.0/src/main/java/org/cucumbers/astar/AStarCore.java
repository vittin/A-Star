package org.cucumbers.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 2016-08-14.
 */

class AStarCore {

    private FunctionG g;
    private FunctionH h;
    private Board board;

    private final List<Field> open;
    private final List<Field> close;
    private Field target;

    AStarCore(FunctionG functionG, FunctionH functionH){
        this.open = new ArrayList<>();
        this.close = new ArrayList<>();
        this.g = functionG;
        this.h = functionH;
    }

    AStarCore(FunctionG functionG, FunctionH functionH, Board board){
        this(functionG, functionH);
        this.board = board;
    }

    void setBoard(Board board){
        this.board = board;
    }

    Board getBoard(){return this.board;}

    void prepareBoard(Field start, Field target){
        refreshLists();
        setTarget(target);
        start.setCurrentCost(0);
        open.add(start);
    }

    private void refreshLists(){
        this.open.clear();
        this.close.clear();
    }

    private void setTarget(Field target){
        this.target = target;
    }

    Path findPath(Field start, Field target){
        prepareBoard(start, target);
        while (open.size() > 0){
            Field bestField = open.get(0);
            if (isTarget(bestField)){
               return new Path(bestField);
            }
            nextStep(open.get(0));
            sortList();
        }
        return null;
    }

    boolean isTarget(Field bestField){
        return bestField.equals(target);
    }

    void sortList(){
        open.sort(Field::compareTo);
    }

    void nextStep(Field field){
        List<Field> newFields = updateOpenList(field);

        newFields.forEach(f -> updateRealCost(field, f));

        open.remove(field);
        close.add(field);
    }

    private List<Field> updateOpenList(Field field){
        List<Coordinates> neighbours = board.getNeighbours(field.getCoordinates());
        List<Field> newFields = neighbours.stream()
                .map(board::getField)
                .collect(Collectors.toList());

        newFields.stream()
                .filter(f -> !close.contains(f))
                .filter(f -> !open.contains(f))
                .filter(f -> !f.isBlocked())
                .peek(this::updateHeuristicCost)
                .forEach(open::add);

        return newFields;
    }

    private void updateHeuristicCost(Field field) {
        field.setHeuristicCost(h.calc(field, target));
    }

    private void updateRealCost(Field parent, Field child) {
        Double cost = g.calc(parent, child);
        if (child.getCurrentCost() == null || child.getCurrentCost() > cost){
            child.setCurrentCost(cost);
            child.setParent(parent);
        }
    }

    List<Field> getOpen() {
        return open;
    }

    List<Field> getClose() {
        return close;
    }
}
