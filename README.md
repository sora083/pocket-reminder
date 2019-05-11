### APIs
##### Get token(Please access via browser)
```
http://localhost:8080/init
```

http://localhost:8080/get?token=${access_token}

### Deploy to heroku
```bash
heroku login
heroku create
git push heroku master
```

#### access via browser
```
https://pocket-reminder.herokuapp.com/init
```

### 調べる
* auth0

### 参考
* [Pocket API: Documentation Overview](https://getpocket.com/developer/docs/overview)
* [PocketのAPIをNodejsから扱う方法](https://blog.leko.jp/post/how-to-treat-pocket-api-with-js/)
* [20180716 Node.jsのhttpsモジュールを用いた通信処理をPromiseで書き直して解読してみた](https://ky-yk-d.hatenablog.com/entry/2018/07/16/011748)