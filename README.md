
1.简介
   可基于内存的、也可以持久化的日志型的 key-value形式的数据库

2.特点
  读写性能：读110000/s  写 80000/s
  value支持的类型：String hash list set zset
  所有的操作是原子操作
  支持数据库的备份，支持主从备份（master-slave）
  支持订阅发布、通知以及key过期等特性

3.redis服务器命令
  启动服务命令  ./redis-server &
  启动客户端命令  ./redis-cli -h 127.0.0.1 -p 6379 -a password (远程启动)
                  ping 检测是否启动 如果启动返回的是pong
  查看配置的命令 config get *   /info

   保存当前数据库的数据到磁盘 save
   恢复数据只需将备份文件（dump.rdb）放到安装目录启动服务即可（config get dir）
   查看所有客户端 client list
   查看数据库key的数量  dbsize
   删除所有数据库的key  flushall



4.数据类型的基本命令

  String   存：set key value  取：get key  一个键最大存储512M
  hash     存：hmset  key  name chen age 27   取 hgetall key
               (取一组数据)  hget key filed   删除：hdel key field2
  list     存：lpush key value    取：lrange key index0   indexN
  set      存: sadd key member  取：smembers key (不允许重复)
  zset     存：zadd key score member  取:zrangebyscore  key 100 10000      或 zrange key 0 1000

5.keys操作命令
  del key  删除操作
  exists key 是否存在
  expire key n  设置超时时间（s）
  persist key  移除key的超时时间
  ttl key  查看key的剩余超时时间 （-1代表永久存在）
  rename  key newKey   修改key的名称
  type key 返回可以的类型


 6. 发布订阅模式
   订阅：subscribe channel
   发布：publish channel "some thing"

 7. 事务
   开始事务 命令入队 执行事务
   multi    command   exec

8.安全
  查看是否设置密码： config get requirepass
  设置密码: config set requirepass "password"
  登录验证: auth password

10.redis分区


11.java使用redis的相关操作

















