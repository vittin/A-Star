package org.cucumbers.astar;

/**
 * Created by Mateusz on 2016-08-14.
 */

interface FunctionG {
    Double calc(Field previousField, Field currentField);
}
