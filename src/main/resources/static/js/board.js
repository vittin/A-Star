/**
 * Created by mati on 2016-08-11.
 */

$(document).on('ready', function () {
    var grid = Board.createClickableGrid(50, 20);
    Board.setField("start", 411);
    Board.setField("end", 437);
    Board.enableClickEvent();
});


var Board = {

    createClickableGrid: function(cols, rows){

        var i=0;

        var grid = $('<table class="grid"></table>');
        $("#board").append(grid);

        for (var r = 0; r < rows; r++){

            var tr = $('<tr></tr>');
            grid.append(tr);

            for (var c = 0; c < cols; c++){

                var cell = $('<td id=' + i++ + '><div class="clickable"> </div></td>');
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
    }
};

var ClickEvent = {
    walls: function(){
        var clicking = false;
        var clicked = "clicked";
        var element;
        var elementId;
        var clickable = $(".clickable");
        $(clickable).on("mousedown", function () {
            clicking = true;
            element = $(this);
            elementId = element.attr("id");

            element.toggleClass(clicked);
        });

        $(document).mouseup(function(){
            clicking = false;
        });

        $(clickable).mousemove(function(){
            if(clicking == false) {return};

            element = $(this);
            var id = element.attr("id");
            if (id == elementId){return};

            elementId = id;
            element.toggleClass(clicked);
        });
    },
    entryPoint: function(){
        $(".start, .end").on("click", function entryClick() {
            var old = $(this);
            var oldId = old.parent().attr("id");
            old.addClass("editable");
            var type = old.attr("class");
            console.log(old);
            $("td").on("mousedown", function newEntryPoint(){
                var id = $(this).attr("id");
                old.remove();
                $("#"+oldId).append('<div class="clickable"> </div>');

                Board.setField(type, id);


                //tier down
                $("td").unbind("mousedown", newEntryPoint);
                var entryPoints = $(".start, .end");
                entryPoints.unbind("click", entryClick);
                entryPoints.bind("click", entryClick);
            })
        })
    }
};