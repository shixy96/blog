### 线程池

- 创建/销毁线程需要消耗系统资源，线程池可以**复用已创建的线程**。

- **控制并发的数量**。并发数量过多，可能会导致资源消耗过多，从而造成服务器崩溃。（主要原因）

- **可以对线程做统一管理**。

### [原理](https://redspider.gitbook.io/concurrent/di-san-pian-jdk-gong-ju-pian/12#12-2-1-threadpoolexecutor-ti-gong-de-gou-zao-fang-fa)

Java中的线程池顶层接口是`Executor`接口，`ThreadPoolExecutor`是这个接口的实现类。

<img src="./java-线程池.png" style="width:600px">

