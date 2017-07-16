### 1. 简介
   * 可基于内存的、也可以持久化的日志型的、key-value形式的非关系型数据库
### 2. 由来
   * 是意大利的一家做实时统计系统的公司在使用mysql时，创始人Salvatore Sanfilippo发现mysql的性能非常低下。于2009年开发完成的redis数据库，并将该数据库开源。VMware公司从2010年赞助redis的开发。
### 3.特点
*   读写性能：读110000/s  写 80000/s
*   value支持的类型：string 、hash 、list 、set、 zset  、BitMap、 HyperLogLog 、 geospatial
*   所有的操作是原子操作
*   支持数据库的备份，支持主从备份（master-slave）
*   支持订阅发布、通知以及key过期等特性
### 4.相同产品对比

*   名称	 类型	数据存储选项	  查询类型	    附加功能
*   Redis	使用内存存储（in-memory） 的非关系数据库。字符串、列表、集合、散列表、有序集合。每种数据类型都有自己的专属命令;另外还有批量操作（bulk operation）和不完全（partial）的事务支持;发布与订阅， 主从复制（master/slave replication）， 持久化， 脚本（存储过程，stored procedure）
*   memcached	使用内存存储的键值缓存;键值之间的映射; 创建命令、读取命令、更新命令、删除命令以及其他几个命令;为提升性能而设的多线程服务器
*   MySQL	关系数据库;每个数据库可以包含多个表，每个表可以包含多个行； 可以处理多个表的视图（view）； 支持空间（spatial）和第三方扩展;SELECT 、 INSERT 、 UPDATE 、 DELETE 、函数、存储过程;支持ACID性质（需要使用InnoDB）， 主从复制和主主复制 （master/master replication）
*   PostgreSQL	关系数据库 ;每个数据库可以包含多个表， 每个表可以包含多个行； 可以处理多个表的视图；  支持空间和第三方扩展；支持可定制类型	SELECT 、 INSERT 、 UPDATE 、 DELETE 、内置函数、自定义的存储过程	支持ACID性质，主从复制， 由第三方支持的多主复制 （multi-master replication）
*   MongoDB	使用硬盘存储（on-disk）的非关系文档存储;每个数据库可以包含多个表， 每个表可以包含多个无schema （schema-less）的BSON文档;创建命令、读取命令、更新命令、删除命令、条件查询命令，等等;	支持map-reduce操作，主从复制，分片， 空间索引（spatial index）

### 5.redis服务器命令
*   启动服务命令  ./redis-server &
*   启动客户端命令  ./redis-cli -h 127.0.0.1 -p 6379 -a password (远程启动)
*   ping 检测是否启动 如果启动返回的是pong
*   查看配置的命令 config get *   或者 info

*    保存当前数据库的数据到磁盘 save
*    恢复数据只需将备份文件（dump.rdb）放到安装目录启动服务即可（config get dir）
*    查看所有客户端 client list
*    查看数据库key的数量  dbsize
*    删除所有数据库的key  flushall



### 6.数据类型的基本命令

*   String   存：set key value  取：get key  一个键最大存储512M
*   hash     存：hmset  key  name chen age 27   取 hgetall key
             (取一组数据)  hget key filed   删除：hdel key field2
*   list     存：lpush key value    取：lrange key index0   indexN
*   set      存: sadd key member  取：smembers key (不允许重复)
*   zset     存：zadd key score member  取:zrangebyscore  key 100 10000      或 zrange key 0 1000

### 7.keys操作命令
*   del key  删除操作
*   exists key 是否存在
*   expire key n  设置超时时间（s）
*   persist key  移除key的超时时间
*   ttl key  查看key的剩余超时时间 （-1代表永久存在）
*   rename  key newKey   修改key的名称
*   type key 返回可以的类型


###  8. 发布订阅模式
*    订阅：subscribe channel
*    发布：publish channel "some thing"

###  9. 事务
*    开始事务 命令入队 执行事务
*    multi    command   exec

### 10.安全
*   查看是否设置密码： config get requirepass
*   设置密码: config set requirepass "password"
*   登录验证: auth password

### 11.redis分区


### 12.java使用redis的相关操作

















