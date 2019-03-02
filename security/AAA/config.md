# Config

## VMWare 虚拟机

1. [操作系统]( https://msdn.itellyou.cn/ )：

* `ACS Server` &  `Web Server`：Windows Server 2003 R2, Standard x64 Edition with SP2 - Disc 1 - VL (Simplified Chinese)；

* `Outside PC`: Windows XP Professional with Service Pack 2- VL (Simplified Chinese)；

* `ASA-8.42`: Virtual Machines 文件夹中附件；

<hr>
2. 软件及服务资源

* `ACS Server` 安装 ACS_40_SW（在 software for VM ACS Server 文件夹下）

* `ACS Server` 和 `Web Server` 的网站资源（html 和 css）在对应文件夹的 web source 文件夹下，将 source 文件夹复制到对应服务器的 `C:\Inetpub\wwwroot\` 路径下即可完成服务资源配置。

* `Outside PC` 安装 vpnclient-win-msi-5.0.07.0410-k9（在 software for VM Outside PC 文件夹下）

<hr>
## 主机
在`真实主机`（eg. win10）上安装 Named Pipe Tcp Proxy（安装包在 software for Real Host PC 文件夹下），
安装 scrt852-x64（或者直接开启telnet功能也可以）
