package org.cucumbers.astar;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 2016-08-14.
 */

public class SimpleAStarApi implements AStarApi {

    private AStarCore algorithm;
    private Board board;
    private ExtendedAStarInfo extendedInfo;
    private final int[] index = {0};


    private int lastRow;

    public SimpleAStarApi(){
        this.algorithm = new AStarCore(new FunctionGSimpleImpl(), new FunctionHManhattan());
        this.extendedInfo = new ExtendedAStarInfoImpl(algorithm);
    }

    @Override
    public void setBoard(List<Boolean> blockedFields, int mapWidth) throws IllegalArgumentException {
        final List<Field> fields = new ArrayList<>();
        lastRow = 0;
        index[0] = 0;
        final int mod = blockedFields.size() % mapWidth;
        if (mod != 0){
            throw new IllegalArgumentException("Unexpected end of input! Last row should have "
                    + mapWidth + " rows, but it have only " + mod + "!");
        }

        blockedFields.forEach(b -> fields.add(new Field(index[0]++, b)));

        this.board = new Board(fields, mapWidth);
        algorithm.setBoard(this.board);
        lastRow = fields.size() / mapWidth;
    }

    @Override
    public void buildBoard(List<Boolean> blockedFields, int row) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (this.board == null){
            setBoard(blockedFields, blockedFields.size());
        } else if (row > lastRow){
            if (row == lastRow + 1){
                lastRow += 1;
            } else {
                throw new IndexOutOfBoundsException("Map have only " + lastRow + " rows, you should add "
                        + (lastRow+1) + "th row, not " + row  + "th!");
            }
        }
        List<Field> fields = blockedFields.stream()
                .map(b -> new Field(index[0]++, b))
                .collect(Collectors.toList());
        this.board.setRow(row, fields);
        algorithm.setBoard(this.board);
    }

    @Override
    public void buildBoard(List<Boolean> blockedFields) throws IllegalArgumentException {
        buildBoard(blockedFields, lastRow + 1);
    }

    @Override
    public void updateField(Coordinates coordinates, Boolean newValue) throws IllegalArgumentException {
        Field field = this.board.getField(coordinates);
        if (field == null){
            throw new IllegalArgumentException("Field does not exist!");
        }
        field.setBlocked(newValue);
    }

    @Override
    @Nullable
    public Path getPath(Coordinates from, Coordinates target) {
        Field start = this.board.getField(from);
        Field end = this.board.getField(target);
        return algorithm.findPath(start, end);
    }

    @Override
    public ExtendedAStarInfo getExtendedApi() {
        return extendedInfo;
    }
}
