# XSS

* 感谢 [美团技术团队](https://tech.meituan.com/fe_security.html)，摘要记录于此，方便以后翻看回忆，仅用于个人学习记录。

## What
[Cross-Site Scripting（跨站脚本攻击）简称 XSS](https://zh.wikipedia.org/wiki/%E8%B7%A8%E7%B6%B2%E7%AB%99%E6%8C%87%E4%BB%A4%E7%A2%BC)，是一种代码注入攻击。攻击者通过在目标网站上注入恶意脚本，使之在用户的浏览器上运行。利用这些恶意脚本，攻击者可获取用户的敏感信息如 Cookie、SessionID 等，进而危害数据安全。

- 本质：恶意代码未经过滤，与网站正常的代码混在一起；浏览器无法分辨哪些脚本是可信的，导致恶意脚本被执行，直接获取用户的信息，或者利用这些信息冒充用户向网站发起攻击者定义的请求。

不仅仅是业务上的“用户的 [UGC](https://zh.wikipedia.org/wiki/%E7%94%A8%E6%88%B7%E7%94%9F%E6%88%90%E5%86%85%E5%AE%B9) 内容”可以进行注入，包括 URL 上的参数等都可以是攻击的来源。在处理输入时，以下内容都`不可信`：

- 来自用户的 UGC 信息
- 来自第三方的链接
- URL 参数
- POST 参数
- Referer （可能来自不可信的来源）
- Cookie （可能来自其他子域注入）

Tips： [用户生成内容（英语：User-generated content，缩写：UGC）](https://zh.wikipedia.org/wiki/%E7%94%A8%E6%88%B7%E7%94%9F%E6%88%90%E5%86%85%E5%AE%B9)
## Classification

根据攻击的来源，XSS 攻击可分为存储型、反射型和 DOM 型三种。

### 存储型
存储型 XSS 的攻击步骤：

1. 攻击者将恶意代码提交到目标网站的数据库中。
2. 用户打开目标网站时，网站服务端将恶意代码从数据库取出，拼接在 HTML 中返回给浏览器。
3. 用户浏览器接收到响应后解析执行，混在其中的恶意代码也被执行。
4. 恶意代码窃取用户数据并发送到攻击者的网站，或者冒充用户的行为，调用目标网站接口执行攻击者指定的操作。

这种攻击常见于带有用户保存数据的网站功能，如论坛发帖、商品评论、用户私信等。

### 反射型
反射型 XSS 的攻击步骤：

1. 攻击者构造出特殊的 URL，其中包含恶意代码。
2. 用户打开带有恶意代码的 URL 时，网站服务端将恶意代码从 URL 中取出，拼接在 HTML 中返回给浏览器。
3. 用户浏览器接收到响应后解析执行，混在其中的恶意代码也被执行。
4. 恶意代码窃取用户数据并发送到攻击者的网站，或者冒充用户的行为，调用目标网站接口执行攻击者指定的操作。
5. 反射型 XSS 跟存储型 XSS 的区别是：存储型 XSS 的恶意代码存在数据库里，反射型 XSS 的恶意代码存在 URL 里。

反射型 XSS 漏洞常见于通过 URL 传递参数的功能，如网站搜索、跳转等。

由于需要用户主动打开恶意的 URL 才能生效，攻击者往往会结合多种手段诱导用户点击。

POST 的内容也可以触发反射型 XSS，只不过其触发条件比较苛刻（需要构造表单提交页面，并引导用户点击），所以非常少见。

### DOM 型

DOM 型 XSS 的攻击步骤：

1. 攻击者构造出特殊的 URL，其中包含恶意代码。
2. 用户打开带有恶意代码的 URL。
3. 用户浏览器接收到响应后解析执行，前端 JavaScript 取出 URL 中的恶意代码并执行。
4. 恶意代码窃取用户数据并发送到攻击者的网站，或者冒充用户的行为，调用目标网站接口执行攻击者指定的操作。

DOM 型 XSS 跟前两种 XSS 的区别：DOM 型 XSS 攻击中，取出和执行恶意代码由浏览器端完成，属于前端 JavaScript 自身的安全漏洞，而其他两种 XSS 都属于服务端的安全漏洞。

## Methods
* 在 `HTML` 中内嵌的文本中，恶意内容以 `script` 标签形成注入。
<br>（在部分情况下，由于输入的限制，注入的恶意脚本比较短。但可以通过引入外部的脚本，并由浏览器执行，来完成比较复杂的攻击策略。）
* 在内联的 `JavaScript` 中，拼接的数据突破了原本的限制（字符串，变量，方法名等）。
* 在标签属性中，恶意内容包含引号，从而突破属性值的限制，注入其他属性或者标签。
* 在标签的 `href`、`src` 等属性中，包含 `javascript`: 等可执行代码。
* 在 `onload`、`onerror`、`onclick` 等事件中，注入不受控制代码。
* 在 `style` 属性和标签中，包含类似 `background-image:url("javascript:...");` 的代码（新版本浏览器已经可以防范）。
* 在 `style` 属性和标签中，包含类似 `expression(...)` 的 `CSS` 表达式代码（新版本浏览器已经可以防范）。

总之，如果开发者没有将用户输入的文本进行合适的过滤，就贸然插入到 HTML 中，这很容易造成注入漏洞。攻击者可以利用漏洞，构造出恶意的代码指令，进而利用恶意代码危害数据安全。

eg( DOM 型 ): <br>
URL like xxx.xxx.com/login?return_to=http%3A%2F%2Fxxx.xxx.com%2Fxxx%2Fxxxx
```html
<button onclick="login()">
    ...
</button>
```
```javascript
function login() {
    ...
    let return_to = /[?&]return_to=([^&#]*)/.exec(location.search)[1];
    if(return_to) {
        location.href = return_to;
    } else {
        ...
    }
}
```
如果代码是这样的，恭喜你，中招了 (hhh)
## Prevention

### 输入过滤
并不完全可靠。
### 防止 HTML 中出现注入。（存储、反射 -> 服务端取出恶意代码后，插入到响应 HTML 里）
- 改成纯前端渲染，把代码和数据分隔开。
- 对 HTML 做充分转义。
### 防止 JavaScript 执行时，执行恶意代码。
( <br>
....................................................W....................T....................F..................................................
    <br><br>网站前端 `JavaScript` 代码本身`不够严谨`，把不可信的数据当作代码执行了 <br><br>
    ....................................................W....................T....................F..................................................
<br>)

### else
#### Content Security Policy
严格的 CSP 在 XSS 的防范中可以起到以下的作用：

- 禁止加载外域代码，防止复杂的攻击逻辑。
- 禁止外域提交，网站被攻击后，用户的数据不会泄露到外域。
- 禁止内联脚本执行（规则较严格，目前发现 GitHub 使用）。
- 禁止未授权的脚本执行（新特性，Google Map 移动版在使用）。
- 合理使用上报可以及时发现 XSS，利于尽快修复问题
#### 输入内容长度控制
对于不受信任的输入，都应该限定一个合理的长度。虽然无法完全防止 XSS 发生，但可以增加 XSS 攻击的难度。

#### HTTP-only Cookie:
 禁止 JavaScript 读取某些敏感 Cookie，攻击者完成 XSS 注入后也无法窃取此 Cookie。

 ...

 如果想研究详细内容，或者想继续读小明同学的 xss 遭遇，请阅读原文 [前端安全系列（一）：如何防止XSS攻击？](https://tech.meituan.com/fe_security.html)。