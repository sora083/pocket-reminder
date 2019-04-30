(function(global, undefined) {
    "use strict";

    var Pocket = (function() {
        var https = require("https");

        function Pocket(opt) {
            this.consumer_key = opt.consumer_key;
            this.access_token = opt.access_token;
        }

        Pocket._API_END_POINT = "getpocket.com";
        Pocket._VERSION = "v3";
        Pocket._ALLOWED_ACTIONS = [
            "favorite",
            "unfavorite",
            "add",
            "readd",
            "archive",
            "delete",
            "tags_add",
            "tags_remove",
            "tags_replace",
            "tags_clear",
            "tags_rename"
        ];

        function serialize(param) {
            var ret = [], p;
            for(p in param) {
                if(param.hasOwnProperty(p)) {
                    if ( typeof param[p] === "object" ) ret.push(p + "=" + escape(JSON.stringify(param[p])));
                    else ret.push(p + "=" + escape(param[p]));
                }
            }
            return ret.join("&");
        }

        Pocket.prototype = {
            request: function(path, param, callback) {
                var options;

                param.consumer_key = this.consumer_key;
                param.access_token = this.access_token;
                param = serialize(param);

                console.log("options: " + JSON.stringify(options));
                console.log("param: " + param);
                console.log("callback: " + callback);

                options = {
                    host: Pocket._API_END_POINT,
                    path: "/" + Pocket._VERSION + path + "?" + param,
                    method: "GET"
                };

                https.get(options, function(req) {
                    var response = "";
                    req.on("data", function(res) {
                        response += res.toString();
                    });
                    req.on("end", function(res) {
                        //console.log("response: " + response)
                        var json = JSON.parse(response);
                        //console.log(json)
                        //callback.call(null, json);
                    });
                });
            },
            get: function(param, callback) {
                this.request("/get", param, callback);
            },
            // add: function(param, callback) {
            //     this.request("/add", param, callback);
            // },
            // modify: function(param, callback) {
            //     // 各アクションにtimeを追加
            //     for ( var i = 0, l = param.actions.length; i < l; i++ ) {
            //         var p = param.actions[i];
            //         if ( Pocket._ALLOWED_ACTIONS.indexOf(p.action) === -1 ) {
            //             throw new TypeError("Action '" + p.action + "' is not allowed.");
            //         }
            //         if ( typeof p.time === "undefined" ) {
            //             p.time = ~~(new Date() / 1000);
            //         }
            //     }
            //     this.request("/send", param, callback);
            // }
        };

        return Pocket;
    }());

    module.exports = Pocket;
}(this));