package com.example.controller;

import org.cucumbers.astar.AStarApi;
import org.cucumbers.astar.SimpleAStarApi;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mateusz on 2016-08-16.
 */


@Controller
@RequestMapping("/")
@Scope("session")
public class SessionController {

    private AStarApi api;

    SessionController(){
        this.api = new SimpleAStarApi();
    }

    AStarApi getApi(){
        return this.api;
    }

    @RequestMapping("")
    public String indexPage(){
        return "aStar.html";
    }
}
