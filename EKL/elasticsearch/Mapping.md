## Mapping

- mapping 类似数据库中的 schema 的定义
  - 定义索引中的字段名称
  - 定义字段的数据类型
  - 字段、倒排索引的相关配置
- Mapping 会把 json 文档映射成 Lucene 所需要的扁平格式
- 一个 Mapping 属于一个索引的 Type
  - 每个文档都属于一个 Type
  - 一个 Type 有一个 Mapping 定义
  - 7.0开始，不需要在 Mapping 中指定 type 信息



### 字段数据类型

- 简单类型
  - Text / Keyword
  - Date
  - Integer / Floating
  - Boolean
  - IPv4 & IPv6
- 复杂类型
  - 对象类型 / 嵌套类型
- 特殊类型
  - geo_point & geo_shape / percolator



#### Dynamic Mapping

在写入文档时，如果索引不存在，会自动创建索引

| JSON类型 | Elasticsearch 类型                                           |
| -------- | ------------------------------------------------------------ |
| 字符串   | 匹配日期格式，设置成Date；设置为Text，并增加 keyword 子字段； |
| 布尔值   | boolean                                                      |
| 浮点数   | float                                                        |
| 整数     | long                                                         |
| 对象     | Object                                                       |
| 数组     | 由第一个非空数值决定                                         |
| 空值     | 忽略                                                         |

```
PUT mapping_test/_doc/1
{
	"uid": "123",      // text
	"isVip": false,    // boolean
	"isAdmin": "true", // text
	"age": 19,         // long
	"heigh": 180       // long
}
```



#### 能否更改 Mapping 的字段类型

- 新增加字段
  - Dynamic 设为 true 时，一旦有新增字段的文档写入，Mapping 也同时被更新
  - Dynamic 设为 false 时，Mapping 不会被更新，新增字段的数据无法被索引，但是信息会出现在 _source 中
  - Dynamic 设置成 Strict，文档写入失败

|                | true | false | strict |
| -------------- | ---- | ----- | ------ |
| 文档可被索引   | Y    | Y     | N      |
| 字段可索引     | Y    | N     | N      |
| Mapping 被更新 | Y    | N     | N      |

```
PUT movies
{
	"mapping": {
		"_doc": {
			"dynamic": "false"
		}
	}
}

PUT movies/_mapping
{
	"dynamic": false
}
GET movies/_mapping
```

- 对已有字段，一旦已经有数据写入，就不在支持修改字段定义
  - Lucene 实现的倒排索引，一旦生成后，就不允许修改，如果修改会导致已被索引的数据无法被搜索