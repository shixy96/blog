Search api

1. URI Search
   - 在URL中使用查询参数
2. Request Body Search
   - DSL（Domain Specific Language）



| 语法                   | 范围                |
| ---------------------- | ------------------- |
| /_search               | 集群上所有的索引    |
| /index1/_search        | index1              |
| /index1,index2/_search | Index 和 index2     |
| /index*/_search        | 以 index 开头的索引 |



- #### URI查询

  curl -XGET

  - Term query：A or B

  http://localhost:9200/index/_search?q=costomer_first_name:(Eddie Bi)

  - Phrase query：A and B

  http://localhost:9200/index/_search?q=costomer_first_name:"Eddie Bi" ~2

  搜索 first name 为 Eddie 的客户

  

- #### Requset Body

  curl -XGET/POST

​	   "http://localhost:9200/index/_search"

​		-H 'Content-Type: application/json'

​		-d 

```json
{
	"from": 10,
	"size": 20,
	"sort": [{"order_date": "desc"}],
	"_source": ["order_date", "category.keyword", "name*"], //source 过滤，支持通配符

  "query": {
    "match": {
      "comment": "L A" // OR
    },
    "match": {
      "comment": {
        "query": "L A",
        "operator": "AND"
      }
    },
    "match_phrase": {
      "comment": {
        "query": "L A",
        "slop": 1 // 中间可以有一个其他term进入
      }
    },
		"match_all": {},
    
    "simple_query_string": { // 不支持 AND OR NOT，替换成 + ｜ —
      "query": "Y -H",
      "fields": ["name"],
      "default_operator": "AND"
    },
    "query_string": {
    	"default_field": "name", //df 指定字段
    	"query": "Ruan And Yiming" // and
  	},
  	"query_string": {
    	"fields": ["name", "about"],
    	"query": "(Ruan And Yiming) OR (J AND B)"
  	}
	},

  "script_fields": {
    "new_field": {
      "script": {
        "lang": "painless",
        "source": "doc['order_date'].value+'_hello'"
      }
    }
  }
}
```

```
//script_fields response
{
  hits: {
		"hits": [
      {
        "fields": {
          "new_field": ["2019..._hello"]
        }
      }
    ]
  }
}
```



返回所有文档



- Response
  - took：查询话费的时间
  - hits.total：符合条件的总文档数
  - hits.hits：结果集，默认前十个文档



