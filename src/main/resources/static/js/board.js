/**
 * Created by mati on 2016-08-11.
 */

$(document).on('ready', function () {
    Grid.width = 50;
    Grid.height = 20;
    Board.createClickableGrid(Grid.width, Grid.height);
    Board.setField("start", 411);
    Board.setField("finish", 437);
    Board.enableClickEvent();
});

Grid = {
    width: 0,
    height: 0,
    changed: true,
    parse: function(){
        var list = [];
        $(".field").each(function(index, element){
            var child = $($(element).children()[0]);
            list[parseInt($(element).attr("id"))] = child.hasClass("clicked");
        });
        return list;
    },
    getStart: function(){
        return Board.getField("start");
    },
    getFinish: function () {
        return Board.getField("finish");
    }
};

var Board = {

    refresh: function(){
        if (!Grid.changed){
            Grid.changed = true;
            $(".result-path").removeClass("result-path");
        }
    },

    createClickableGrid: function(cols, rows){

        var i=0;

        var grid = $('<table class="grid"></table>');
        $("#board").append(grid);

        for (var r = 0; r < rows; r++){

            var tr = $(`<tr class="y-${r}"></tr>`);
            grid.append(tr);

            for (var c = 0; c < cols; c++){

                var cell = $(`<td class="field x-${c}" id="${i++}"><div class="clickable"></div></td>`);
                tr.append(cell);
            }
        }

        return grid;
    },


    enableClickEvent: function(){
        ClickEvent.walls();
        ClickEvent.entryPoint();

    },

    setField: function(type, id){
        var elem = $("#" + id);
        $(elem).children().remove();
        $(elem).append('<div class='+type+'></div>');
    },

    getField: function(type){
        var id = ($("." + type).parent().attr("id"));
        var x = id % Grid.width;
        var y = Math.floor(id / Grid.width);
        return new Coordinates(x, y);
    }
};

var Coordinates = function(x, y){
    this.x = x;
    this.y = y;
};

var ClickEvent = {

    toggleClass(element){
        element.toggleClass("clicked");
        Board.refresh();
    },

    walls: function(){
        var clicking = false;
        var element;
        var elementId;
        var clickable = $(".clickable");
        $(clickable).on("mousedown", function () {
            clicking = true;
            element = $(this);
            elementId = element.parent().attr("id");

            ClickEvent.toggleClass(element);
        });

        $(document).mouseup(function(){
            clicking = false;
        });

        $(clickable).mousemove(function(){
            if(clicking == false) {return};

            element = $(this);
            var id = element.parent().attr("id");
            if (id == elementId){return};
            elementId = id;
            ClickEvent.toggleClass(element);
        });
    },
    entryPoint: function(){
        $(".start, .finish").on("click", function entryClick() {
            Board.refresh();
            var old = $(this);
            var oldId = old.parent().attr("id");
            old.addClass("editable");
            var type = old.attr("class");
            console.log(old);
            $("td").on("mouseup", function newEntryPoint(){
                var id = $(this).attr("id");
                old.remove();
                $("#"+oldId).append('<div class="clickable"> </div>');

                Board.setField(type, id);


                //tier down
                $("td").unbind("mouseup", newEntryPoint);
                var entryPoints = $(".start, .finish");
                entryPoints.unbind("click", entryClick);
                entryPoints.bind("click", entryClick);
            })
        })
    }
};