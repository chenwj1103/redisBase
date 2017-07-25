

### 基于redis-3.2.9版本的默认配置文件
    redis服务器是8核 4G内存

1. 比较key值长和短的内存区别.

```
存500万数据的情况（key值较短）

127.0.0.1:6379> dbsize
(integer) 4974516
127.0.0.1:6379> scan 0 COUNT 20
1) "131072"
2)  1) "listKey:59486170"
    2) "listKey:28791958"
    3) "setKey:11354070"
    4) "mapKey:54303736"
    5) "zaddKey:97996283"
    6) "listKey:49883561"
    7) "mapKey:18328012"
    8) "num:87615610"
    9) "setKey:66520022"
   10) "mapKey:82866653"
   11) "listKey:78369193"
   12) "zaddKey:8000851"
   13) "zaddKey:76565413"
   14) "listKey:86673104"
   15) "zaddKey:86136620"
   16) "mapKey:59828160"
   17) "zaddKey:1337731"
   18) "listKey:13973556"
   19) "num:41374425"
   20) "mapKey:42556618"
127.0.0.1:6379> info Memory
# Memory
used_memory:558953888
used_memory_human:533.06M
used_memory_rss:649760768
used_memory_rss_human:619.66M
used_memory_peak:2986123072
used_memory_peak_human:2.78G
total_system_memory:4143886336
total_system_memory_human:3.86G
used_memory_lua:37888
used_memory_lua_human:37.00K
maxmemory:0
maxmemory_human:0B
maxmemory_policy:noeviction
mem_fragmentation_ratio:1.16
mem_allocator:jemalloc-4.0.3




存500万数据的情况（key值较长）
key的长度由原来的加上前缀'testKeyLength++++++++++++++++++++++++++++++++++'

127.0.0.1:6379> dbsize
(integer) 4980115
127.0.0.1:6379> scan 0 COUNT 20
1) "3801088"
2)  1) "testKeyLength++++++++++++++++++++++++++++++++++num:79637262"
    2) "testKeyLength++++++++++++++++++++++++++++++++++num:35885178"
    3) "testKeyLength++++++++++++++++++++++++++++++++++setKey:96883097"
    4) "testKeyLength++++++++++++++++++++++++++++++++++zaddKey:46220067"
    5) "testKeyLength++++++++++++++++++++++++++++++++++mapKey:28878542"
    6) "testKeyLength++++++++++++++++++++++++++++++++++setKey:31440872"
    7) "testKeyLength++++++++++++++++++++++++++++++++++mapKey:28120548"
    8) "testKeyLength++++++++++++++++++++++++++++++++++num:89373286"
    9) "testKeyLength++++++++++++++++++++++++++++++++++setKey:83873841"
   10) "testKeyLength++++++++++++++++++++++++++++++++++setKey:91661262"
   11) "testKeyLength++++++++++++++++++++++++++++++++++zaddKey:33829475"
   12) "testKeyLength++++++++++++++++++++++++++++++++++zaddKey:59643215"
   13) "testKeyLength++++++++++++++++++++++++++++++++++listKey:23629033"
   14) "testKeyLength++++++++++++++++++++++++++++++++++mapKey:96428506"
   15) "testKeyLength++++++++++++++++++++++++++++++++++listKey:69827524"
   16) "testKeyLength++++++++++++++++++++++++++++++++++zaddKey:68129964"
   17) "testKeyLength++++++++++++++++++++++++++++++++++mapKey:32472278"
   18) "testKeyLength++++++++++++++++++++++++++++++++++num:32012329"
   19) "testKeyLength++++++++++++++++++++++++++++++++++num:68696622"
   20) "testKeyLength++++++++++++++++++++++++++++++++++setKey:3665294"
127.0.0.1:6379> info MEMORY
# Memory
used_memory:831832512
used_memory_human:793.30M
used_memory_rss:857923584
used_memory_rss_human:818.18M
used_memory_peak:2986123072
used_memory_peak_human:2.78G
total_system_memory:4143886336
total_system_memory_human:3.86G
used_memory_lua:37888
used_memory_lua_human:37.00K
maxmemory:0
maxmemory_human:0B
maxmemory_policy:noeviction
mem_fragmentation_ratio:1.03
mem_allocator:jemalloc-4.0.3

```

2.高级一点的数据类型如set, sorted set,hash,他们在数据大小不同的情况下使用的存储结构是不同的.

```
#HASH的内存优化实例

1)条件：
   hash-max-ziplist-entries  > 512 (520)
   hash-max-ziplist-value >64(70)

   dbsize:  49998

   key :mapKey:688631424
   filed:test key length ,need length is gt 64 byte.it is too large ll688631876
   value:test key length ,need length is gt 64 byte.it is too large ll688631876

   127.0.0.1:6379> hlen mapKey:688631424
   (integer) 520


   used_memory_human:5.81G

   object encoding :hashtable


2) 条件：
   hash-max-ziplist-entries  < 512 (500)
   hash-max-ziplist-value >64(70)

   dbsize:  50000

   key :mapKey:887468809
   filed:test key length ,need length is gt 64 byte.it is too large ll887469058
   value:test key length ,need length is gt 64 byte.it is too large ll887469058

   127.0.0.1:6379> hlen mapKey:887468809
   (integer) 500

   used_memory_human:5.23G

   object encoding :hashtable



3) 条件：
   hash-max-ziplist-entries  > 512 (520)
   hash-max-ziplist-value <64(60)

   dbsize:   49998

   key :mapKey:117573535
   filed:test key length ,need length is gt 64 byte.it is to117573833
   value:test key length ,need length is gt 64 byte.it is to117573833

    127.0.0.1:6379> hlen mapKey:117573535
    (integer) 520

   used_memory_human:3.40G

   object encoding :hashtable


4) 条件：
   hash-max-ziplist-entries  < 512 (500)
   hash-max-ziplist-value <64(60)

   dbsize:   49999

   key :mapKey:524737234
   filed:test key length ,need length is gt 64 byte.it is to524737494
   value:test key length ,need length is gt 64 byte.it is to524737494

   127.0.0.1:6379> hlen mapKey:524737234
   (integer) 500

   used_memory_human:3.06G

   object encoding :ziplist

总结：

```


 List的内存优化实例

    list-max-ziplist-size  -2
      list-compress-depth 0

      The number of entries allowed
      # -2: max size: 8 Kb   <-- good
      # -1: max size: 4 Kb   <-- good

      # 0: disable all list compression
      # 1: depth 1 means "don't start compressing until after 1 node into the list,
      #    going from either the head or tail"
      #    So: [head]->node->node->...->node->[tail]
      #    [head], [tail] will always be uncompressed; inner nodes will compress.


  1) 条件：


    list-compress-depth 0
    list-max-ziplist-size  -2

     dbsize:   49999

     key :mapKey:524737234


     127.0.0.1:6379> hlen mapKey:524737234
     (integer) 500

     used_memory_human:3.06G

     object encoding :ziplist


2) 条件：

    list-compress-depth 1

  list-max-ziplist-size  -2
     dbsize:   49999

     key :mapKey:524737234


     127.0.0.1:6379> hlen mapKey:524737234
     (integer) 500

     used_memory_human:3.06G

     object encoding :ziplist



3) 条件：

        list-max-ziplist-size  -2
        list-compress-depth 1

         dbsize:   49999

         key :mapKey:524737234


         127.0.0.1:6379> hlen mapKey:524737234
         (integer) 500

         used_memory_human:3.06G

         object encoding :ziplist



Set的内存优化实例()

1)set-max-intset-entries >512  (520) 存储的数据是包含符号位 64位的10进制的整数类型的字符串

     dbsize:   200

     key :setKey:20444404
     127.0.0.1:6379> scard setKey:20444404
     (integer) 520
     used_memory_human:6.40M
     object encoding :hashtable

2)set-max-intset-entries <512  (500)

     dbsize:   200
     key :setKey:50383508
     127.0.0.1:6379> scard setKey:50383508
     (integer) 500

     used_memory_human:1.22M

     object encoding :intset

3)set-max-intset-entries >512  (520)  存的是字符串加了一个t

     dbsize:   200

     key :setKey:85731004
     127.0.0.1:6379> scard setKey:85731004
     (integer) 520
     used_memory_human:8.74M
     object encoding :hashtable



4)set-max-intset-entries >512  (520)  存的是字符串加了一个t

     dbsize:   200

     key :setKey:86662222
     127.0.0.1:6379> scard setKey:86662222
     (integer) 500
     used_memory_human:6.98M
     object encoding :hashtable




zSet的内存优化实例

 1)    zset-max-ziplist-entries  >128 （130）
       zset-max-ziplist-value >64 （70）


   dbsize:   1000

   key :zaddKey:4158812

   member:test key length ,need length is gt 64 byte.it is too large ll4158923
   score:0.023705493968210201

    127.0.0.1:6379> zcard zaddKey:4158812
    (integer) 130

   used_memory_human:25.12M

   object encoding :skiplist


2)    zset-max-ziplist-entries  <128 （120）
      zset-max-ziplist-value >64 （70）

       dbsize:   1000

       key :zaddKey:83824178

       member:test key length ,need length is gt 64 byte.it is too large ll83824238
       score:0.0054909356620924665

        127.0.0.1:6379> zcard zaddKey:83824178
        (integer) 120

       used_memory_human:21.58M

       object encoding :skiplist


3)   zset-max-ziplist-entries  >128 （130）
     zset-max-ziplist-value <64 （60）

       dbsize:   1000

       key :zaddKey:21401026

       member:test key length ,need length is gt 64 byte.it is to21401073
       score:0.0075969902614139162

        127.0.0.1:6379> zcard zaddKey:4158812
        (integer) 130

       used_memory_human:23.14M

       object encoding :skiplist


4)  zset-max-ziplist-entries  <128 （120）
    zset-max-ziplist-value <64 （60）

       dbsize:   1000

       key :zaddKey:51080267

       member:test key length ,need length is gt 64 byte.it is to51080298
       score:0.0082274075888735254

        127.0.0.1:6379> zcard zaddKey:51080267
        (integer) 120

       used_memory_human:10.65M

       object encoding :ziplist



 ```


存储　id -> v1, v2 其中 v1和v2是随机的int64.第一种，只是简单的string存储，第二种，把v1,v2以base64等或者类似机制转为二进制字符串存储.

```
v1和v2是随机的int64
    127.0.0.1:6379> dbsize
    (integer) 498696
    127.0.0.1:6379> scan 0
    1) "360448"
    2)  1) "longKey:69677052"
        2) "longKey:52327791"
        3) "longKey:78972825"
        4) "longKey:52254007"
        5) "longKey:17822021"
        6) "longKey:16315400"
        7) "longKey:57830608"
        8) "longKey:43967667"
        9) "longKey:70333777"
       10) "longKey:94829727"
       11) "longKey:51608598"
       12) "longKey:58582819"
    127.0.0.1:6379> get longKey:69677052
    "69677052"
    127.0.0.1:6379> object encoding longKey:69677052
    "int"
    127.0.0.1:6379> info Memory
    # Memory
    used_memory:44874104
    used_memory_human:42.80M
    used_memory_rss:53878784
    used_memory_rss_human:51.38M
    used_memory_peak:3014519760
    used_memory_peak_human:2.81G
    total_system_memory:4143886336
    total_system_memory_human:3.86G
    used_memory_lua:37888
    used_memory_lua_human:37.00K
    maxmemory:0
    maxmemory_human:0B
    maxmemory_policy:noeviction
    mem_fragmentation_ratio:1.20
    mem_allocator:jemalloc-4.0.3


二进制的字符串
    127.0.0.1:6379> dbsize
    (integer) 498762
    127.0.0.1:6379> scan 0
    1) "360448"
    2)  1) "longKey:52447371"
        2) "longKey:79886333"
        3) "longKey:70324932"
        4) "longKey:82925575"
        5) "longKey:90295967"
        6) "longKey:51894353"
        7) "longKey:55784365"
        8) "longKey:73923241"
        9) "longKey:48683583"
       10) "longKey:84544025"
       11) "longKey:97242179"
       12) "longKey:36238904"
       13) "longKey:19017625"
    127.0.0.1:6379> object encoding longKey:52447371
    "embstr"
    127.0.0.1:6379> info Memory
    # Memory
    used_memory:52822048
    used_memory_human:50.38M
    used_memory_rss:61710336
    used_memory_rss_human:58.85M
    used_memory_peak:3014519760
    used_memory_peak_human:2.81G
    total_system_memory:4143886336
    total_system_memory_human:3.86G
    used_memory_lua:37888
    used_memory_lua_human:37.00K
    maxmemory:0
    maxmemory_human:0B
    maxmemory_policy:noeviction
    mem_fragmentation_ratio:1.17
    mem_allocator:jemalloc-4.0.3
    127.0.0.1:6379> get longKey:52447371
    "11001000000100100010001011"



```
####字符串对象保存各类型值的编码方式
1. 可以用 long 类型保存的整数。  |  int
2. 可以用 long double 类型保存的浮点数。	|embstr 或者 raw  `这个字符串值的长度大于 39 字节 使用raw`
3. 字符串值， 或者因为长度太大而没办法用 long 类型表示的整数， 又或者因为长度太大而没办法用 long double 类型表示的浮点数。	|embstr 或者 raw