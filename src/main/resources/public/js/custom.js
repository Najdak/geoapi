var searchTerm = "",
    urlApi = 'http://localhost:8080/api?q=' + searchTerm ;


$("#searchbutton").click(function() {

    searchTerm = $("#search-box").val();
    console.log( searchTerm );

    $.getJSON(urlApi+searchTerm, function(data) {

    	// var prettyJSON =JSON.stringify(data, undefined, 4);
    	var prettyJSON =syntaxHighlight(data);

        $("#results").children().remove();
        $("#api-url").children().remove();

        $("#api-url").show().append("<h3>API Request:</h3>");
        $("#api-url").append("<div id='request'><a id href="+ urlApi+ searchTerm+">"+urlApi+ searchTerm+"</a></div>");

        $("#results").show().append("<h3>API Response:</h3>");
        $("#results").append("<div id='json'><pre>"+ prettyJSON+"</pre></div>");

    });
});

function syntaxHighlight(json) {
    if (typeof json != 'string') {
        json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
        var cls = 'number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'key';
            } else {
                cls = 'string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'boolean';
        } else if (/null/.test(match)) {
            cls = 'null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}