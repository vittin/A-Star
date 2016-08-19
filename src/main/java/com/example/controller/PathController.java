package com.example.controller;

import org.cucumbers.astar.AStarApi;
import org.cucumbers.astar.Coordinates;
import org.cucumbers.astar.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 2016-08-16.
 */

@RestController
@RequestMapping("api/path/")
@Scope("session")
public class PathController {

    private final AStarApi api;

    @Autowired
    PathController(SessionController session) {
        this.api = session.getApi();
    }

    @RequestMapping("get/from={x1},{y1},to={x2},{y2}")
    public List<Coordinates> getPath(@PathVariable Integer x1, @PathVariable Integer y1,
                                     @PathVariable Integer x2, @PathVariable Integer y2) {

        Path path = api.getPath(new Coordinates(x1, y1), new Coordinates(x2, y2));
        if (path == null){
            return new ArrayList<>();
        }
        return path.getCoordinates();
    }

}