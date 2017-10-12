var serverUrl='http://localhost:8080';
$(function () {
    $("#send").click(function () {
        //alert(111)
        var url=$("input[name='url']").val();
        var method=$("select[name='method']").find("option:selected").val();
        var contentType=$("select[name='contentType']").find("option:selected").val();
        var data=$("textarea[name='data']").val();
        if (data.replace(/(^\s*)|(\s*$)/g, "").length ==0){
            data='{}';
        }
        //alert(contentType)
        if(contentType=='application/json'){
            try {
                var tmp = JSON.parse(data);
                data=JSON.stringify(tmp);
            } catch (e) {
                alert("json 不正确！")
                return;
            }
        }else if(contentType=='application/x-www-form-urlencoded'){
            try {
                data = JSON.parse(data);
            } catch (e) {
                alert("json 不正确！")
                return;
            }
        }
        //alert(data)
        $.ajax({
            url:serverUrl+url,
            type: method,
            contentType:contentType,
            dataType: 'json',
            data: data,
            success: function (data, textStatus, jqXHR) {
                $("#response").text(formatJson(data))
            },
            error: function(e) {
                $("#response").text(JSON.stringify(e))
            }
        });
    })
})



var formatJson = function(json, options) {
    var reg = null,
        formatted = '',
        pad = 0,
        PADDING = '    '; // one can also use '\t' or a different number of spaces

    // optional settings
    options = options || {};
    // remove newline where '{' or '[' follows ':'
    options.newlineAfterColonIfBeforeBraceOrBracket = (options.newlineAfterColonIfBeforeBraceOrBracket === true) ? true : false;
    // use a space after a colon
    options.spaceAfterColon = (options.spaceAfterColon === false) ? false : true;

    // begin formatting...
    if (typeof json !== 'string') {
        // make sure we start with the JSON as a string
        json = JSON.stringify(json);
    } else {
        // is already a string, so parse and re-stringify in order to remove extra whitespace
        json = JSON.parse(json);
        json = JSON.stringify(json);
    }

    // add newline before and after curly braces
    reg = /([\{\}])/g;
    json = json.replace(reg, '\r\n$1\r\n');

    // add newline before and after square brackets
    reg = /([\[\]])/g;
    json = json.replace(reg, '\r\n$1\r\n');

    // add newline after comma
    reg = /(\,)/g;
    json = json.replace(reg, '$1\r\n');

    // remove multiple newlines
    reg = /(\r\n\r\n)/g;
    json = json.replace(reg, '\r\n');

    // remove newlines before commas
    reg = /\r\n\,/g;
    json = json.replace(reg, ',');

    // optional formatting...
    if (!options.newlineAfterColonIfBeforeBraceOrBracket) {
        reg = /\:\r\n\{/g;
        json = json.replace(reg, ':{');
        reg = /\:\r\n\[/g;
        json = json.replace(reg, ':[');
    }
    if (options.spaceAfterColon) {
        reg = /\:/g;
        json = json.replace(reg, ':');
    }

    $.each(json.split('\r\n'), function(index, node) {
        var i = 0,
            indent = 0,
            padding = '';

        if (node.match(/\{$/) || node.match(/\[$/)) {
            indent = 1;
        } else if (node.match(/\}/) || node.match(/\]/)) {
            if (pad !== 0) {
                pad -= 1;
            }
        } else {
            indent = 0;
        }

        for (i = 0; i < pad; i++) {
            padding += PADDING;
        }

        formatted += padding + node + '\r\n';
        pad += indent;
    });

    return formatted;
};