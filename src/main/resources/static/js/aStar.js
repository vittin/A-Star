var clickEvents = function(){

    var tracking = false;

    var triggerTracking = function(){
        tracking = !tracking;
    };

    $("#findPath").click(function(){
        Board.refresh();
        Api.getPath(Path.draw)
    });

    $("#move").click(function() {
        var hero = $(".hero");
        console.log(hero);
        if (hero.length){
            $(".hero").removeClass("hero");
        }
        Api.getPath(Path.move)
    });

    $("#stepByStep").click(function(){
        if (tracking){
            Api.getNextStep(function(response){
                if (response.currentField){
                    Path.showExtendedInfo(response);
                } else {
                    $("#stepByStep").text("enable tracking");
                    Api.getPath(Path.draw);
                    triggerTracking();
                }
            })
        } else {
            Api.enableTracking(function(){
                $("#stepByStep").text("next step");
                    triggerTracking();
            });
        }

    });

    $("#auto").click(function(){
        Api.enableTracking(function(response){
            next();
        });

        var next = function(){
            Api.getNextStep(function(response) {
                if (response.currentField) {
                    Path.showExtendedInfo(response);
                    setTimeout(function () {
                        next()
                    }, 50)
                } else {
                    Api.getPath(Path.draw);
                }
            })
        }
    })
}();



var Api = {
    getPath: function(callback){
        var start = Grid.getStart();
        var finish = Grid.getFinish();
        AjaxRequest.saveBoard(start, finish,
            () => AjaxRequest.findPath(start, finish, callback));
    },

    enableTracking: function(callback){
        var start = Grid.getStart();
        var finish = Grid.getFinish();
        AjaxRequest.saveBoard(start, finish,
            () => AjaxRequest.enableTracking(start, finish, callback));
    },

    getNextStep: function(callback){
        AjaxRequest.getNextStep(callback);
    }
};


var AjaxRequest = {

    saveBoard: function(start, finish, callback){
        var fields = Grid.parse();
        var width = Grid.width;
        $.ajax({
            url: "api/board/set/",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({fields: fields, width: width})
        })
            .done(() => {
                callback();
            })
            .fail((response) => console.log(response))
    },

    findPath: function(startCoordinates, finishCoordinates, callback){
        var x1 = startCoordinates.x;
        var y1 = startCoordinates.y;
        var x2 = finishCoordinates.x;
        var y2 = finishCoordinates.y;

        var url = `api/path/get/from=${x1},${y1},to=${x2},${y2}`;

        $.ajax({
            url: url,
            method: "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .done((response) => callback(response))
            .fail((response) => console.log(response))
    },

    enableTracking: function(startCoordinates, finishCoordinates, callback){
        var x1 = startCoordinates.x;
        var y1 = startCoordinates.y;
        var x2 = finishCoordinates.x;
        var y2 = finishCoordinates.y;

        var url = `api/path/tracking/enableTracking/from=${x1},${y1},to=${x2},${y2}`;

        $.ajax({
            url: url,
            method: "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .done((response) => callback(response))
            .fail((response) => console.log(response));
    },

    getNextStep: function(callback){

        var url = `api/path/tracking/show/nextStep`
        $.ajax({
            url: url,
            method: "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .done((response) => callback(response))
            .fail((response) => console.log(response));
    }
};

var Path = {

    clear: function(){
        Board.refresh();
    },

    allowed: {move: true, create: true},

    draw: function(fieldsCoordinates){
        fieldsCoordinates.forEach(field => {
            var field = $(Path.getField(field.x, field.y));
            field.addClass("result-path");
            field.removeClass("touched");
        });
        Grid.changed = false;
    },

    move: function(fieldsCoordinates){

        if(!Path.allowed.create){
            return;
        }

        var array = fieldsCoordinates.reverse();
        var length = array.length;

        Grid.changed = false;
        Path.allowed.move = true;

        Path.allowed.create = false;

        nextStep(0);



        var previousDOMField;

        function nextStep(index){

            if (Path.allowed.move === false){Path.allowed.move = true; Path.allowed.create = true; return}
            if (index >= length){Path.allowed.create = true; return}

            var field = array[index];

            setTimeout(function(){

                if (previousDOMField){
                    $(previousDOMField).removeClass("hero");
                }

                var DOMfield = Path.getField(field.x, field.y);

                $(DOMfield).addClass("hero");
                previousDOMField = DOMfield;

                nextStep(index+1);
            }, 500);

        }

    },

    showExtendedInfo: function(response){
        console.log(response);
        response.openList.forEach(field => $(Path.getField(field.coordinates.x, field.coordinates.y)).addClass("seen"));
        $(".currentField").removeClass("currentField");
        var current = response.currentField.coordinates;
        var currentField = $(Path.getField(current.x, current.y));
        currentField.addClass("currentField");
        currentField.addClass("touched");
        currentField.removeClass("seen");
        Grid.changed = false;
    },

    getField: function(xCoordinate, yCoordinate){
        var selector = `.y-${yCoordinate} > .x-${xCoordinate}`;
        var field = $(selector);
        return field.children()[0];

    }
};