# Query DSL（ Domain Specific Language ）

 Elasticsearch 提供了一个完整的基于 `JSON `的 `Query DSL` 来定义查询，由两种类型的子句组成 ：

####  **Leaf query clauses** 

特定字段查找特定值（match, term, rang ...）

#### **Compound query clauses** 

复合查询子句包装其他叶查询或复合查询，用于以逻辑方式组合多个查询(bool、dis max...)，或更改它们的行为 (constant_score)。 

## match

| paramter                            | type                                | default value   | description                                    |
| :---------------------------------- | :---------------------------------- | --------------- | :--------------------------------------------- |
| query (require)                     | text, number, boolean value or date |                 |                                                |
| analyzer                            | string                              | search analyzer |                                                |
| auto_generate_synonyms_phrase_query | boolean                             | true            | , 同义词查询                                   |
| fuzziness(模糊性)                   | string                              | AUTO:3,6        | 允许匹配的最大编辑距离*[1]*                    |
| max_expansions                      | integer                             | 50              | 查询扩展最大项数                               |
| prefix_length                       | integer                             | 0               | 模糊匹配的起始字符数                           |
| transpositions                      | boolean                             | true            | 模糊匹配的编辑包括两个相邻字符的交换(ab -> ba) |
| fuzzy_rewrite                       | string                              |                 | 重写 query 的方法                              |
| lenient                             | boolean                             | false           | 忽略数据类型不匹配导致的异常                   |
| operator                            | string (OR, AND)                    | OR              | 链接方式                                       |
| minimum_should_match                | string                              |                 | 必须与要返回的文档匹配的子句的最小数目 [2]。   |
| zero_terms_query                    | string (none, all)                  | none            |                                                |

1： [要使一个字符串与另一个字符串相等，需要改变字符的数量](http://www.elastic.co/guide/en/elasticsearch/reference/7.5/common-options.html#fuzziness)

2:  [minimum_should_match parameter](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/query-dsl-minimum-should-match.html)  

REFERENCE:

 https://www.elastic.co/guide/en/elasticsearch/reference/7.5/query-dsl-match-query.html 



## term

| paramter         | type   | default value | description                          |
| ---------------- | ------ | ------------- | ------------------------------------ |
| value（require） | string |               | 与字段值完全匹配，包括空格和大小写。 |
| boost            | float  | 1.0           |                                      |

term 不适用于检索文本，用 match 检索文本，match 会在搜索前分析给定的query，返回列表，在搜索后查找所有有列表中token的文档。