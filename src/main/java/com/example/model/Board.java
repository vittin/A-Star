package com.example.model;

import java.util.List;

/**
 * Created by Mateusz on 2016-08-17.
 */

public class Board {

    private List<Boolean> fields;
    private Integer width;

    Board(){}

    public Board(List<Boolean> fields, Integer width){
        this.fields = fields;
        this.width = width;
    }

    public void setFields(List<Boolean> fields) {
        this.fields = fields;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public List<Boolean> getFields() {
        return fields;
    }

    public Integer getWidth() {
        return width;
    }
}
