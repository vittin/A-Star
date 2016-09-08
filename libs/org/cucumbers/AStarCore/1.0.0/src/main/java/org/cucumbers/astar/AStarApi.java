package org.cucumbers.astar;

import com.sun.istack.internal.Nullable;

import java.util.List;

/**
 * Created by Mateusz on 2016-08-14.
 */

public interface AStarApi {

    /**
     *
     * @param blockedFields accept list of booleans - true means that field is locked.
     * @param boardWidth determines map width.
     */
    void setBoard(List<Boolean> blockedFields, int boardWidth) throws IllegalArgumentException;

    void buildBoard(List<Boolean> blockedFields, int row) throws IndexOutOfBoundsException, IllegalArgumentException;

    void buildBoard(List<Boolean> blockedFields) throws IllegalArgumentException;

    void updateField(Coordinates coordinates, Boolean newValue) throws IllegalArgumentException;

    @Nullable
    Path getPath(Coordinates from, Coordinates target);

    ExtendedAStarInfo getExtendedApi();

}
