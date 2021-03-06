//@author hcadavid

apimock = (function () {

    var mockdata = [];

    mockdata["johnconnor"] = [{author: "johnconnor", "points": [{"x": 150, "y": 120}, {"x": 215, "y": 115}], "name": "house"},
        {author: "johnconnor", "points": [{"x": 340, "y": 240}, {"x": 15, "y": 215}], "name": "gear"}];
    mockdata["maryweyland"] = [{author: "maryweyland", "points": [{"x": 140, "y": 140}, {"x": 115, "y": 115}], "name": "house2"},
        {author: "maryweyland", "points": [{"x": 140, "y": 140}, {"x": 115, "y": 115}], "name": "gear2"}];

    mockdata["JuanOrtiz"] = [{author: "JuanOrtiz", "points": [{"x": 10, "y": 10}, {"x": 20, "y": 20} , {"x": 30, "y": 30} , {"x": 40, "y": 40}], "name": "PlanosCasa"},
        {author: "JuanOrtiz", "points": [{"x": 11, "y": 11}, {"x": 22, "y": 22}], "name": "Monnalisa"}];
    mockdata["FelipeNiño"] = [{author: "FelipeNiño", "points": [{"x": 80, "y": 80}, {"x": 40, "y": 40}], "name": "PlanosFinca"},
        {author: "FelipeNiño", "points": [{"x": 100, "y": 100}, {"x": 200, "y": 200}, {"x": 300, "y": 300}], "name": "NegraCrespa"}];


    return {
        getBlueprintsByAuthor: function (authname, callback) {
            callback(
                    mockdata[authname]
                    );
        },
        getBlueprintsByNameAndAuthor: function (authname, bpname, callback) {

            callback(
                    mockdata[authname].find(function (e) {
                return e.name === bpname
            })
                    );
        }
    }

})();

/*
 Example of use:
 var fun=function(list){
 console.info(list);
 }
 
 apimock.getBlueprintsByAuthor("johnconnor",fun);
 apimock.getBlueprintsByNameAndAuthor("johnconnor","house",fun);*/