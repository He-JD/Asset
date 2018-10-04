
asset系统是基于springBoot开发的一套简单脚手架系统，主要用于快速搭建企业中小型系统，由于企业中小型系统所采用的技术框架大体相同，为了避免每次重新搭建和配置的繁琐及各种兼容问题，以及为了一个有一个属于自己代码风格的系统（这应该是每一个程序员的理想吧），asset就这么诞生了。


#### 主要功能：
   本系统定位是一个脚手架，也即是说只搭建系统的骨架，没有具体的业务功能，很多开源系统都是整套系统，相当于一个完整的项目，   这样的弊端很明显，因为不同的业务场景所需要的具体业务不同，所以我们只能学习和参考，膜拜，但不能拿来即用，   随着技术的迭代，优胜劣汰,目前，虽然项目各有不同，但各种项目技术框架的选型和配置都差不多，   所以我更喜欢偏向于搭建一个可以基于任何系统拿来即用的脚手架，因为我觉得有了骨架，具体的血肉因当根据不同的业务场景进行填充，   我相信一个好的脚手架，一定可以大大减少我们苦逼开发人员在搭建配置框架那些繁琐的工作量，   在基于一套配置完善的且风格统一的编码风格下，便得以可以将更多的时间与精力分配到具体的业务功能代码上，轻轻松松，安安静静的码代码。


#### 技术框架

   * 核心框架：SpringBoot2.0
   * 安全框架：Apache Shiro 1.3.2
   * 缓存框架：Redis 3.2
   * 持久层框架：MyBatis 3 mybatisplus 2.1.4
   * 数据库连接池：Alibaba Druid 1.0.2
   * 日志管理：SLF4J 1.7、logback
   * 前端框架：layui
   * 后台模板：layuicms 2.0。
   * 接口文档：swagger2.0
   * 其它插件：lombok

统一编码规范：
>网页功能主要是create、read、update、delete。但是实现方式、网址设计可能不一样。
>目前主流提倡RESTful面向资源地设计API，当然为了更好地体现语义化（易于理解）、统一规范（同样为了易于接受和理解）、以及表现资源的层级关系、资源与资源之间的关系等等，那么URI的设计应该就要有所讲究了。

  
#### URL命名定义规范：

>       URL请求采用小写字母，数字，部分特殊符号（非制表符）组成。  
>       URL请求中不采用大小写混合的驼峰命名方式，尽量采用全小写单词，如果需要连接多个单词，则采用连接符“_”连接单词
URL分级
>       第一级Pattern为模块,比如组织管理/orgz, 网格化：/grid
>       第二级Pattern为资源分类或者功能请求，优先采用资源分类
  ****
#### CRUD请求定义规范（RESTful）:  
   1.  GET-获取资源
     读取 (read，使用 GET )
   2.  POST-创建资源-不具有幂等性
    新增 (create，使用 POST )
   3.  PUT-更新 (update，使用 PUT )
    创建（更新）资源-具有幂等性
   4. DELETE-删除资源
    删除 (destroy，使用 DELETE)
>幂等性：每次HTTP请求相同的参数，相同的URL，产生的结果是相同的

#### 统一返回状态码规范：
>       200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
>       201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
>       202 Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
>       400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
>       401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
>       403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
>       404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
>       406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
>       410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
>       422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
>       500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。

|Dao 接口命名规范   |  Service 接口命名规范|
 | - | :-: |  
|insert... | Gryffindor| 
| batchInsert... | add...| 
|selectXXXByXXX | findXXXByXXX| 
|selectOne|findOne|
|count...|count...|
|selectXXXList|findXXXList|
|updateXXXByXXX|modifyXXXByXXX|
|deleteXXXByXXX|removexxxByXXX|










