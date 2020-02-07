

- 普通api

| CRUD   | api                              |
| ------ | -------------------------------- |
| Index  | PUT my_index/_doc/1              |
| Create | PUT my_index/_create/1    指定id |
| Create | POST my_index/_doc    自增id     |
| Read   | GET my_index/_doc/1              |
| Update | POST my_index/_update/1          |
| Delete | DELETE my_index/_doc/1           |

​		- Type 名，约定都用 _doc

​		- Create：如果ID已存在，会失败

​		- Index：如果ID不存在，创建新的文档；否则，先删除现有的文档，再创建新的文档，版本会增加

​		- Update：文档必须已经存在，更新只会对相应的字段增量修改，相应字段放在 doc 下

```{
{
	doc: {
		........
	}
}
```



- bulk api 批量操作
  - 支持在一次 api 调用中，对不同的索引进行操作
  - 支持四种类型操作
    - Index
    - Create
    - Update
    - Delete
  - 可以在 URI 中指定index，也可以在请求的 payload 中指定
  - 操作中单条操作失败，并不会影响其他操作
  - 返回结果包括了每一条操作执行的结果

```
POST _bulk
{"index": {"_index": "test", "_id": 1}}
{"field1": "value1"}
```



- mget api 批量读取
  - 批量操作，可以减少网络连接所产生的开销，提高性能

```
GET _mget
{
	"docs": [{
		"_index": "user",
		"_id": 1
	},{
		"_index": "comment",
		"_id": 1
	}]
}
```



- msearch 批量查询



#### 常见错误返回

| 问题         | 原因                 |
| ------------ | -------------------- |
| 无法连接     | 网络故障或者集群挂了 |
| 连接无法关闭 | 网络故障或者节点出错 |
| 429          | 集群过于繁忙         |
| 4xx          | 请求格式体有错       |
| 500          | 集群内部错误         |

