var Pocket = require("./pocket-client.js"),
    pocket = new Pocket({
        consumer_key: "",
        access_token: ""
    });
// Pocketから取得
var opt = {
    sort: "newest",
    count: 10
};
//console.log("opt: " + opt);
pocket.get(opt, function(json) {
    // 記事の配列
});

// // Pocketされている記事の情報変更
// var opt = {
//     actions: [
//         {
//             "action": "favorite",
//             "item_id": 99999999
//         }
//     ]
// };
// pocket.modify(opt, function(json) {
//     // 送信したアクションを行った結果
// });

// // Pocketに記事を追加
// var opt = {
//     url: "http://leko.jp",
//     title: "うぇぶえっぐ",
//     tags: "web,egg"
// };
// pocket.add(opt, function(json) {
//     // 記事をPocketに追加した結果
// });