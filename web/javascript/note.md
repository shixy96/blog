#### promise

[常见错误]([https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Guide/Using_promises#%E5%B8%B8%E8%A7%81%E9%94%99%E8%AF%AF](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Guide/Using_promises#常见错误))

```js
doSomething()
.then(function(result) {
  return doSomethingElse(result);
})
.then(newResult => doThirdThing(newResult))
.then(() => doFourthThing());
.catch(error => console.log(error));
```

[异步函数 - 提高 Promise 的易用性](<https://developers.google.com/web/fundamentals/primers/async-functions>)

[From JavaScript Promises to Async/Await: why bother?](<https://blog.pusher.com/promises-async-await/>)

