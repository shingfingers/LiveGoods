markdown
# LiveGoods - 房屋租赁与交易平台

LiveGoods 是一个基于微服务架构的房屋租赁与交易平台，采用前后端分离的开发模式。前端基于 Vue.js 全家桶进行开发，后端使用 Spring Boot + Spring Cloud 微服务框架，整合 Dubbo RPC、Nacos、Seata、RabbitMQ 等中间件，提供高可扩展性和高可维护性。项目涵盖房屋浏览、搜索、秒杀抢购、订单管理、评论反馈等核心功能模块。

## 目前功能

### 房屋展示
- Banner 轮播图展示
- 热门房源推荐
- 房源详情查看
- 房源多条件搜索

### 秒杀抢购
- 房源秒杀活动
- 预定时限控制
- 消息队列异步下单

### 订单管理
- 订单创建与查询
- 订单状态管理
- 分布式事务保证数据一致性

### 评论反馈
- 用户评论发表
- 评论数据脱敏
- 分页加载评论列表

### 用户服务
- 短信验证码登录
- Redis 缓存验证码

### 网关服务
- 统一路由转发
- 负载均衡
- 服务发现与注册

## 技术选型

| 技术 | 说明 |
|---|---|
| Spring Boot 3.1.2 | 后端基础框架 |
| Spring Cloud Gateway | API 网关 |
| Dubbo 3.2.5 | RPC 远程调用框架 |
| Nacos 2.3.2 | 服务注册、发现与配置中心 |
| MyBatis 3.5.13 | ORM 框架 |
| MySQL 8.0 | 关系型数据库 |
| Redis | 缓存与会话管理 |
| RabbitMQ | 消息队列，实现异步下单 |
| Seata 1.7.0 | 分布式事务解决方案 |
| Elasticsearch 8.4.1 | 搜索引擎 |
| FastDFS | 分布式文件存储 |
| Vue 2 + Vue CLI | 前端框架 |
| Vue Router | 前端路由管理 |
| Axios | 前端 HTTP 请求 |
| Element UI | 前端 UI 组件库 |

## 项目架构

```text
livegoods_parent
├── livegoods_api                  # 服务接口定义模块（Dubbo 公共接口）
│   ├── livegoods_api_banner       # Banner 服务接口
│   ├── livegoods_api_details      # 房源详情服务接口
│   ├── livegoods_api_feedback     # 评论反馈服务接口
│   ├── livegoods_api_hot          # 热门房源服务接口
│   ├── livegoods_api_login        # 登录服务接口
│   ├── livegoods_api_order        # 订单服务接口
│   ├── livegoods_api_search       # 搜索服务接口
│   └── livegoods_api_seckill      # 秒杀服务接口
├── livegoods_common               # 公共服务模块
│   ├── livegoods_common_amqp      # RabbitMQ 公共配置
│   ├── livegoods_common_dubbo     # Dubbo 公共配置
│   ├── livegoods_common_mysql     # MySQL 公共配置
│   ├── livegoods_common_nacos_config    # Nacos 配置中心
│   ├── livegoods_common_nacos_discovery # Nacos 服务发现
│   ├── livegoods_common_redis     # Redis 公共配置
│   ├── livegoods_commons_seata    # Seata 分布式事务配置
│   └── livegoods_common_vo        # 公共 VO 对象
├── livegoods_frontend             # 前端微服务模块
│   ├── livegoods_frontend_banner  # Banner 服务（端口 8081）
│   ├── livegoods_frontend_details # 房源详情服务（端口 8084）
│   ├── livegoods_frontend_feedback # 评论反馈服务（端口 8085）
│   ├── livegoods_frontend_hot     # 热门房源服务（端口 8083）
│   ├── livegoods_frontend_login   # 登录服务（端口 8086）
│   ├── livegoods_frontend_order   # 订单服务（端口 8088）
│   ├── livegoods_frontend_search  # 搜索服务（端口 8082）
│   └── livegoods_frontend_seckill # 秒杀服务（端口 8087）
├── livegoods_middleware            # 中间件模块
│   └── livegoods_middleware_gateway # 网关服务（端口 4006）
├── livegoods_pojo                  # 实体类模块
│   ├── livegoods_pojo_banner       # Banner 实体
│   ├── livegoods_pojo_details      # 房源详情实体
│   ├── livegoods_pojo_feedback     # 评论反馈实体
│   ├── livegoods_pojo_order        # 订单实体
│   ├── livegoods_pojo_product      # 产品实体
│   └── livegoods_pojo_search       # 搜索实体
└── pom.xml                         # Maven 父工程配置
后端说明
启动入口
各服务均有独立启动类，位于对应模块的 com.tz 包下：

服务	启动类	端口
Banner	FrontendBannerApplication	8081
Details	FrontendDetailsApplication	8084
Feedback	FrontendFeedbackApplication	8085
Hot	FrontendHotApplication	8083
Login	FrontendLoginApplication	8086
Order	FrontendOrderApplication	8088
Search	FrontendSearchApplication	8082
Seckill	FrontendSeckillApplication	8087
Gateway	GatewayApplication	4006
环境要求
JDK 17+

Maven 3.8+

MySQL 8.0+

Redis

Nacos 2.x

RabbitMQ

Elasticsearch 8.x

FastDFS

Seata 1.7.0

后端启动
启动 MySQL、Redis、Nacos、RabbitMQ、Elasticsearch、FastDFS、Seata 等中间件

在 livegoods_parent 目录执行：

bash
mvn clean install -DskipTests
依次启动各微服务（建议先启动 Gateway，再启动其他服务）

后端打包
bash
mvn clean package -DskipTests
打包产物位于各模块的 target/ 目录。

前端说明
前端为独立 Vue 项目，位于 livegoods前端启动 目录。

环境要求
Node.js 14+

npm 6+ 或 cnpm

前端启动
bash
cd livegoods前端启动
cnpm install
npm run start
前端默认端口：81

前端打包
bash
npm run build
联调方式
启动虚拟机中的 MySQL、Redis、Nacos、RabbitMQ、Elasticsearch、FastDFS、Seata

在 IDEA 中依次启动各后端微服务

启动前端 npm run start

浏览器访问 http://localhost:81 进行业务联调

微服务调用流程
text
浏览器 → Gateway (4006)
           ├── /banner       → frontend-banner (8081)
           ├── /details      → frontend-details (8084)
           ├── /hotProduct   → frontend-hot (8083)
           ├── /search       → frontend-search (8082)
           ├── /login        → frontend-login (8086)
           ├── /order        → frontend-order (8088)
           ├── /feedback     → frontend-feedback (8085)
           └── /seckill      → frontend-seckill (8087)
服务间通过 Dubbo RPC 进行远程调用，Nacos 作为注册中心统一管理服务实例。

