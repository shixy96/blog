[TOC]

### 正则表达式

正则表达式区分大小写 REGEXP BINARY:

```sql
SELECT * FROM XX WHERE FIELD REGEXP BINARY 'Sample|As|[SM]';
```

转义：

```
\\. 匹配.
\\n 匹配换行
\\r 匹配回车
\\\ 匹配\
```

![字符类](./mysql-字符类.png)

![image-20200611235458107](/Users/shixy/Desktop/Code/blog/note/mysql-定位元字符.png)

​	可以在不使用数据库表的情况下用SELECT来测试正则表达式。REGEXP检查总是返回0（没有匹配）或1（匹配）。可以用带文字串的REGEXP来测试表达式，并试验它们。相应的语法如下：

```sql
SELECT 'hello' REGEXP '[0-9]';
```



### 数据处理函数

省略 FROM 子句测试表达式

```sql
SELECT Trim('abc')；
```

#### 1. 文本处理函数

拼接字段 Concat()，别名 AS

```sql
SELECT Concat(name, '(', address, ')') AS title FROM user;
```

去除空格 RTrim(), LTrim(), Trim()

```sql
SELECT Trim(name) FROM user;
```

转换大写 Upper()

```sql
SELECT Upper(name) FROM user;
```

![image-20200612013827554](/Users/shixy/Desktop/Code/blog/note/mysql-文本处理函数.png)

#### 2. 日期和时间处理函数

![image-20200612014226604](/Users/shixy/Desktop/Code/blog/note/mysql-日期函数.png)

日期格式为 yyyy-mm-dd

```sql
SELECT name FROM orders WHERE Year(order_date) = 2020 AND Month(order_date) = 9;
```

#### 3. 数值处理函数

![image-20200612015029468](/Users/shixy/Desktop/Code/blog/note/mysql-数值处理函数.png)



### 数据汇总函数

![image-20200612015244827](/Users/shixy/Desktop/Code/blog/note/mysql-聚集函数.png)

*AVG、MAX、MIN、SUM函数忽略列值为$NULL的行*

COUNT(*) 对表中行的数目进行计数，包含NULL值；COUNT(column) 对特定列中有值的行计数，忽略NULL值。

DISTINCT 必须使用列名

```sql
SELECT AVG(DISTINCT price) AS avg_price FROM product;
```



### 数据分组

**GROUP**: 分组允许把数据分为多个逻辑组，以便能**对每个组**进行聚集计算。

```sql
SELECT vender_id, COUNT(*) AS num_prods FROM products GROUP BY vender_id;
```

- GROUP BY 子句可以包含任意数目的列
- 除聚集计算语句外，SELECT语句中的每个列都必须在GROUP BY子句中给出。
- 如果分组列中具有NULL值，则NULL将作为一个分组返回。如果列中有多行NULL值，它们将分为一组。
- WITH ROLLUP 返回分组汇总之后的值

```sql
SELECT coalesce(name, '总金额'), SUM(money) AS money FROM products GROUP BY name WITH ROLLUP;
```

**HAVING** 支持所有 WHERE 操作符，规定包括哪些分组，排除哪些分组

```sql
SELECT cust_id, COUNT(*) AS orders 
FROM orders 
WHERE prod_price >= 10
GROUP BY cust_id 
HAVING COUNT(*) >= 2
ORDER BY orders
```

​	WHERE 在数据分组前进行过滤，HAVING 在数据分组后进行过滤。



### 子查询

嵌套在其他查询中的查询，不要嵌套太多子查询

1. 子查询作为条件

```sql
SELECT cust_name 
FROM customers
WHERE cust_id IN (SELECT cust_id 
                  FROM orders
                 	WHERE order_num IN (SELECT order_num
                                      FROM orderitems
                                      WHERE prod_id = 'APPLE'));
```

2. 子查询作为计算字段

```sql
SELECT cust_name, 
			 cust_state, 
			 (SELECT COUNT(*) FROM orders WHERE orders.cust_id = customers.cust_id) AS orders
FROM customers
ORDER BY cust_name;
```



### 联结 JOIN

