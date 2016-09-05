package com.example.controller;

import com.example.model.AStarStepEntity;
import com.sun.istack.internal.Nullable;
import org.cucumbers.astar.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("tracking/enableTracking/from={x1},{y1},to={x2},{y2}")
    public ResponseEntity enableTracking(@PathVariable Integer x1, @PathVariable Integer y1,
                                         @PathVariable Integer x2, @PathVariable Integer y2) {
        api.getExtendedApi().enableTracking(new Coordinates(x1, y1), new Coordinates(x2, y2));
        return ResponseEntity.accepted().body(null);
    }

    @Nullable
    @RequestMapping("tracking/show/nextStep")
    public AStarStepEntity getNextStep(){
        ExtendedAStarInfo extendedApi = api.getExtendedApi();
        if (extendedApi.nextStep()){
            List<Field> openList = extendedApi.getOpenList();
            return new AStarStepEntity(openList, openList.get(0));
        }
        return new AStarStepEntity(null, null);

    }

}