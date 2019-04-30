var url = require('url');
var https = require('https');

var params = {
    consumer_key: "XXX",
    access_token: "XXX",
    sort: "newest",
    count: 10
};

var urlObj = {
    protocol: 'https',
    hostname: 'getpocket.com',
    search: `consumer_key=${params.consumer_key}&access_token=${params.access_token}&sort=${params.sort}&count=${params.count}`,
    pathname: '/v3/get' };


var urlString = url.format(urlObj);
console.log(urlString);

var req = https.request(urlString, (res) => {
    var json = "";
    res.on('data', (chunk) => {
        json = JSON.parse(chunk);
    });
    res.on('end', () => {
        console.log(json)
        //return json;
    });
})

req.on('error', (e) => {
  console.error(`エラーが出ました： ${e.message}`);
});

req.end();