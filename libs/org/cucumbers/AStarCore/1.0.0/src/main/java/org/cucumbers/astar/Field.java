package org.cucumbers.astar;

/**
 * Created by Mateusz on 2016-08-14.
 */

public class Field {

    private final int id;
    private final int fieldCost;
    private Coordinates coordinates;

    private Boolean blocked;
    private boolean checked = false;
    private boolean closed = false;

    private Field parent;
    private Double currentCost;
    private Double heuristicCost;


    Field(int id, Boolean blocked){
        this.id = id;
        this.blocked = blocked;
        this.fieldCost = 10;
    }

    Field(int id, int cost, Boolean blocked){
        this.id = id;
        this.blocked = blocked;
        this.fieldCost = cost;
    }

    public Coordinates getCoordinates(){
        return this.coordinates;
    }

    public Field getParent(){
        return parent;
    }

    void setCoordinates(int x, int y){
        this.coordinates = new Coordinates(x,y);
    }

    void setParent(Field parent){
        this.parent = parent;
    }

    int getId(){
        return id;
    }

    Double getCurrentCost() {
        return currentCost;
    }

    void setCurrentCost(double currentCost) {
        this.currentCost = currentCost;
    }

    int getFieldCost() {
        return fieldCost;
    }

    void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    Double getHeuristicCost() {
        return heuristicCost;
    }

    void setHeuristicCost(double heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    boolean hasParent(){
        return parent != null;
    }

    boolean isBlocked() {
        return this.blocked;
    }

    int compareTo(Field f2) {
        return (this.currentCost + this.heuristicCost < f2.currentCost + f2.heuristicCost) ? -1 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (blocked != field.blocked) return false;
        if (!coordinates.equals(field.coordinates)) return false;
        return parent != null ? parent.equals(field.parent) : field.parent == null;

    }

    @Override
    public int hashCode() {
        int result = coordinates.hashCode();
        result = 31 * result + (blocked ? 1 : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Field{" +
                "coordinates=" + coordinates +
                ", currentCost=" + currentCost +
                '}';
    }
}
