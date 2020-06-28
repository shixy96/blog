[TOC]

## Docker 基础 -- 镜像

获取、查看、搜索、删除、创建、存出和载入、上传等

> docker image help

### 获取镜像

```
// 下载镜像
docker [image] pull NAME[:TAG]
```

​	其中，NAME 是镜像仓库的名字，TAG 是镜像的标签，如果不显式定义TAG，则默认选择 latest 标签，即NAME:latest，内容不稳定。

```shell
// 获取一个Ubuntu 18.04系统的基础镜像
docker pull ubuntu:18.04
// 获取最新版本的Ubuntu操作系统的镜像
docker pull ubuntu
```

​	使用docker pull命令下载中会获取并输出镜像的各层信息。当不同的镜像包括相同的层时，本地仅存储了层的一份内容，减小了存储空间。

​	如果从非官方的仓库下载，则需要在仓库名称前指定完整的仓库地址。

```sh
docker pull hub.c.163.com/public/ubuntu:18.04
```

pull子命令支持的选项主要包括：

- -a, --all-tags=true|false：是否获取仓库中的所有镜像，默认为否；

-  --disable-content-trust：取消镜像的内容校验，默认为真。



### 查看镜像信息

#### images（列出镜像）

```sh
// 列出镜像
docker images
docker image ls
// help
man docker-images
```

​	其中镜像的ID信息十分重要，它唯一标识了镜像。在使用镜像ID的时候，一般可以使用该ID的前若干个字符组成的可区分串来替代完整的ID。

images子命令主要支持如下选项，用户可以自行进行尝试：

- -a, --all=true|false：列出所有（包括临时文件）镜像文件，默认为否；

- --digests=true|false：列出镜像的数字摘要值，默认为否；

- -f, --filter=[]：过滤列出的镜像，如dangling=true只显示没有被使用的镜像；也可指定带有特定标注的镜像等；

- --format="TEMPLATE"：控制输出格式，如．ID代表ID信息，.Repository代表仓库信息等；

- --no-trunc=true|false：对输出结果中太长的部分是否进行截断，如镜像的ID信息，默认为是；

- -q, --quiet=true|false：仅输出ID信息，默认为否。

#### tag（添加镜像标签）

```
// 添加一个新的myubuntu:latest镜像标签
docker tag ubuntu:latest myubuntu:latest
```

docker tag命令添加的标签实际上起到了类似链接的作用。

#### inspect（查看详细信息）

```sh
// 获取该镜像的详细信息，包括制作者、适应架构、各层的数字摘要等
docker [image] inspect
```

#### history（查看镜像历史）

```sh
docker history ubuntu:18.04
```



### 搜索镜像

```
docker search [option] keyword
```

❑ -f, --filter filter：过滤输出内容；

❑ --format string：格式化输出内容；

❑ --limit int：限制输出结果个数，默认为25个；

❑ --no-trunc：不截断输出结果。



### 删除和清理镜像

#### 删除镜像

docker rmi	/	docker image rm

> docker rmi IMAGE[IMAGE...]，其中IMAGE可以为标签或ID

支持选项包括：

❑ -f, -force：强制删除镜像，即使有容器依赖它；

❑ -no-prune：不要清理未带标签的父镜像。

```sh
docker rmi myubuntu:latest
docker rmi a21c0840213e
```

​	当同一个镜像拥有多个标签的时候，docker rmi命令只是删除了该镜像多个标签中的指定标签而已，并不影响镜像文件。

#### 清理镜像

​	使用Docker一段时间后，系统中可能会遗留一些临时的镜像文件，以及一些没有被使用的镜像，可以通过命令来进行清理。

```sh
docker image prune
```

支持选项包括：

❑ -a, -all：删除所有无用镜像，不光是临时镜像；

❑ -filter filter：只清理符合给定过滤器的镜像；

❑ -f, -force：强制删除镜像，而不进行提示确认。



### 创建镜像

#### 基于已有容器创建

```sh
docker [container] commit [OPTIONS] CONTAINER [REPOSITORY [:TAG]]
```

主要选项包括：

❑ -a, --author=""：作者信息；

❑ -c, --change=[]：提交的时候执行Dockerfile指令，包括CMD|ENTRYPOINT|ENV|EXPOSE|LABEL|ONBUILD|USER|VOLUME|WORKDIR等；

❑ -m, --message=""：提交消息；

❑ -p, --pause=true：提交时暂停容器运行。

使用ID或名称来指定容器

```sh
docker commit -m "add something" -a "shixy" a925cb40b3f0
```

#### 基于本地模板导入

```sh
docker [image] import [OPTIONS] file|URL|-[REPOSITORY [:TAG]]
```

要直接导入一个镜像，可以使用OpenVZ提供的模板来创建，或者用其他已导出的镜像模板来创建。OPENVZ模板的下载地址为http://openvz.org/Download/templates/precreated。

#### 基于Dockerfile创建

Dockerfile是一个文本文件，利用给定的指令描述基于某个父镜像创建新镜像的过程。

```dockerfile
FROM debian:stretch-slim
	LABEL version="1.0" maintainer="docker user ..."
	RUN apt-get update && \
				apt-get install -y python3 && \
				apt-get clean && \
				rm -rf /var/lib/apt/lists/*
```

使用 build 命令创建镜像

> docker [image] build

```sh
docker build -t python:3 .
```



### 存出和载入镜像

#### 存出镜像

​	如果要导出镜像到本地文件，可以使用docker [image] save命令。该命令支持-o、-outputstring参数，导出镜像到指定的文件中。

```sh
docker save -o ubuntu_18.04.tar ubuntu:18.04
```

#### 载入镜像

​	可以使用docker [image] load将导出的tar文件再导入到本地镜像库。支持-i、-input string选项，从指定文件中读入镜像内容。

```
docker load -i ubuntu_18.04
or
docker load < ubuntu_18.04
```



### 上传镜像

​	可以使用docker [image] push命令上传镜像到仓库，默认上传到Docker Hub官方仓库（需要登录）。

> docker [image] push NAME[:TAG] | [REGISTRY_HOST[:REGISTRY_PORT]/]NAME[:TAG]

```sh
// 先添加新的标签user/test:latest，然后用docker [image] push命令上传镜像
docker tag test:latest user/test:latest
docker push user/test:latest
```

