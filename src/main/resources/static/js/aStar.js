$("#findPath").click(function(){
    Api.getPath(Path.draw)
});

$("#move").click(function() {
    Api.getPath(Path.move)
});


var Api = {
    getPath: function(callback){
        Board.refresh();
        AjaxRequest.saveBoardAndFindPath(callback);
    },
};


var AjaxRequest = {

    saveBoardAndFindPath: function(callback){
        var fields = Grid.parse();
        var width = Grid.width;
        $.ajax({
            url: "api/board/set/",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({fields: fields, width: width})
        })
            .done((response) => {
                AjaxRequest.findPath(callback)
            })
            .fail((response) => console.log(response))
    },

    findPath: function(callback){
        var startCoordinates = Grid.getStart();
        var x1 = startCoordinates.x;
        var y1 = startCoordinates.y;
        var finishCoordinates = Grid.getFinish();
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
    }
};

var Path = {

    clear: function(){
        Board.refresh();
    },

    allowed: true,

    draw: function(fieldsCoordinates){
        fieldsCoordinates.forEach(field => $(Path.getField(field.x, field.y)).addClass("result-path"));
        Grid.changed = false;
    },

    move: function(fieldsCoordinates){
        var array = fieldsCoordinates.reverse();
        var length = array.length;

        Grid.changed = false;

        nextStep(0);

        var previousDOMField;

        function nextStep(index){

            if (Path.allowed === false){Path.allowed = true; return}
            if (index >= length){return}

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

    getField: function(xCoordinate, yCoordinate){
        var selector = `.y-${yCoordinate} > .x-${xCoordinate}`;
        var field = $(selector);
        return field.children()[0];

    }
};