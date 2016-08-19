package com.example.controller;

import com.example.model.Board;
import org.cucumbers.astar.AStarApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mateusz on 2016-08-16.
 */

@RestController
@RequestMapping("api/board/")
@Scope("session")
public class BoardController {

    private final AStarApi api;

    @Autowired
    BoardController(SessionController session){
        this.api = session.getApi();
    }

    @RequestMapping(value = "set", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity setBoard(@RequestBody Board board) {

        try {
            //fields.forEach((k, v) -> fieldsList.add(Boolean.valueOf(v)));
            api.setBoard(board.getFields(), board.getWidth());
        } catch (IllegalArgumentException illegalArgument) {
            return ResponseEntity.unprocessableEntity().body(illegalArgument.getMessage());
        }
        return ResponseEntity.accepted().body(null);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public HttpEntity updateBoard(@RequestBody List<Boolean> fields) {
        try {
            api.buildBoard(fields, fields.size());
        } catch(IndexOutOfBoundsException outOfBounds){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(outOfBounds.getMessage());
        } catch (IllegalArgumentException illegalArgument){
            return ResponseEntity.unprocessableEntity().body(illegalArgument.getMessage());
        }

        return ResponseEntity.accepted().body(null);
    }
}
