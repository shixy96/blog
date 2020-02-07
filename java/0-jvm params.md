| params                          | description                                  |
| ------------------------------- | -------------------------------------------- |
| -Xmx20m                         | 堆最大内存20MB                               |
| -Xms20m                         | 堆最小内存20MB                               |
| -XX:+HeapDumpOnOutOfMemoryError | 出现内存溢出异常时Dump出当前的内存堆转储快照 |
| -Xoss                           | 设置本地方法栈大小                           |
| -Xss128k                        | 设置虚拟机栈大小为128KB                      |
| -XX:MaxPermSize                 | 设置最大永久代容量                           |
| -XX:PermSize                    | 设置永久代大小                               |
| -XX:MaxDirectMemorySize         | 指定直接内存最大容量，默认与堆最大内存一样   |
| -Xnoclassgc                     | 是否对类进行回收                             |

Permanent Generation 永久代