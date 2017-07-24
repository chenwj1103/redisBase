
1. 比较key值长和短的内存区别.
````
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


执行清除

127.0.0.1:6379> flushall
OK
(3.34s)
127.0.0.1:6379> dbsize
(integer) 0
127.0.0.1:6379> info Memory
# Memory
used_memory:820992
used_memory_human:801.75K
used_memory_rss:21307392
used_memory_rss_human:20.32M
used_memory_peak:2986123072
used_memory_peak_human:2.78G
total_system_memory:4143886336
total_system_memory_human:3.86G
used_memory_lua:37888
used_memory_lua_human:37.00K
maxmemory:0
maxmemory_human:0B
maxmemory_policy:noeviction
mem_fragmentation_ratio:25.95
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

````

2.高级一点的数据类型如set, sorted set,hash,他们在数据大小不同的情况下使用的存储结构是不同的. see https://redis.io/topics/memory-optimization
弄明白里面每一个参数的意义.例如 hash-max-zipmap-entries 512 写程序来对比内存使用大小.


#HASH的内存优化实例

1)
    hash-max-ziplist-entries >512 （1万 entries>512 entries>64）
    hash-max-ziplist-value  >64 (49万 value>64  entries<512)
````
    127.0.0.1:6379> dbsize
    (integer) 498720
    127.0.0.1:6379> scan 0
    1) "163840"
    2)  1) "mapKey:73293890"
        2) "mapKey:64423927"
        3) "mapKey:7010940"
        4) "mapKey:98631431"
        5) "mapKey:2049735"
        6) "mapKey:65125853"
        7) "mapKey:60842228"
        8) "mapKey:82903244"
        9) "mapKey:20185661"
       10) "mapKey:91981620"
    127.0.0.1:6379> object encoding mapKey:73293890
    "hashtable"
    127.0.0.1:6379> info memory
    # Memory
    used_memory:2788305992
    used_memory_human:2.60G
    used_memory_rss:2865180672
    used_memory_rss_human:2.67G
    used_memory_peak:3014519760
    used_memory_peak_human:2.81G
    total_system_memory:4143886336
    total_system_memory_human:3.86G
    used_memory_lua:37888
    used_memory_lua_human:37.00K
    maxmemory:0
    maxmemory_human:0B
    maxmemory_policy:noeviction
    mem_fragmentation_ratio:1.03
    mem_allocator:jemalloc-4.0.3


 2)hash-max-ziplist-entries  <512 (50万 entries<512 value<64)
   hash-max-ziplist-value <64

     127.0.0.1:6379> dbsize
     (integer) 498694
     127.0.0.1:6379> scan 0
     1) "32768"
     2)  1) "mapKey:89856531"
         2) "mapKey:40601055"
         3) "mapKey:52155425"
         4) "mapKey:83622674"
         5) "mapKey:17020923"
         6) "mapKey:5148821"
         7) "mapKey:11712272"
         8) "mapKey:95316466"
         9) "mapKey:33847714"
        10) "mapKey:77469552"
        11) "mapKey:2098874"
     127.0.0.1:6379> Object ENCODING  mapKey:89856531
     "ziplist"
     127.0.0.1:6379> info Memory
     # Memory
     used_memory:377002432
     used_memory_human:359.54M
     used_memory_rss:391245824
     used_memory_rss_human:373.12M
     used_memory_peak:3014519760
     used_memory_peak_human:2.81G
     total_system_memory:4143886336
     total_system_memory_human:3.86G
     used_memory_lua:37888
     used_memory_lua_human:37.00K
     maxmemory:0
     maxmemory_human:0B
     maxmemory_policy:noeviction
     mem_fragmentation_ratio:1.04
     mem_allocator:jemalloc-4.0.3

 ````


 #List的内存优化实例

1)    list-max-ziplist-size  -2
      list-compress-depth 0

      # -2: max size: 8 Kb   <-- good
      # -1: max size: 4 Kb   <-- good

      # 0: disable all list compression
      # 1: depth 1 means "don't start compressing until after 1 node into the list,
      #    going from either the head or tail"
      #    So: [head]->node->node->...->node->[tail]
      #    [head], [tail] will always be uncompressed; inner nodes will compress.

````
   127.0.0.1:6379> dbsize
   (integer) 492202
   127.0.0.1:6379> scan 0
   1) "196608"
   2)  1) "listKey:77533394"
       2) "listKey:13417629"
       3) "listKey:96863048"
       4) "listKey:47932058"
       5) "listKey:86145819"
       6) "listKey:76739577"
       7) "listKey:94135054"
       8) "listKey:74655838"
       9) "listKey:45634837"
      10) "listKey:22441455"
      11) "listKey:34325928"
   127.0.0.1:6379> Object encoding listKey:77533394
   "hashtable"
   127.0.0.1:6379> info Memory
   # Memory
   used_memory:542886456
   used_memory_human:517.74M
   used_memory_rss:561434624
   used_memory_rss_human:535.43M
   used_memory_peak:3014519760
   used_memory_peak_human:2.81G
   total_system_memory:4143886336
   total_system_memory_human:3.86G
   used_memory_lua:37888
   used_memory_lua_human:37.00K
   maxmemory:0
   maxmemory_human:0B
   maxmemory_policy:noeviction
   mem_fragmentation_ratio:1.03
   mem_allocator:jemalloc-4.0.3


2) list-max-ziplist-entries <512
   list-max-ziplist-value <64  (全部数据  entries <512  value <64)

    127.0.0.1:6379> DBSIZE
    (integer) 502843
    127.0.0.1:6379>
    127.0.0.1:6379> info Memory
    # Memory
    used_memory:93728248
    used_memory_human:89.39M
    used_memory_rss:107859968
    used_memory_rss_human:102.86M
    used_memory_peak:3014519760
    used_memory_peak_human:2.81G
    total_system_memory:4143886336
    total_system_memory_human:3.86G
    used_memory_lua:37888
    used_memory_lua_human:37.00K
    maxmemory:0
    maxmemory_human:0B
    maxmemory_policy:noeviction
    mem_fragmentation_ratio:1.15
    mem_allocator:jemalloc-4.0.3
    127.0.0.1:6379>  scan 0
    1) "294912"
    2)  1) "listKey:1283729"
        2) "listKey:66127017"
        3) "listKey:56998857"
        4) "listKey:12259435"
        5) "listKey:21866672"
        6) "listKey:2338803"
        7) "listKey:23134138"
        8) "listKey:3850645"
        9) "listKey:36345483"
       10) "listKey:80173708"
       11) "listKey:48060181"
    127.0.0.1:6379> object encoding listKey:1283729
    "quicklist"

````


 #Set的内存优化实例
 ````
1)set-max-intset-entries >512  (1万个>512 49万个<512)

    127.0.0.1:6379> dbsize
    (integer) 489636
    127.0.0.1:6379> scard setKey:81890650
    (integer) 520
    127.0.0.1:6379> object encoding setKey:81890650
    "hashtable"
    127.0.0.1:6379> scard setKey:72899004
    (integer) 10
    127.0.0.1:6379> object encoding setKey:72899004
    "intset"
    127.0.0.1:6379> info Memory
    # Memory
    used_memory:83719328
    used_memory_human:79.84M
    used_memory_rss:94388224
    used_memory_rss_human:90.02M
    used_memory_peak:3014519760
    used_memory_peak_human:2.81G
    total_system_memory:4143886336
    total_system_memory_human:3.86G
    used_memory_lua:37888
    used_memory_lua_human:37.00K
    maxmemory:0
    maxmemory_human:0B
    maxmemory_policy:noeviction
    mem_fragmentation_ratio:1.13
    mem_allocator:jemalloc-4.0.3

2)set-max-intset-entries <512  (50万个<512)
    127.0.0.1:6379> dbsize
    (integer) 498793
    127.0.0.1:6379> scan 0
    1) "425984"
    2)  1) "setKey:89186665"
        2) "setKey:25990168"
        3) "setKey:30376790"
        4) "setKey:56297937"
        5) "setKey:12719381"
        6) "setKey:30075705"
        7) "setKey:60013765"
        8) "setKey:99466138"
        9) "setKey:52754745"
       10) "setKey:39037357"
    127.0.0.1:6379> object encoding setKey:89186665
    "intset"
    127.0.0.1:6379> info Memory
    # Memory
    used_memory:44522800
    used_memory_human:42.46M
    used_memory_rss:53714944
    used_memory_rss_human:51.23M
    used_memory_peak:3014519760
    used_memory_peak_human:2.81G
    total_system_memory:4143886336
    total_system_memory_human:3.86G
    used_memory_lua:37888
    used_memory_lua_human:37.00K
    maxmemory:0
    maxmemory_human:0B
    maxmemory_policy:noeviction
    mem_fragmentation_ratio:1.21
    mem_allocator:jemalloc-4.0.3

 #Set的内存优化实例

 ````
 1)zset-max-ziplist-entries  >128 （8.1万  entries>128 ,value>64）
   zset-max-ziplist-value >64

    127.0.0.1:6379> dbsize
    (integer) 81608
    127.0.0.1:6379> SCAN 0
    1) "90112"
    2)  1) "zaddKey:72827837"
        2) "zaddKey:51634483"
        3) "zaddKey:62234560"
        4) "zaddKey:16367616"
        5) "zaddKey:8308892"
        6) "zaddKey:36558983"
        7) "zaddKey:36733832"
        8) "zaddKey:57353685"
        9) "zaddKey:40624942"
       10) "zaddKey:50445470"
    127.0.0.1:6379> object encoding zaddKey:72827837
    "skiplist"
    127.0.0.1:6379> zcard  zaddKey:72827837
    (integer) 130
    127.0.0.1:6379> zrange  zaddKey:72827837 0 3 withscores
    1) "72827927testKeyLength++zaddKey\xe6\xb5\x8b\xe8\xaf\x95\xe5\x8d\xa0\xe7\x94\xa8\xe5\x86\x85\xe5\xad\x98\xe7\xa9\xba\xe9\x97\xb4\xe5\xa4\xa7\xe5\xb0\x8f\xef\xbc\x8c\xe5\xad\x97\xe8\x8a\x82\xe9\x95\xbf\xe5\xba\xa6"
    2) "0.0018567401032645314"
    3) "72827848testKeyLength++zaddKey\xe6\xb5\x8b\xe8\xaf\x95\xe5\x8d\xa0\xe7\x94\xa8\xe5\x86\x85\xe5\xad\x98\xe7\xa9\xba\xe9\x97\xb4\xe5\xa4\xa7\xe5\xb0\x8f\xef\xbc\x8c\xe5\xad\x97\xe8\x8a\x82\xe9\x95\xbf\xe5\xba\xa6"
    4) "0.004352924525922397"
    5) "72827889testKeyLength++zaddKey\xe6\xb5\x8b\xe8\xaf\x95\xe5\x8d\xa0\xe7\x94\xa8\xe5\x86\x85\xe5\xad\x98\xe7\xa9\xba\xe9\x97\xb4\xe5\xa4\xa7\xe5\xb0\x8f\xef\xbc\x8c\xe5\xad\x97\xe8\x8a\x82\xe9\x95\xbf\xe5\xba\xa6"
    6) "0.030811260549635233"
    7) "72827843testKeyLength++zaddKey\xe6\xb5\x8b\xe8\xaf\x95\xe5\x8d\xa0\xe7\x94\xa8\xe5\x86\x85\xe5\xad\x98\xe7\xa9\xba\xe9\x97\xb4\xe5\xa4\xa7\xe5\xb0\x8f\xef\xbc\x8c\xe5\xad\x97\xe8\x8a\x82\xe9\x95\xbf\xe5\xba\xa6"
    8) "0.034050642587689417"
    127.0.0.1:6379> info Memory
    # Memory
    used_memory:2057049848
    used_memory_human:1.92G
    used_memory_rss:2114818048
    used_memory_rss_human:1.97G
    used_memory_peak:3014519760
    used_memory_peak_human:2.81G
    total_system_memory:4143886336
    total_system_memory_human:3.86G
    used_memory_lua:37888
    used_memory_lua_human:37.00K
    maxmemory:0
    maxmemory_human:0B
    maxmemory_policy:noeviction
    mem_fragmentation_ratio:1.03
    mem_allocator:jemalloc-4.0.3


 2)zset-max-ziplist-entries  <128 （8.1万  entries<128 ,value<64）
   zset-max-ziplist-value <64
    127.0.0.1:6379> dbsize
    (integer) 80868
    127.0.0.1:6379> scan 0
    1) "102400"
    2)  1) "zaddKey:75353794"
        2) "zaddKey:96759618"
        3) "zaddKey:20221988"
        4) "zaddKey:7352777"
        5) "zaddKey:55194582"
        6) "zaddKey:37534671"
        7) "zaddKey:65221063"
        8) "zaddKey:98939557"
        9) "zaddKey:40196902"
       10) "zaddKey:81951734"
    127.0.0.1:6379> object encoding zaddKey:75353794
    "ziplist"
    127.0.0.1:6379> zrange zaddKey:75353794 0 -1 withscores
    1) "75353795"
    2) "0.22051296878932347"
    3) "75353796"
    4) "0.79986477041725113"
    127.0.0.1:6379> info Memory
    # Memory
    used_memory:13214824
    used_memory_human:12.60M
    used_memory_rss:20992000
    used_memory_rss_human:20.02M
    used_memory_peak:3014519760
    used_memory_peak_human:2.81G
    total_system_memory:4143886336
    total_system_memory_human:3.86G
    used_memory_lua:37888
    used_memory_lua_human:37.00K
    maxmemory:0
    maxmemory_human:0B
    maxmemory_policy:noeviction
    mem_fragmentation_ratio:1.59
    mem_allocator:jemalloc-4.0.3



 ````


# 存储　id -> v1, v2 其中 v1和v2是随机的int64.第一种，只是简单的string存储，第二种，把v1,v2以base64等或者类似机制转为二进制字符串存储.

# v1和v2是随机的int64
````
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

    字符串对象保存各类型值的编码方式

    可以用 long 类型保存的整数。	int
    可以用 long double 类型保存的浮点数。	embstr 或者 raw （并且这个字符串值的长度大于 39 字节 使用raw）
    字符串值， 或者因为长度太大而没办法用 long 类型表示的整数， 又或者因为长度太大而没办法用 long double 类型表示的浮点数。	embstr 或者 raw

````